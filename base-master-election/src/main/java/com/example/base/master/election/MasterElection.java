package com.example.base.master.election;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;


/**
 * @author xianmingyuan
 */
@Slf4j
@Data
public class MasterElection {

    private ZooKeeper zooKeeper;
    private String path;
    private String value;
    private boolean isMaster;

    public MasterElection(String address, int timeout, String path, String value) {
        try {
            zooKeeper = new ZooKeeper(address, timeout, event -> { });
            this.path = path;
            this.value = value;
            create();
        } catch (InterruptedException e) {
            log.error("zookeeper执行exists、create等方法发生中断异常", e);
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            log.error("连接zookeeper进行master选举发生异常", e);
        } catch (KeeperException e) {
            log.error("zookeeper执行exists、create等方法发生异常", e);
        }
    }

    private void create() throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists(path, event -> {
            Watcher.Event.EventType type = event.getType();
            if (type.equals(Watcher.Event.EventType.NodeDeleted)) {
                log.info("有master退出，进行master选举");
                try {
                    create();
                } catch (KeeperException e) {
                    log.error("zookeeper执行exists、create等方法发生异常", e);
                } catch (InterruptedException e) {
                    log.error("zookeeper执行exists、create等方法发生中断异常", e);
                    Thread.currentThread().interrupt();
                }
            }
        });
        if (null == exists) {
            zooKeeper.create(path, value.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            log.info("已抢到master");
            setMaster(true);
        } else {
            log.info("抢master失败,进行slave");
            setMaster(false);
        }
    }
}
