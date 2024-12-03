package com.xiaopinyun.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface UploadService {
    /**
     * 文件上传
     *
     * @param file 上传的文件
     * @return 返回文件在服务器里的链接
     */
    public String uploadFile(MultipartFile file);


    /**
     * 下载文件
     *
     * @param filename 文件名
     * @return 返回该文件的输入流
     */
    public InputStream downloadFile(String filename);
}
