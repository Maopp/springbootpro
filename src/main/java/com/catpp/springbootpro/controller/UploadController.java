package com.catpp.springbootpro.controller;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * com.catpp.springbootpro.controller
 *
 * @Author cat_pp
 * @Date 2018/10/18
 * @Description 文件上传
 */
@Slf4j
@Controller
public class UploadController {

    /**
     * 文件上传路径
     */
    @Value("${file.upload.path}")
    private String uploadDir;
    /**
     * 文件大小
     */
    @Value("${file.max.size}")
    private String fileMaxSize;

    /**
     * 单文件上传
     * @param request
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String upload(HttpServletRequest request, MultipartFile file) {
        // 上传目录地址
        // String uploadDir = request.getSession().getServletContext().getRealPath("/") + "upload/";

        // 如果目录不存在，创建目录
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        try {
            uploadFile(file);
        } catch (IOException e) {
            log.error("上传失败，错误信息：{}", e.getMessage());
            return "上传失败";
        }
        return "上传成功";
    }

    private void uploadFile(MultipartFile file) throws IOException {
        // 文件大小
        long size = file.getSize();
        Preconditions.checkArgument(size <= Long.parseLong(fileMaxSize), "文件太大啦");
        // 文件后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 上传文件名
        String fileName = UUID.randomUUID().toString().replace("-", "") + suffix;
        // 服务器端保存的文件对象
        File serverFile = new File(uploadDir + fileName);
        // 将上传的文件写入到服务器端文件内
        file.transferTo(serverFile);
    }

    /**
     * 多文件上传
     * @param file
     * @return
     */
    @RequestMapping("/uploads")
    @ResponseBody
    public String uploads(MultipartFile[] file) {
        // 如果目录不存在，创建目录
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdir();
        }
        // 遍历上传
        for (MultipartFile multipartFile : file) {
            try {
                uploadFile(multipartFile);
            } catch (IOException e) {
                log.error("上传失败，文件名：{}，错误信息：{}", multipartFile.getOriginalFilename(), e.getMessage());
                return "上传失败";
            }
        }
        return "上传成功";
    }
}
