package com.quan.ailink.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 统一响应结果封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Result {

    private Integer code ;//0成功 , 1 失败

    private String msg; //提示信息

    private Object data; //数据 data


    public static Result success(Object data){
        return new Result(0, "success", data);
    }
    public static Result success(String msg, Object data){
        return new Result(0, msg, data);
    }
    public static Result success(){
        return new Result(0, "success", null);
    }
    public static Result error(String msg){
        return new Result(1, msg, null);
    }

    public static Result notLogin() {
        return new Result(2, "未登录", null);
    }

    public static Result notLogin(String msg) {
        return new Result(2, msg, null);
    }

}
