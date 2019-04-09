package com.example.base.distribution.lock;

import lombok.Data;

@Data
public class ZkConfig {

    private String address = "192.168.175.131:2181";
    private int sessionTimeout = 10;
    private long waitConnTimeout = 10;
    private int fairLock = 1;

}
