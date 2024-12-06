package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xiaopinyun.config.MinIOConfig;
import com.xiaopinyun.pojo.vo.Result;
import com.xiaopinyun.service.UploadService;
import com.xiaopinyun.util.BizCode;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.http.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private MinIOConfig minIOConfig;
    @Autowired
    private MinioClient minioClient;
    @Autowired
    private UploadServiceImpl uploadServiceImpl;

    /**
     * 文件上传
     */
    public Result<String> uploadFile(MultipartFile file) {
        // 获取上传的文件名
        String filename = file.getOriginalFilename();
        if (StringUtils.isBlank(filename)) {
            return Result.fail(BizCode.FILE_UPLOAD_FAIL);
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
            return Result.success(BizCode.FILE_UPLOAD_SUCCESS, fileURL);
        } catch (Exception e) {
            return Result.fail(BizCode.FILE_UPLOAD_FAIL);
        }
    }

    /**
     * 文件下载
     */
    public Result<byte[]> downloadFile(String filename) {
        /*
            try-with-resources 语句，JDK7加入的
            当 try 中代码执行结束后（正常结束或者异常结束），都会调用 try() 括号中对象的close()方法来关闭资源
        */
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(minIOConfig.getBucketName())
                        .object(filename).build());
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            // 创建缓冲区，用于临时存储从输入流中读取的数据
            byte[] buffer = new byte[1024];
            int length;
            // 读取数据
            while ((length = stream.read(buffer)) != -1) {
                // 写入数据
                outputStream.write(buffer, 0, length);
            }
            byte[] byteArray = outputStream.toByteArray();
            return Result.success(BizCode.FILE_DOWNLOAD_SUCCESS, byteArray);
        } catch (Exception e) {
            return Result.fail(BizCode.FILE_DOWNLOAD_FAIL);
        }
    }


    /**
     * 根据文件路径得到预览文件绝对地址
     */
    public Result<String> getPreviewFileUrl(String filename) {
        // 如果文件在桶中不存在
        if (!uploadServiceImpl.fileIsExist(filename)) {
            return Result.fail(BizCode.FILE_PREVIEW_FAIL);
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
            return Result.fail(BizCode.FILE_PREVIEW_FAIL);
        }
        return Result.success(BizCode.FILE_PREVIEW_SUCCESS, previewFileUrl);
    }


    /**
     * 删除文件
     */
    public Result<String> deleteFile(String filename) {
        // 如果文件在桶中不存在
        if (!uploadServiceImpl.fileIsExist(filename)) {
            return Result.fail(BizCode.FILE_PREVIEW_FAIL);
        }
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(minIOConfig.getBucketName())
                            .object(filename)
                            .build());
            return Result.success(BizCode.FILE_DELETE_SUCCESS);
        } catch (Exception e) {
            return Result.fail(BizCode.FILE_DELETE_FAIL);
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
