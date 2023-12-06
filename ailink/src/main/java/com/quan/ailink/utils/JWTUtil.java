package com.quan.ailink.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;


public class JWTUtil {

    private static final long EXP = 24 * 60 * 60 * 1000;//过期时间

    /**
     * 生成token
     * @param username  账号信息
     * @param secret  秘钥
     * @return 生成的token
     */
    public static String sign(String username,String secret){
        Date date = new Date(System.currentTimeMillis() + EXP);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        //附带username信息的token
        return JWT.create().withClaim("username",username).withExpiresAt(date).sign(algorithm);
    }

    /**
     * 从token中获取账号
     * @param token
     * @return
     */
    public static String getUsername(String token){
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("username").asString();
        }catch (JWTDecodeException e){
            return null;
        }
    }

    /**
     * 校验token是否正确
     * @param token
     * @param username
     * @param secret
     * @return
     */
    public static boolean verify(String token,String username,String secret){
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withClaim("username", username).build();
            DecodedJWT verify = jwtVerifier.verify(token);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * 检查token是否过期
     * @param token
     * @return
     */
    public static boolean isExpires(String token){
        return System.currentTimeMillis() > JWT.decode(token).getExpiresAt().getTime();
    }
/*

    public static void main(String[] args) {
        String zhangsan = sign("zhangsan", "123456");
        System.out.println(zhangsan);
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NzUzMDU1NDUsInVzZXJuYW1lIjoiemhhbmdzYW4ifQ.KmXiCvyP-2tZPhCovsB3dlzXeV4kql_NQOQCyyihlfs";
        String username = getUsername(zhangsan);
        System.out.println(username);
        boolean b = verify(token, "zhangsan1", "123456");
        System.out.println(b);
    }
*/

}
