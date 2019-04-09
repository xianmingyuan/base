package com.example.base.distribution.lock;

import com.example.base.distribution.lock.annotation.DistributionLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.ZooKeeper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class DistributionLockAspect {

    private static final String DEFAULT_PATH = "";
    private static final String START_CHAR = "/";
    private ZooKeeper zooKeeper;

    public DistributionLockAspect(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    @Around("@annotation(an)")
    public Object proceed(ProceedingJoinPoint jp, DistributionLock an) throws Throwable {
        String name = jp.getSignature().toString();
        String path = an.path();
        if (DEFAULT_PATH.equals(path)) {
            path = START_CHAR + name.substring(name.indexOf(' '), name.indexOf('('));
        }
        Lock lock = new Lock(zooKeeper);
        try {
            if (lock.tryLock(path)) {
                return jp.proceed();
            }
            return null;
        } finally {
            lock.close();
        }
    }

}
