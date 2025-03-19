package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.config.MinIOConfig;
import com.xiaopinyun.service.UploadService;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.http.Method;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private MinIOConfig minIOConfig;
    @Autowired
    private MinioClient minioClient;

    /**
     * 文件上传
     */
    public Result<String> uploadFile(MultipartFile file) {
        // 获取上传的文件名
        String filename = file.getOriginalFilename();
        // 如果文件名为空
        if (StringUtils.isBlank(filename)) {
            return Result.paramError("文件名不能为空");
        }
        // 获取文件扩展名
        String ext = filename.substring(filename.lastIndexOf("."));
        // 获取文件名
        filename = filename.substring(0, filename.lastIndexOf("."));
        // 日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String today = dateFormat.format(new Date());
        // 获得处理过的上传文件名
        filename = filename + "_" + today + ext;
        // 获得流对象
        try (InputStream in = file.getInputStream()) {
            // 上传到 MinIO 服务器的 Bucket 存储桶里
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minIOConfig.getBucketName())
                            .object(filename)
                            .stream(in, file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());
            // 返回文件在服务器里的链接
            String fileURL = minIOConfig.getEndpoint() + "/" + minIOConfig.getBucketName() + "/" + filename;
            return Result.ok(fileURL, "文件上传成功");
        } catch (Exception e) {
            return Result.error("文件上传失败");
        }
    }

    /**
     * 文件下载
     */
    public Result<Void> downloadFile(String filename, HttpServletResponse response) {
        // 如果文件名为空
        if (StringUtils.isBlank(filename)) {
            return Result.paramError("文件名不能为空");
        }
        // 如果文件在桶中不存在
        if (!fileIsExist(filename)) {
            return Result.error("文件不存在");
        }
        // 配置响应头
        response.setHeader("Content-Disposition", "attachment; filename=" + filename);
        response.setContentType("application/octet-stream");
        /*
            try-with-resources 语句，JDK7加入的
            当 try 中代码执行结束后（正常结束或者异常结束），都会调用 try() 括号中对象的close()方法来关闭资源
        */
        try (InputStream inputStream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(minIOConfig.getBucketName())
                        .object(filename).build());
             ServletOutputStream outputStream = response.getOutputStream()) {
            // 创建缓冲区，用于临时存储从输入流中读取的数据
            byte[] buffer = new byte[1024];
            int length;
            // 读取数据
            while ((length = inputStream.read(buffer)) != -1) {
                // 写入数据
                outputStream.write(buffer, 0, length);
            }
            return Result.ok("文件下载成功");
        } catch (Exception e) {
            return Result.error("文件下载失败");
        }
    }


    /**
     * 根据文件路径得到预览文件绝对地址
     */
    public Result<String> getPreviewFileUrl(String filename) {
        // 如果文件名为空
        if (StringUtils.isBlank(filename)) {
            return Result.error("文件名不能为空");
        }
        // 如果文件在桶中不存在
        if (!fileIsExist(filename)) {
            return Result.error("文件不存在");
        }
        String previewFileUrl = null;
        try {
            // getPresignedObjectUrl 获得文件预览地址
            previewFileUrl = minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Method.GET)
                            .bucket(minIOConfig.getBucketName())
                            .object(filename).build());
        } catch (Exception e) {
            return Result.error("文件预览失败");
        }
        return Result.ok(previewFileUrl, "文件预览成功");
    }

    /**
     * 删除文件
     */
    public Result<Void> deleteFile(String filename) {
        // 如果文件名为空
        if (StringUtils.isBlank(filename)) {
            return Result.paramError("文件名不能为空");
        }
        // 如果文件在桶中不存在
        if (!fileIsExist(filename)) {
            return Result.error("文件不存在");
        }
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(minIOConfig.getBucketName())
                            .object(filename)
                            .build());
            return Result.ok("文件删除成功");
        } catch (Exception e) {
            return Result.error("文件删除失败");
        }
    }

    /**
     * 判断文件在桶中是否存在
     */
    public boolean fileIsExist(String filename) {
        // 使用 statObject 方法检查文件是否存在
        try {
            minioClient.statObject(StatObjectArgs.builder().bucket(minIOConfig.getBucketName()).object(filename).build());
        } catch (ErrorResponseException e) {
            if (e.response().code() == 404) {
                return false;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
