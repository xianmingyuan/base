package com.example.base.zookeeper.master.aspect;

import com.example.base.zookeeper.master.ZookeeperMasterElection;
import com.example.base.zookeeper.master.ZookeeperMasterElectionProperties;
import com.example.base.zookeeper.master.annotation.UseZookeeperMasterElection;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class UseZookeeperMasterElectionAspect implements ApplicationContextAware {

    private ConfigurableApplicationContext context;

    private ZookeeperMasterElectionProperties properties;

    public UseZookeeperMasterElectionAspect(ZookeeperMasterElectionProperties properties) {
        this.properties = properties;
    }

    @Around("@annotation(selection)")
    public Object proceed(ProceedingJoinPoint jp, UseZookeeperMasterElection selection) {
        ZookeeperMasterElection election;
        try {
            election = (ZookeeperMasterElection) context.getBean(selection.name());
        } catch (NoSuchBeanDefinitionException e) {
            //实例化一个ZookeeperMasterElection
            log.info("正在实例化ZookeeperMasterElection");
            String path = selection.name();
            String value = selection.name();
            context.getBeanFactory()
                    .registerSingleton(selection.name(), new ZookeeperMasterElection(properties.getAddress(), properties.getTimeout(), path, value));
            election = (ZookeeperMasterElection) context.getBean(selection.name());
        }
        log.info("election = {}", election);
        if (election.isMaster()) {
            try {
                return jp.proceed();
            } catch (Throwable throwable) {
                log.error("进行master判断后，执行方法异常", throwable);
            }
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = (ConfigurableApplicationContext) context;
    }
}
