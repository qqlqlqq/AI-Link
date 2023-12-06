package com.quan.ailink.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PermissionVO {
    private Long pid;

    private String pname;

    private String pdesc;

    private Byte levels;

    private Long parentId;

    private String parent;

    private String icon;

    private String href;

    private String target;

    private Byte type;

    private Byte state;
}
