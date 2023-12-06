package com.quan.ailink.utils;


import com.alibaba.fastjson2.JSON;
import javax.imageio.ImageIO;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;


public class StringUtil {

    private static List<String> resumeFileTypes = Arrays.asList("application/msword,application/pdf,application/vnd.openxmlformats-officedocument.wordprocessingml.document".split(","));

    private static List<String> imageFileTypes = Arrays.asList("image/jpeg,image/png,image/jpg".split(","));

    /**
     * 判断是否需要的简历类型
     * @param mime
     * @return
     */
    public static boolean isResumeFile(String mime){
        return resumeFileTypes.contains(mime);
    }

    /**
     * 判断是否是图片类型
     * @param mime
     * @return
     */
    public static boolean isCompanyFile(String mime){
        return imageFileTypes.contains(mime);
    }


    /**
     * 判断一个字符串是否为空
     * @param str
     * @return
     */
    public static boolean isBlank(String str){
        return str == null || "".equals(str);
    }

    /**
     * 判断一个字符串不为空
     * @param str
     * @return
     */
    public static boolean isNotNull(String str){
        return str != null && !"".equals(str);
    }

    private static String str = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 生成一个指定长度的随机字符串
     * @param len
     * @return
     */
    public static String randomStr(int len){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int index = (int) (Math.random() * str.length());
            stringBuilder.append(str.charAt(index));
        }
        return stringBuilder.toString();
    }

    private static String numberStr = "1234567890";

    /**
     * 生成一个随机的数字
     * @param len
     * @return
     */
    public static String randomNumber(int len){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int index = (int) (Math.random() * numberStr.length());
            stringBuilder.append(numberStr.charAt(index));
        }
        return stringBuilder.toString();
    }

    /**
     * 生成一个唯一的字符串uuid
     * @return
     */
    public static String uuid(){
        return UUID.randomUUID().toString();
    }

    /**
     * 格式化时间
     * @param date
     * @return
     */
    public static String formatDate(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 获取当前时间的字符串
     * @return
     */
    public static String getCurrentTime(){
        return formatDate(new Date());
    }



    /**
     * 将图片加密到base64的字符串
     * @param bufferedImage
     * @return
     */
    public static String changeImage2String(BufferedImage bufferedImage){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage,"jpg",bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(bos.toByteArray());
    }

    /**
     * 从请求中接收json参数
     * @param request
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseJSONFromRequest(HttpServletRequest request, Class<T> clazz) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        InputStreamReader isr = new InputStreamReader(inputStream,"UTF-8");// 字节转字符流
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder stringBuilder = new StringBuilder();
        String str = null;
        while ((str = bufferedReader.readLine()) != null){
            stringBuilder.append(str);
        }
        bufferedReader.close();
        isr.close();
        inputStream.close();
        return JSON.parseObject(stringBuilder.toString(),clazz);// 把json转换成对象

    }
}
