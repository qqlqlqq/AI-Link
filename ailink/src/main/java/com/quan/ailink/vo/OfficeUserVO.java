package com.quan.ailink.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OfficeUserVO {
    private Long duid;

    private Long uid;

    private OfficeVO officeVO;

    private RoleVO roleVO;
}
