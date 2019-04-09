package com.example.base.distribution.scheduling;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author Administrator
 */
@Data
public class DistributionScheduledProperties {

    @Value("${base.distribution.scheduling.address:111.231.249.159:2181}")
    private String address;
    @Value("${base.distribution.scheduling.timeout:5000}")
    private int timeout;

}
