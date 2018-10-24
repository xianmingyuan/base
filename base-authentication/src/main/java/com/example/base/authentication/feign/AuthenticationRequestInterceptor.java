package com.example.base.authentication.feign;

import com.example.base.authentication.AuthenticationProperties;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * @author xianmingyuan
 */
public class AuthenticationRequestInterceptor implements RequestInterceptor {

    private AuthenticationProperties properties;

    public AuthenticationRequestInterceptor(AuthenticationProperties properties) {
        this.properties = properties;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header(properties.getKey(), properties.getToken());
    }

}
