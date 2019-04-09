package com.example.base.test.schedule;

import org.springframework.scheduling.annotation.Scheduled;

public class TestScheduled {

    @Scheduled(cron = "0/10 * * * * ? ")
    public void test(){
        System.out.println("TestScheduled.test");
    }

}
