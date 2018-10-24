package com.example.base.dynamic.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xianmingyuan
 */
@Data
@Component
@ConfigurationProperties(prefix = "base.dynamic")
public class DataBaseProperties {

    private Map<String, Object> database;

}
