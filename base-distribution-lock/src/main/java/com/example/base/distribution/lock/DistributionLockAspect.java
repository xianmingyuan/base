package com.example.base.distribution.lock;

import com.example.base.distribution.lock.annotation.DistributionLock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author xianmingyuan
 */
@Slf4j
@Aspect
public class DistributionLockAspect implements ApplicationContextAware {

    private ConfigurableApplicationContext context;
    private DistributionLockProperties properties;
    private static final String DEFAULT_PATH = "";
    private static final String START_CHAR = "/";

    public DistributionLockAspect(DistributionLockProperties properties) {
        this.properties = properties;
    }

    @Around("@annotation(an)")
    public Object proceed(ProceedingJoinPoint jp, DistributionLock an) throws Throwable {
        String name = jp.getSignature().toString();
        Lock lock;
        try {
            lock = (Lock) context.getBean(name);
        } catch (NoSuchBeanDefinitionException e) {
            //实例化一个ZookeeperMasterElection
            log.info("正在实例化ZookeeperMasterElection");
            String path = an.path();
            if (DEFAULT_PATH.equals(path)) {
                path = START_CHAR + name.substring(name.indexOf(' '), name.indexOf('('));
            }
            lock = new Lock(properties.getAddress(), properties.getTimeout(), path);
            context.getBeanFactory().registerSingleton(name, lock);
        }

        if (lock.hasLocked() && lock.isOpen()) {
            jp.proceed();
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = (ConfigurableApplicationContext) context;
    }
}
