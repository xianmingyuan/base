package com.example.base.authentication.rest;

import com.example.base.authentication.AuthenticationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;

import java.io.IOException;

/**
 * @author xianmingyuan
 */
@Slf4j
public class AuthenticationClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    private AuthenticationProperties properties;

    public AuthenticationClientHttpRequestInterceptor(AuthenticationProperties properties) {
        this.properties = properties;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        log.info("改造请求头");
        HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
        requestWrapper.getHeaders().set(properties.getKey(), properties.getToken());
        return execution.execute(requestWrapper, body);
    }
}
