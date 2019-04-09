package com.example.base.test.service;

import com.example.base.distribution.lock.annotation.DistributionLock;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private int count = 10000;

    //@DistributionLock
    public void test(){
        count = count - 1;
    }

    public int getCount(){
        return count;
    }

}
