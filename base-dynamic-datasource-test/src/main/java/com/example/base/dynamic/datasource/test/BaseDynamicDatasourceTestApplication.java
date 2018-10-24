package com.example.base.dynamic.datasource.test;

import com.example.base.dynamic.datasource.annotation.EnableDynamicDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xianmingyuan
 */
@EnableDynamicDataSource
@SpringBootApplication
public class BaseDynamicDatasourceTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseDynamicDatasourceTestApplication.class, args);
    }

}
