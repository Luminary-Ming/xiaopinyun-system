package com.xiaopinyun.controller;

import com.xiaopinyun.pojo.vo.Result;
import com.xiaopinyun.service.UploadService;
import com.xiaopinyun.service.impl.UploadServiceImpl;
import com.xiaopinyun.util.BizCode;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private UploadServiceImpl uploadService;

    /**
     * 文件上传
     */
    @PostMapping
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return uploadService.uploadFile(file);
    }

    /**
     * 文件预览

    @GetMapping("/getRedFile")
    public String getRedFile(@RequestParam String filename) {
        return uploadService.getPreviewFileUrl(filename);
    }
     */

    /**
     * 下载文件

    @GetMapping("/download/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) {
        byte[] data = uploadService.downloadFile(filename);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(data);
    }
     */
    /**
     * 删除文件

    @GetMapping("/deleteFile/{filename}")
    public String deleteFile(@PathVariable String filename) {
        return uploadService.deleteFile(filename);
    }
     */
}