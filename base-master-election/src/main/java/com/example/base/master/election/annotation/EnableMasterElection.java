package com.example.base.master.election.annotation;

import com.example.base.master.election.MasterElectionAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Import(MasterElectionAutoConfiguration.class)
public @interface EnableMasterElection {
}
