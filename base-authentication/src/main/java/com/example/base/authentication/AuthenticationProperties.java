package com.example.base.authentication;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Data
public class AuthenticationProperties {

    @Value("${authentication.key:authentication}")
    private String key;
    @Value("${authentication.token:test}")
    private String token;

}
