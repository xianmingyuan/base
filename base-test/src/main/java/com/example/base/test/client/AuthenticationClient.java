package com.example.base.test.client;

import com.example.base.util.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("authentication")
public interface AuthenticationClient {

    @GetMapping("test")
    Response test();

}
