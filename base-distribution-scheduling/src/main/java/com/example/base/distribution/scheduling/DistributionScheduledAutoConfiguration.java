package com.example.base.distribution.scheduling;

import org.springframework.context.annotation.Bean;

public class DistributionScheduledAutoConfiguration {

    @Bean
    public DistributionScheduledAspect distributionScheduledAspect(DistributionScheduledProperties properties){
        return new DistributionScheduledAspect(properties);
    }

    @Bean
    public DistributionScheduledProperties distributionScheduledProperties() {
        return new DistributionScheduledProperties();
    }

}
