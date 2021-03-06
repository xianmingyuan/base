package com.example.base.test;

import com.example.base.distribution.lock.annotation.EnableDistributionLock;
import com.example.base.distribution.scheduling.annotation.EnableDistributionScheduled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableDistributionLock
@SpringBootApplication
public class BaseTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseTestApplication.class, args);
    }

}
