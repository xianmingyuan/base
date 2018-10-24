package com.example.base.dynamic.datasource;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xianmingyuan
 */
@Slf4j
@Configuration
@ComponentScan
public class DynamicDataSourceAutoConfiguration {

    @Bean
    public DynamicDataSource dynamicDataSource(DynamicDataSourceProperties properties){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<String, Map<String, String>> map = properties.getDatasource();
        Map<Object, Object> target = new HashMap<>(map.size());
        String defaultDataSourceName = null;
        for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
            if (Objects.isNull(defaultDataSourceName)) {
                defaultDataSourceName = entry.getKey();
            }
            target.put(entry.getKey(), createDataSource(entry.getValue()));
        }
        dynamicDataSource.setTargetDataSources(target);
        return dynamicDataSource;
    }

    private DataSource createDataSource(Map<String, String> map) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(map.get("driver-class-name"));
        dataSource.setJdbcUrl(map.get("jdbc-url"));
        dataSource.setUsername(map.get("username"));
        dataSource.setPassword(map.get("password"));
        return dataSource;
    }

}
