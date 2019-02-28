package com.example.base.log.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtField;
import org.apache.ibatis.javassist.CtMethod;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

@Slf4j
public class RabbitMqPerformanceBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            ClassPool pool = ClassPool.getDefault();
            pool.importPackage("org.slf4j.Logger");
            pool.importPackage("org.slf4j.LoggerFactory");
            pool.importPackage("com.rabbitmq.client.impl.ChannelN");
            CtClass ctClass = pool.get("com.rabbitmq.client.impl.ChannelN");

            // 添加log字段
            CtField f = CtField.make("private static final Logger log = LoggerFactory.getLogger(ChannelN.class);", ctClass);
            ctClass.addField(f);

            // 修改消息发送方法
            CtClass[] params = new CtClass[6];
            params[0] = pool.get("java.lang.String");
            params[1] = pool.get("java.lang.String");
            params[2] = pool.get("boolean");
            params[3] = pool.get("boolean");
            params[4] = pool.get("com.rabbitmq.client.AMQP$BasicProperties");
            params[5] = pool.get("byte[]");
            CtMethod basicPublish = ctClass.getDeclaredMethod("basicPublish", params);
            basicPublish.setBody(basicPublish, null);
            basicPublish.insertBefore("{" +
                    "log.info(\"exchange = [{}], routingKey = [{}]\", $1, $2);" +
                    "}");

            // 重新加载
            ctClass.toClass();
        } catch (Exception e) {
            log.error("修改ChannelN字节码异常", e);
        }
    }

}
