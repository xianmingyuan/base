package com.example.base.dynamic.datasource.test.service;

import com.example.base.dynamic.datasource.test.facade.UserFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xianmingyuan
 */
@Slf4j
@Service
public class UserService {

    private UserFacade userFacade;

    public UserService(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public void write(){
        userFacade.write1();
        userFacade.write2();
    }

}
