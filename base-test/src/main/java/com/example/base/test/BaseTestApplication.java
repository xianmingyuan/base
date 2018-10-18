package com.example.base.test;

import com.example.base.authentication.EnableAuthentication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableAuthentication
@SpringBootApplication
@EnableFeignClients
public class BaseTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseTestApplication.class, args);
    }
}
