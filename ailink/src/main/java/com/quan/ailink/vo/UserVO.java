package com.quan.ailink.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserVO {
    private Long uid;

    private String userNo;

    private String username;

    private String email;

    private String nickname;

    private Byte state;

    private Byte enabled;
}
