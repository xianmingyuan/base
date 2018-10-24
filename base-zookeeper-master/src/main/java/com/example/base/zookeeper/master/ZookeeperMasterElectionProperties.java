package com.example.base.zookeeper.master;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Component
@Data
public class ZookeeperMasterElectionProperties {

    @Value("${zookeeper.master.election.address}")
    private String address;
    @Value("${zookeeper.master.election.timeout}")
    private int timeout;
    @Value("${zookeeper.master.election.value}")
    private String value;
}
