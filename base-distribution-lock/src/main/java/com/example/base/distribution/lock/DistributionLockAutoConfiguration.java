package com.example.base.distribution.lock;

import org.springframework.context.annotation.Bean;

public class DistributionLockAutoConfiguration {

    @Bean
    public DistributionLockAspect distributionLockAspect(DistributionLockProperties properties){
        return new DistributionLockAspect(properties);
    }

    @Bean
    public DistributionLockProperties distributionLockProperties() {
        return new DistributionLockProperties();
    }

}
