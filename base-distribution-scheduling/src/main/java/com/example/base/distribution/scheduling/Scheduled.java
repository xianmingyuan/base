package com.example.base.distribution.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * 当locked为true时，表明当前抢占到锁
 * 当value为open时，表明当前锁的开关为开状态，允许执行
 *
 * @author xianmingyuan
 */
@Slf4j
class Scheduled {

    private static final String OPEN = "open";

    private ZooKeeper keeper;
    private String path;
    private String value = OPEN;
    private boolean locked = false;

    Scheduled(String address, int timeout, String path) {
        try {
            keeper = new ZooKeeper(address, timeout, event -> {
            });
            this.path = path;
            tryLocked();
        } catch (InterruptedException e) {
            log.error("zookeeper执行exists、create等方法发生中断异常", e);
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            log.error("连接zookeeper进行master选举发生异常", e);
        } catch (KeeperException e) {
            log.error("zookeeper执行exists、create等方法发生异常", e);
        }
    }

    /**
     * 如果当前锁的开关状态为开，则返回true，否则返回false
     */
    boolean isOpen() {
        return OPEN.equals(value);
    }

    /**
     * 如果当前抢占到锁，则返回true，否则返回false
     */
    boolean hasLocked() {
        return locked;
    }

    /**
     * 尝试抢占锁
     */
    private void tryLocked() throws KeeperException, InterruptedException {
        Stat exists = keeper.exists(path, event -> {
            Watcher.Event.EventType type = event.getType();
            if (type.equals(Watcher.Event.EventType.NodeDeleted)) {
                log.info("有节点解锁，进行抢锁");
                try {
                    tryLocked();
                } catch (KeeperException e) {
                    log.error("zookeeper执行exists、create等方法发生异常", e);
                } catch (InterruptedException e) {
                    log.error("zookeeper执行exists、create等方法发生中断异常", e);
                    Thread.currentThread().interrupt();
                }
            }
        });

        if (null == exists) {
            try {
                keeper.create(path, value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                log.info("已抢到锁");
                watchValue();
                locked = true;
            } catch (Exception e) {
                tryLocked();
            }
        } else {
            log.info("抢锁失败，当前已有其他节点持有锁");
            locked = false;
        }
    }

    private void watchValue() throws KeeperException, InterruptedException {
        keeper.exists(path, event -> {
            if (event.getType().equals(Watcher.Event.EventType.NodeDataChanged)) {
                try {
                    value = new String(keeper.getData(path, true, null));
                    log.info("value值被修改了，value = [{}]", value);
                    watchValue();
                } catch (KeeperException e) {
                    log.error("读取value值异常", e);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }
}
