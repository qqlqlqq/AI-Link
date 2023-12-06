package com.quan.ailink.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OfficeVO {
    private Long oid;

    private String oname;

    private DepartmentVO departmentVO;

    private Byte state;
}
