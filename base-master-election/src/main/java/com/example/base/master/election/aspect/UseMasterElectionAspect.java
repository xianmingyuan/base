package com.example.base.master.election.aspect;

import com.example.base.master.election.MasterElection;
import com.example.base.master.election.MasterElectionProperties;
import com.example.base.master.election.annotation.UseMasterElection;
import com.example.base.master.election.exception.NoMasterException;
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

/**
 * @author xianmingyuan
 */
@Slf4j
@Aspect
@Component
public class UseMasterElectionAspect implements ApplicationContextAware {

    private ConfigurableApplicationContext context;

    private MasterElectionProperties properties;

    public UseMasterElectionAspect(MasterElectionProperties properties) {
        this.properties = properties;
    }

    @Around("@annotation(selection)")
    public Object proceed(ProceedingJoinPoint jp, UseMasterElection selection) {
        String path = selection.path();
        String value = selection.value();
        MasterElection election;
        try {
            election = (MasterElection) context.getBean(path);
        } catch (NoSuchBeanDefinitionException e) {
            //实例化一个ZookeeperMasterElection
            log.info("正在实例化ZookeeperMasterElection");
            context.getBeanFactory()
                    .registerSingleton(path, new MasterElection(properties.getAddress(), properties.getTimeout(), path, value));
            election = (MasterElection) context.getBean(path);
        }
        if (election.isMaster()) {
            try {
                return jp.proceed();
            } catch (Throwable throwable) {
                log.error("进行master判断后，执行方法异常", throwable);
            }
        }
        throw new NoMasterException();
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = (ConfigurableApplicationContext) context;
    }
}
