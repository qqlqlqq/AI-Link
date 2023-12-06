package com.quan.ailink.utils;

import org.apache.shiro.crypto.hash.Md5Hash;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    /**
     * 针对密码进行加密操作
     */
    public static String md5(String password,String salt,Integer count) {
        return new Md5Hash(password,salt,count).toString();
//        return new SimpleHash("MD5",password,salt,count).toString();
    }

    public static String md5(String password,String salt) {
        return md5(password,salt,10).toString();
    }


}
