package com.example.base.test;

import com.example.base.test.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTestApplicationTests {

    @Autowired
    private TestService testService;

    @Test
    public void contextLoads() {
        for (int y = 0; y < 10; y++) {
            ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 20, 1, TimeUnit.MINUTES
                    , new LinkedBlockingDeque<>());
            for (int i = 0; i < 10000; i++) {
                executor.execute(() -> testService.test());
            }
            while (10000 != executor.getCompletedTaskCount()) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int count = testService.getCount();
            System.out.println("count = " + count);
        }
    }


}
