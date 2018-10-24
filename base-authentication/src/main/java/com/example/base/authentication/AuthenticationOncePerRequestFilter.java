package com.example.base.authentication;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author xianmingyuan
 */
public class AuthenticationOncePerRequestFilter extends OncePerRequestFilter {

    private AuthenticationProperties properties;

    public AuthenticationOncePerRequestFilter(AuthenticationProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(properties.getKey());
        TokenHolder.setToken(token);
        filterChain.doFilter(request, response);
    }
}
