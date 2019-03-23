package com.example.base.distribution.lock;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Administrator
 */
@Data
public class DistributionLockProperties {

    @Value("${base.distribution.lock.address:111.231.249.159:2181}")
    private String address;
    @Value("${base.distribution.lock.timeout:5000}")
    private int timeout;

}
