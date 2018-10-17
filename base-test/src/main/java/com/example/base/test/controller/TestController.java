package com.example.base.test.controller;


import com.example.base.util.PageResponse;
import com.example.base.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class TestController {

    @GetMapping("test/test1")
    public Response<String> test(){
        log.info("TestController.sleuthTest");
        return Response.success("success");
    }
    @GetMapping("test/test2")
    public Response sleuthTest(){
        return Response.success();
    }
    @GetMapping("test/test3")
    public Response fail(){
        return Response.fail(701, "系统错误");
    }


    @GetMapping("test/test4")
    public PageResponse<String> test1(){
        log.info("TestController.sleuthTest");
        List<String> value = new ArrayList<>();
        return PageResponse.success(value, 1, 0, 0);
    }
    @GetMapping("test/test5")
    public PageResponse test2(){
        return PageResponse.success();
    }
    @GetMapping("test/test6")
    public PageResponse fail1(){
        return PageResponse.fail(701, "系统错误");
    }

}
