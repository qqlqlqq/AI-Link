package com.quan.ailink.service.impl;

import com.quan.ailink.entity.Permission;
import com.quan.ailink.mapper.PermissionMapper;
import com.quan.ailink.service.PermissionService;
import com.quan.ailink.vo.PermissionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author LQL
 * @Create 2023/12/3 17:40
 * @Version 1.0
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    @Cacheable(value = "quanredis", keyGenerator = "permissionRidsKeyGenerator")
    public List<PermissionVO> selectPermissionByRoleIds(List<Long> rids) {
        List<Permission> permissions = permissionMapper.selectPermissionByRids(rids);
        return permissions.stream().map(p -> {
            PermissionVO permissionVO = new PermissionVO();
            BeanUtils.copyProperties(p,permissionVO);
            return permissionVO;
        }).collect(Collectors.toList());
    }
}
