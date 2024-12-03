package com.xiaopinyun.util;

import com.xiaopinyun.pojo.MinIOConfigurationProperties;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置 MinIO 服务器链接对象
 */
@Configuration
public class MinIOConfiguration {

    @Autowired
    private MinIOConfigurationProperties minIOConfigurationProperties;

    /**
     * @Bean 的作用将返回的 MinioClient 对象交给 Spring 管理
     */
    @Bean
    public MinioClient getMinioClient() {
        return MinioClient.builder()
                .endpoint(minIOConfigurationProperties.getEndpoint())
                .credentials(minIOConfigurationProperties.getAccessKey(), minIOConfigurationProperties.getSecretKey())
                .build();
    }
}
