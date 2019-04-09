package com.example.base.distribution.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
class Lock {

    private static final String DEFAULT_ROOT_PATH = "/distribution_lock";
    private String key;
    private final java.util.concurrent.locks.Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private ZooKeeper zooKeeper;

    Lock(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
        Stat exists;
        try {
            exists = zooKeeper.exists(DEFAULT_ROOT_PATH, event -> {

            });
            if (null == exists) {
                zooKeeper.create(DEFAULT_ROOT_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (InterruptedException e) {
            log.error("监听节点是否存在被中断", e);
            Thread.currentThread().interrupt();
        } catch (KeeperException e) {
            log.error("监听节点是否存在异常", e);
        }

    }

    boolean hasLock() {
        List<String> children = null;
        try {
            children = zooKeeper.getChildren(DEFAULT_ROOT_PATH, false);
            return key.equals(DEFAULT_ROOT_PATH + "/" + children.get(0));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    boolean tryLock(String path) {
        try {
            key = zooKeeper.create(DEFAULT_ROOT_PATH + path, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            while (!hasLock()) {

            }
            return true;
        } catch (KeeperException e) {
            log.error("创建临时顺序节点异常", e);
        } catch (InterruptedException e) {
            log.error("创建临时顺序节点被中断", e);
            Thread.currentThread().interrupt();
        }
        return false;
    }

    void close() {
        try {
            zooKeeper.delete(key, 0);
        } catch (KeeperException e) {
            log.error("删除临时顺序节点异常", e);
        } catch (InterruptedException e) {
            log.error("删除临时顺序节点被中断", e);
            Thread.currentThread().interrupt();
        }
    }

}
