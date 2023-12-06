package com.quan.ailink.service;

import com.quan.ailink.vo.RoleVO;

import java.util.List;

/**
 * @Author LQL
 * @Create 2023/12/3 17:40
 * @Version 1.0
 */
public interface RoleService {
    List<RoleVO> selectRoleByUid(Long uid);
}
