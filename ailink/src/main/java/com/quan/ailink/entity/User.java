package com.quan.ailink.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Long uid;

    private String userNo;

    private String username;

    private String password;

    private String email;

    private String salt;

    private String nickname;

    private Byte state;

    private Byte enabled;


}