package com.example.base.distribution.scheduling.annotation;

import com.example.base.distribution.scheduling.DistributionScheduledAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Import(DistributionScheduledAutoConfiguration.class)
public @interface EnableDistributionScheduled {
}
