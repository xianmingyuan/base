package com.example.base.distribution.lock.annotation;

import com.example.base.distribution.lock.DistributionLockAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Import(DistributionLockAutoConfiguration.class)
public @interface EnableDistributionLock {
}
