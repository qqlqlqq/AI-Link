package com.quan.ailink.generator;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author LQL
 * @Create 2023/12/3 17:15
 * @Version 1.0
 */
@Component
public class PermissionRidsKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder stringBuilder = new StringBuilder("permission:rid");
        List<Long> rids = (List<Long>) params[0];
        rids.forEach(r -> {
            stringBuilder.append(":");
            stringBuilder.append(r);
        });
        return stringBuilder.toString();
    }
}
