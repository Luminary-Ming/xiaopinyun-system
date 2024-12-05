package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xiaopinyun.config.MinIOConfig;
import com.xiaopinyun.pojo.vo.Result;
import com.xiaopinyun.service.UploadService;
import com.xiaopinyun.util.BizCode;
import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListObjectsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.http.Method;
import io.minio.messages.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
        try {
            // 获得流对象
            InputStream in = file.getInputStream();
            // 上传到 MinIO 服务器的 Bucket 存储桶里
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    .bucket(minIOConfig.getBucketName())
                    .object(filename)
                    .stream(in, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();
            minioClient.putObject(objectArgs);
            // 返回文件在服务器里的链接
            String fileURL = minIOConfig.getEndpoint() + "/" + minIOConfig.getBucketName() + "/" + filename;
            return Result.success(BizCode.FILE_UPLOAD_SUCCESS, fileURL);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 文件下载
     */
    public byte[] downloadFile(String filename) {
        InputStream stream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            stream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(minIOConfig.getBucketName())
                    .object(filename).build());
            outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = stream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            return outputStream.toByteArray();

        } catch (Exception e) {
            try {
                throw new Exception("Error occurred while downloading file from MinIO", e);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            } finally {
                try {
                    if (stream != null) {
                        stream.close();

                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }


    /**
     * 根据文件路径得到预览文件绝对地址
     */
    public String getPreviewFileUrl(String filename) {

        Iterable<io.minio.Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(minIOConfig.getBucketName())
                .prefix(filename)
                .maxKeys(100)
                .build());
        if (results == null) {
            return "桶中没有此文件";
        }

        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().method(Method.GET).bucket(minIOConfig.getBucketName()).object(filename).build());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 删除文件
     */
    public String deleteFile(String filename) {
        Iterable<io.minio.Result<Item>> results = minioClient.listObjects(ListObjectsArgs.builder()
                .bucket(minIOConfig.getBucketName())
                .prefix(filename)
                .maxKeys(100)
                .build());
        if (results == null) {
            return "桶中没有此文件";
        }


        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder()
                            .bucket(minIOConfig.getBucketName())
                            .object(filename)
                            .build());
            return "删除成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
