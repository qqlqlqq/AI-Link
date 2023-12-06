package com.quan.ailink.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ActiveUser {
    private Long uid;

    private String userNo;

    private String username;

    private String email;

    private String nickname;

    private Byte state;

    private Byte enabled;

    private List<RoleVO> roleVOS;

    private List<PermissionVO> permissionVOS;

    private List<DepartmentUserVO> departmentUserVOS;

    private List<OfficeUserVO> officeUserVOS;
}
