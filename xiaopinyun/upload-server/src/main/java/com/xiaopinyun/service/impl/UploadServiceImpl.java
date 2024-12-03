package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xiaopinyun.pojo.MinIOConfigurationProperties;
import com.xiaopinyun.service.UploadService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private MinIOConfigurationProperties minIOConfigurationProperties;

    @Autowired
    private MinioClient minioClient;


    /**
     * 文件上传
     *
     * @param file 上传的文件
     * @return 返回文件在服务器里的链接
     */
    public String uploadFile(MultipartFile file) {
        // 获取上传的文件名
        String filename = file.getOriginalFilename();
        if (StringUtils.isBlank(filename)) {
            return "文件名格式不正确";
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
                    .bucket(minIOConfigurationProperties.getBucketName())
                    .object(filename)
                    .stream(in, file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build();
            minioClient.putObject(objectArgs);
            // 返回文件在服务器里的链接
            return minIOConfigurationProperties.getEndpoint() + "/" + minIOConfigurationProperties.getBucketName() + "/" + filename;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭链接，释放资源
            try {
                minioClient.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 下载文件
     *
     * @param filename 文件名
     * @return 返回该文件的输入流
     */
    public InputStream downloadFile(String filename) {
        try {
            return minioClient.getObject(GetObjectArgs.builder()
                    .bucket(minIOConfigurationProperties.getBucketName())
                    .object(filename)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                minioClient.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
