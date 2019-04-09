package com.example.base.distribution.lock.configuration;

import com.example.base.distribution.lock.DistributionLockAspect;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

public class DistributionLockAutoConfiguration {

    @Bean
    public ZooKeeper zooKeeper() throws IOException {
        return new ZooKeeper("192.168.175.131:2181", 300000, event -> {
        });
    }

    @Bean
    public DistributionLockAspect distributionLockAspect(ZooKeeper zooKeeper){
        return new DistributionLockAspect(zooKeeper);
    }

}
