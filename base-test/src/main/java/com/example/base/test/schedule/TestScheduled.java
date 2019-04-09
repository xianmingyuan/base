package com.example.base.test.schedule;

import com.example.base.distribution.scheduling.annotation.DistributionLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduled {

    @DistributionLock
    @Scheduled(cron = "0/10 * * * * ? ")
    public void test(){
        System.out.println("TestScheduled.test");
    }

}
