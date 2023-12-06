package com.quan.ailink.service.impl;

import com.quan.ailink.entity.*;
import com.quan.ailink.mapper.DepartmentUserMapper;
import com.quan.ailink.mapper.OfficeUserMapper;
import com.quan.ailink.mapper.RoleMapper;
import com.quan.ailink.service.RoleService;
import com.quan.ailink.vo.RoleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author LQL
 * @Create 2023/12/3 17:40
 * @Version 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private DepartmentUserMapper departmentUserMapper;

    @Autowired
    private OfficeUserMapper officeUserMapper;

    @Override
    @Cacheable(value = "quanredis", keyGenerator = "roleUidKeyGenerator")
    public List<RoleVO> selectRoleByUid(Long uid) {
        List<Long> roleIds = new ArrayList<>(); // 用户的角色id
        // 查看用户在部门中担任的角色
        DepartmentUserExample departmentUserExample = new DepartmentUserExample();
        departmentUserExample.createCriteria().andUidEqualTo(uid);
        List<DepartmentUser> departmentUsers = departmentUserMapper.selectByExample(departmentUserExample);
        if(departmentUsers != null && !departmentUsers.isEmpty()) {
            departmentUsers.forEach(d -> {
                if(!roleIds.contains(d.getRid())) {
                    roleIds.add(d.getRid());
                }
            });
        }
        // 查看用户在科室中担任的角色
        OfficeUserExample officeUserExample = new OfficeUserExample();
        officeUserExample.createCriteria().andUidEqualTo(uid);
        List<OfficeUser> officeUsers = officeUserMapper.selectByExample(officeUserExample);
        if(officeUsers != null && !officeUsers.isEmpty()) {
            officeUsers.forEach(o -> {
                if(!roleIds.contains(o.getRid())) {
                    roleIds.add(o.getRid());
                }
            });
        }
        // 通过roleId查询角色
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andRidIn(roleIds);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        if(roles != null && !roles.isEmpty()) {
            return roles.stream().map(r -> {
                RoleVO roleVO = new RoleVO();
                BeanUtils.copyProperties(r, roleVO);
                return roleVO;
            }).collect(Collectors.toList());
        }
        return null;
    }
}
