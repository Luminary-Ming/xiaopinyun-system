package com.xiaopinyun.controller;

import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.service.UploadService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /**
     * 文件上传
     */
    @PostMapping
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return uploadService.uploadFile(file);
    }

    /**
     * 下载文件
     */
    @PostMapping("/{filename}")
    public Result<Void> downloadFile(@PathVariable String filename, HttpServletResponse response) {
        return uploadService.downloadFile(filename, response);
    }

    /**
     * 文件预览
     */
    @GetMapping("/{filename}")
    public Result<String> previewFile(@PathVariable String filename) {
        return uploadService.getPreviewFileUrl(filename);
    }

    /**
     * 删除文件
     */
    @DeleteMapping("/{filename}")
    public Result<Void> deleteFile(@PathVariable String filename) {
        return uploadService.deleteFile(filename);
    }
}