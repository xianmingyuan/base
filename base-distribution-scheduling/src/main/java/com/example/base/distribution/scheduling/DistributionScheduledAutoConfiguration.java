package com.example.base.distribution.scheduling;

import org.springframework.context.annotation.Bean;

public class DistributionScheduledAutoConfiguration {

    @Bean
    public DistributionScheduledAspect distributionLockAspect(DistributionScheduledProperties properties){
        return new DistributionScheduledAspect(properties);
    }

    @Bean
    public DistributionScheduledProperties distributionLockProperties() {
        return new DistributionScheduledProperties();
    }

}
