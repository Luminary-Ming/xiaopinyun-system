package com.xiaopinyun.controller;

import com.xiaopinyun.service.impl.UploadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadServiceImpl uploadServiceImpl;

    @PostMapping
    public String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        String filePath = uploadServiceImpl.uploadFile(file);
        return "上传成功 ->" + filePath;
    }

    @GetMapping("/{filename}")
    public String download(@PathVariable String filename) {
        uploadServiceImpl.downloadFile(filename);

        return "下载成功";
    }
}