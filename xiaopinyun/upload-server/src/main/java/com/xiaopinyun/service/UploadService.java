package com.xiaopinyun.service;

import com.xiaopinyun.bean.dto.Result;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Mono;


public interface UploadService {

    /**
     * 文件上传
     */
    Mono<Result<String>> uploadFileAsync(FilePart file);

    /**
     * 下载文件
     */
    ResponseEntity<ByteArrayResource> downloadFile(String filename);

    /**
     * 预览文件
     */
    Result<String> getPreviewFileUrl(String filename);

    /**
     * 删除文件
     */
    Result<Void> deleteFile(String filename);

}
