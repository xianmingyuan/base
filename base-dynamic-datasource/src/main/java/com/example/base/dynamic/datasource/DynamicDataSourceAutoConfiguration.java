package com.example.base.dynamic.datasource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xianmingyuan
 */
@Configuration
@EnableAutoConfiguration(
        exclude = DataSourceAutoConfiguration.class
)
public class DynamicDataSourceAutoConfiguration {

    @Bean
    public DynamicDataSource dynamicDataSource(){
        return new DynamicDataSource();
    }

}
