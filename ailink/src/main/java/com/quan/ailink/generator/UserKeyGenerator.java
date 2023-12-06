package com.quan.ailink.generator;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author LQL
 * @Create 2023/12/3 17:15
 * @Version 1.0
 */
@Component
public class UserKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder stringBuilder = new StringBuilder("user:username:");
        stringBuilder.append(params[0]);
        return stringBuilder.toString();
    }
}
