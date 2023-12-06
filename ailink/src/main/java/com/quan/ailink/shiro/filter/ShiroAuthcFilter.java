package com.quan.ailink.shiro.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quan.ailink.shiro.token.JWTToken;
import com.quan.ailink.utils.Result;
import com.quan.ailink.utils.StringUtil;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;



/**
 * @Author LQL
 * @Create 2023/12/2 21:35
 * @Version 1.0
 */
@Slf4j
public class ShiroAuthcFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest req, ServletResponse resp) throws Exception {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String requestURI = request.getRequestURI();
        log.debug("当前拦截的路径是：" + requestURI);
        // 拿token
        String token = request.getHeader("token");
        if(StringUtil.isNotNull(token)) {
            try {
                SecurityUtils.getSubject().login(new JWTToken(token));
                return true;
            }catch (RuntimeException e) {
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(new ObjectMapper().writeValueAsString(Result.notLogin(e.getMessage())));
                return false;
            }
        }else {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(new ObjectMapper().writeValueAsString(Result.notLogin()));
            return false;
        }
    }


    protected boolean preHandle(ServletRequest req, ServletResponse resp) throws Exception {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String method = request.getMethod();
        //设置响应头
        //设置哪些地方的请求可以跨域 * 表示任意请求  多个值可以用，隔开
        response.setHeader("Access-Control-Allow-Origin", "*");
        //是否允许携带cookie
        response.setHeader("Access-Control-Allow-Credentials", "false");
        //允许的请求方式
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD, PUT");
        // 允许的请求头
        response.setHeader("Access-Control-Allow-Headers", "Origin, x-requested-with, authorization, Content-Type, token, Accept,Connection,User-Agent,Cookie ");
        // 允许浏览器获取的响应头
        response.setHeader("Access-Control-Expose-Headers", "token");
        // 超时时间
        response.setHeader("Access-Control-Max-Age", "3600");
        if ("OPTIONS".equalsIgnoreCase(method)) {
            return false;
        }else {
            return super.preHandle(request, response);
        }
    }
}
