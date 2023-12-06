package com.quan.ailink.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.quan.ailink.dto.UserDTO;
import com.quan.ailink.service.UserService;
import com.quan.ailink.utils.Result;
import com.quan.ailink.utils.StringUtil;
import com.quan.ailink.vo.MenuVO;
import com.quan.ailink.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author LQL
 * @Create 2023/12/2 21:26
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/user")
@Tag(name = "测试Controller", description = "这是描述")
public class UserController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @GetMapping("kaptcha")
    public Result kaptcha(HttpServletResponse response) {
        String text = defaultKaptcha.createText();
        log.debug("验证码：" + text);
        BufferedImage image = defaultKaptcha.createImage(text);
        // 生成key
        String key = StringUtil.uuid();
        redisTemplate.opsForValue().set(key,text,5, TimeUnit.MINUTES);
        response.setHeader("token", key); // 响应给客户端
        String s = StringUtil.changeImage2String(image);
        return Result.success("获取验证码成功", s);
    }

    @PostMapping("login")
    @Operation(summary = "测试接口")
    public Result login(@Parameter(name = "name", description = "名称") @RequestBody @Validated UserDTO userDTO, @RequestHeader("token") String key, HttpServletResponse response) {
        String text = (String) redisTemplate.opsForValue().get(key);
        if(StringUtil.isBlank(text)) {
            return Result.error("验证码过期");
        }
        // 先删除redis中的验证码
        redisTemplate.delete(key);
        if(!userDTO.getCaptcha().equalsIgnoreCase(text)) {
            return Result.error("验证码错误");
        }
        String token = userService.login(userDTO);
        response.setHeader("token",token);
        return Result.success("登录成功", SecurityUtils.getSubject().getPrincipal());
    }


    @GetMapping("/menu")
    public Result menu() {
        List<MenuVO> menuVOS = userService.selectMenu();
        return Result.success("查询成功",menuVOS);
    }

    @GetMapping("getUserByDepartment")
    public Result getUserByDepartment() {
        List<UserVO> userVOS = userService.selectSameDepartmentAndOffice();
        return Result.success("查询成功",userVOS);
    }


}
