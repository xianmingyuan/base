package com.example.base.authentication;

/**
 * @author xianmingyuan
 */
public class TokenHolder {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private TokenHolder(){}

    public static void setToken(String token) {
        threadLocal.set(token);
    }

    public static String getToken() {
        return threadLocal.get();
    }

}
