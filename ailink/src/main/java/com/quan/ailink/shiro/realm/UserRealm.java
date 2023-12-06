package com.quan.ailink.shiro.realm;

import com.quan.ailink.entity.User;
import com.quan.ailink.service.PermissionService;
import com.quan.ailink.service.RoleService;
import com.quan.ailink.service.UserService;
import com.quan.ailink.shiro.token.JWTToken;
import com.quan.ailink.utils.JWTUtil;
import com.quan.ailink.utils.StringUtil;
import com.quan.ailink.vo.ActiveUser;
import com.quan.ailink.vo.PermissionVO;
import com.quan.ailink.vo.RoleVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author LQL
 * @Create 2023/12/2 21:28
 * @Version 1.0
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public String getName() {
        return "UserRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
      return null;
    }


    /**
     * 认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        if(StringUtil.isBlank(token)) {
            throw new RuntimeException("token无效1");
        }
        String username = JWTUtil.getUsername(token);
        if(username == null) {
            throw new RuntimeException("token无效2");
        }
        User user = userService.selectUserByUsername(username);
        if(user == null) {
            throw new RuntimeException("token无效3");
        }
        if(!JWTUtil.verify(token,username,user.getPassword())) {
            throw new RuntimeException("token无效4");
        }
        ActiveUser activeUser = new ActiveUser();
        BeanUtils.copyProperties(user,activeUser);
        // 查询角色
        List<RoleVO> roleVOS = roleService.selectRoleByUid(activeUser.getUid());
        // 查询权限
        List<Long> collect = roleVOS.stream().map(r -> {
            return r.getRid();
        }).collect(Collectors.toList());
        List<PermissionVO> permissionVOS = permissionService.selectPermissionByRoleIds(collect);
        // 判断是否有登录权限
        boolean b = permissionVOS.stream().anyMatch(p ->  p.getPdesc().equals("user:system:login"));
        if(!b) {
            throw new RuntimeException("当前用户没有登录权限");
        }
        activeUser.setRoleVOS(roleVOS);
        activeUser.setPermissionVOS(permissionVOS);
        return new SimpleAuthenticationInfo(activeUser,token,getName());
    }
}
