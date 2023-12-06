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
public class MenuVO {
    private Long pid;

    private String pname;

    private String pdesc;

    private Byte levels;

    private Long parentId;

    private String href;

    private List<MenuVO> children;
}
