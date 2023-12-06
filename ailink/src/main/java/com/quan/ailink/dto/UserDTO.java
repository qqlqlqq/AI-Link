package com.quan.ailink.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * @Author LQL
 * @Create 2023/12/3 15:43
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {

    @NotNull(message = "账号不能为空")
    @Length(message = "账号必须在{min}到{max}之间",min = 3,max = 18)
    private String username;

    @NotNull(message = "密码不能为空")
    @Length(message = "密码必须在{min}到{max}之间",min = 6,max = 18)
    private String password;

    @NotBlank(message = "验证码不能为空")
    private String captcha;
}
