package com.example.base.zookeeper.master.annotation;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface UseZookeeperMasterElection {

    String name();

}
