package com.quan.ailink.service;

import com.quan.ailink.dto.UserDTO;
import com.quan.ailink.entity.User;
import com.quan.ailink.vo.MenuVO;
import com.quan.ailink.vo.UserVO;

import java.util.List;

/**
 * @Author LQL
 * @Create 2023/12/3 17:05
 * @Version 1.0
 */
public interface UserService {
    String login(UserDTO userDTO);

    User selectUserByUsername(String username);

    List<MenuVO> selectMenu();

    List<UserVO> selectSameDepartmentAndOffice();

}
