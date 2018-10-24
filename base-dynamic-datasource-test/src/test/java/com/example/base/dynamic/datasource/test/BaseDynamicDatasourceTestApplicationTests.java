package com.example.base.dynamic.datasource.test;

import com.example.base.dynamic.datasource.test.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseDynamicDatasourceTestApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        userService.write();
    }

}
