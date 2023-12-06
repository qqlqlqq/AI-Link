package com.quan.ailink.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectDTO {

    private String pname;

    private Date beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expectTime;

    private Date finishTime;

    private Long createUser;

    private List<Long> selectUsers;

    private Byte isCompany;

    private Byte isIdh;

    private String customerContact;

    private String remarks;

    private String introduce;

    private String path;


}
