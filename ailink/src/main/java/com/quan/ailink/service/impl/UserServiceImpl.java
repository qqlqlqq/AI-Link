package com.quan.ailink.service.impl;

import com.quan.ailink.dto.UserDTO;
import com.quan.ailink.entity.User;
import com.quan.ailink.entity.UserExample;
import com.quan.ailink.mapper.UserMapper;
import com.quan.ailink.service.UserService;
import com.quan.ailink.shiro.token.JWTToken;
import com.quan.ailink.utils.ArraysToTreeUtil;
import com.quan.ailink.utils.JWTUtil;
import com.quan.ailink.utils.MD5Util;
import com.quan.ailink.vo.ActiveUser;
import com.quan.ailink.vo.MenuVO;
import com.quan.ailink.vo.PermissionVO;
import com.quan.ailink.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.lang.invoke.CallSite;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author LQL
 * @Create 2023/12/3 17:05
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    @Lazy
    private UserService userService;

    @Override
    public String login(UserDTO userDTO) {
        User user = userService.selectUserByUsername(userDTO.getUsername());
        if(user == null) {
            throw new RuntimeException("账号或密码错误");
        }
        String s = MD5Util.md5(userDTO.getPassword(), user.getSalt());
        if(!s.equals(user.getPassword())) {
            throw new RuntimeException("账号或密码错误");
        }
        String token = JWTUtil.sign(userDTO.getUsername(), user.getPassword());
        SecurityUtils.getSubject().login(new JWTToken(token));
        return token;
    }

    @Override
    @Cacheable(value = "quanredis", keyGenerator = "userKeyGenerator")
    public User selectUserByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        return users == null || users.isEmpty() ? null : users.get(0);
    }

    @Override
    public List<MenuVO> selectMenu() {
        ActiveUser principal = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        List<PermissionVO> permissionVOS = principal.getPermissionVOS();
        List<MenuVO> collect = permissionVOS.stream().filter(p -> p.getType().equals((byte) 1) && p.getState().equals((byte) 0))
                .map(p -> {
                    MenuVO menuVO = new MenuVO();
                    BeanUtils.copyProperties(p, menuVO);
                    return menuVO;
                })
                .collect(Collectors.toList());

        List<MenuVO> tree = new ArraysToTreeUtil<MenuVO>()
                .code(MenuVO::getPid)
                .parent(MenuVO::getParentId)
                .children(menuVO -> {
                    List<MenuVO> children = menuVO.getChildren();
                    if (children == null) {
                        menuVO.setChildren(new ArrayList<>());
                    }
                    return menuVO.getChildren();
                }).tree(collect);
        return tree;
    }

    @Override
    public List<UserVO> selectSameDepartmentAndOffice() {
        ActiveUser principal = (ActiveUser) SecurityUtils.getSubject().getPrincipal();
        List<User> users = userMapper.selectSameDepartmentAndOfficeByUid(principal.getUid());
        return users.stream().map(u -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(u,userVO);
            return userVO;
        }).collect(Collectors.toList());
    }
}
