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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Service
public class UploadServiceImpl implements UploadService {
    @Autowired
    private MinIOConfig minIOConfig;
    @Autowired
    private MinioClient minioClient;

    /**
     * 文件上传
     */
    public Mono<Result<String>> uploadFileAsync(FilePart file) {
        // 获取上传的文件名
        String filename = file.filename();

        // 如果文件名为空
        if (StringUtils.isBlank(filename)) {
            return Mono.just(Result.paramError("文件名不能为空"));
        }

        // 获取文件扩展名
        String ext = filename.substring(filename.lastIndexOf("."));

        // 获取文件名（不包含扩展名）
        String baseFilename = filename.substring(0, filename.lastIndexOf("."));

        // 日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String today = dateFormat.format(new Date());

        // 生成处理过的上传文件名
        String processedFilename = baseFilename + "_" + today + ext;

        // 将 Flux<DataBuffer> 转换为 InputStream
        return DataBufferUtils.join(file.content()).flatMap(dataBuffer -> {
            byte[] bytes = new byte[dataBuffer.capacity()];
            dataBuffer.read(bytes);
            DataBufferUtils.release(dataBuffer); // 释放资源
            InputStream inputStream = new ByteArrayInputStream(bytes);

            try {
                // 上传到 MinIO 服务器的 Bucket 存储桶里
                minioClient.putObject(
                        PutObjectArgs.builder()
                                .bucket(minIOConfig.getBucketName())
                                .object(processedFilename)
                                .stream(inputStream, bytes.length, -1)
                                .contentType(Objects.requireNonNull(file.headers().getContentType()).toString())
                                .build());

                // 返回文件在服务器里的链接
                String fileURL = minIOConfig.getEndpoint() + "/" + minIOConfig.getBucketName() + "/" + processedFilename;
                return Mono.just(Result.ok(fileURL, "文件上传成功"));
            } catch (Exception e) {
                return Mono.just(Result.error("文件上传失败"));
            }
        });
    }

    /**
     * 文件下载
     */
    public ResponseEntity<ByteArrayResource> downloadFile(String filename) {
        ByteArrayResource byteArrayResource = null;
        try (InputStream inputStream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(minIOConfig.getBucketName())
                        .object(filename).build())) {

            // 创建一个 ByteArrayOutputStream 来存储从输入流中读取的数据
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;

            // 读取数据并写入 ByteArrayOutputStream
            while ((length = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, length);
            }

            // 将 ByteArrayOutputStream 转换为字节数组
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            // 使用字节数组创建 ByteArrayResource 对象
            byteArrayResource = new ByteArrayResource(byteArray);
        } catch (Exception e) {
            // 捕获异常并返回 500 错误
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8)); // 编码文件名

        // 动态设置文件类型
        MediaType mediaType = MediaTypeFactory.getMediaType(filename)
                .orElse(MediaType.APPLICATION_OCTET_STREAM); // 如果无法推断类型，默认使用二进制流
        headers.setContentType(mediaType);

        headers.add("Cache-Control", "no-cache, no-store, must-revalidate"); // 禁用缓存
        headers.add("Pragma", "no-cache"); // 禁用缓存
        headers.add("Expires", "0"); // 禁用缓存

        // 返回 ResponseEntity
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(byteArrayResource.contentLength()) // 设置文件长度
                .body(byteArrayResource);
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