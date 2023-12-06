package com.quan.ailink.shiro.token;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author LQL
 * @Create 2023/12/2 21:34
 * @Version 1.0
 */
public class JWTToken implements AuthenticationToken {

    private String token;

//    public void setToken(String token) {
//        this.token = token;
//    }

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
