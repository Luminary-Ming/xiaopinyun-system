package com.xiaopinyun.controller;

import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.config.MinIOConfig;
import com.xiaopinyun.service.UploadService;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;
    @Autowired
    private MinIOConfig minIOConfig;
    @Autowired
    private MinioClient minioClient;

    /**
     * 文件上传
     */
    @PostMapping
    public Mono<Result<String>> uploadFile(@RequestPart("file") FilePart file) {
        return uploadService.uploadFileAsync(file);
    }

    /**
     * 下载文件
     */
    @GetMapping("/download/{filename}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String filename) {
        return uploadService.downloadFile(filename);
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