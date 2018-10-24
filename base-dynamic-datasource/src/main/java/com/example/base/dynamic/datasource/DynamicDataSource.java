package com.example.base.dynamic.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author xianmingyuan
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Override
    protected Object determineCurrentLookupKey() {
        return threadLocal.get();
    }

    public static void setName(String name) {
        threadLocal.set(name);
    }

}
