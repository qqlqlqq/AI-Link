package com.quan.ailink.controller;

import com.quan.ailink.utils.Result;
import com.quan.ailink.utils.StringUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author LQL
 * @Create 2023/12/5 13:13
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("upload")
public class UploadController {

    @PostMapping
    public Result upload(@RequestParam("file") MultipartFile file) throws IOException {
        //处理文件上传
        String originalFilename = file.getOriginalFilename();//获取文件的真实名称
        //设置保存的地方
        String path = "http://localhost:8080/upload/files/" + StringUtil.uuid() + originalFilename.substring(originalFilename.lastIndexOf("."));
        log.debug("上传的文件路径为：{}",path);
        //开始处理文件上传
        Client client = Client.create();
        //连接服务器
        WebResource resource = client.resource(path);
        //执行上传
        resource.post(file.getBytes());
        return Result.success("上传成功",path);
    }
}
