package com.xiaopinyun.service;

import com.xiaopinyun.bean.vo.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;


public interface UploadService {

    /**
     * 文件上传
     */
    public Result<String> uploadFile(MultipartFile file);

    /**
     * 下载文件
     */
    Result<Void> downloadFile(String filename, HttpServletResponse response);

    /**
     * 预览文件
     */
    Result<String> getPreviewFileUrl(String filename);

    /**
     * 删除文件
     */
    Result<Void> deleteFile(String filename);

}
