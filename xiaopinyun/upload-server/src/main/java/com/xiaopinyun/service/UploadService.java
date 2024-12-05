package com.xiaopinyun.service;

import com.xiaopinyun.pojo.vo.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface UploadService {

    /**
     * 文件上传
     */
    public Result<String> uploadFile(MultipartFile file);


    /**
     * 下载文件
     */

    /**
     * 预览文件
     */

    /**
     * 删除文件
     */

}
