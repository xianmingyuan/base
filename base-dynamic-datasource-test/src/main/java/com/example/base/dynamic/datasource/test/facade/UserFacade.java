package com.example.base.dynamic.datasource.test.facade;

import com.example.base.dynamic.datasource.annotation.DynamicDataSource;
import com.example.base.dynamic.datasource.test.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xianmingyuan
 */
@Slf4j
@Component
public class UserFacade {

    @Resource
    private UserDao userDao;

    @DynamicDataSource(name = "write")
    public void write1() {
        Integer result = userDao.insert(1, "张三");
        log.info("result = [{}]", result);
    }

    @DynamicDataSource(name = "read")
    public void write2() {
        Integer result = userDao.insert(1, "李四");
        log.info("result = [{}]", result);
    }

}
