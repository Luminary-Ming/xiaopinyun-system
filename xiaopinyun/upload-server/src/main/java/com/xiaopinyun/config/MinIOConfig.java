package com.xiaopinyun.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MinIO 服务器链接对象
 */
@Data
// 声明为配置类
@Configuration
// 指定配置文件中配置的属性映射到本类对象上
@ConfigurationProperties(prefix = "minio")
public class MinIOConfig {
    // 端点
    private String endpoint;
    // 密钥 accessKey
    private String accessKey;
    // 密钥 secretKey
    private String secretKey;
    // 桶名
    private String bucketName;

    /**
     * @Bean 的作用将返回的 MinioClient 对象交给 Spring 管理
     */
    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}