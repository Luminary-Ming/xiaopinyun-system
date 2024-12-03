package com.xiaopinyun.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * MinIO 服务器链接对象
 */
@Data
// 声明为配置类
@Configuration
// 指定配置文件中配置的属性映射到本类对象上
@ConfigurationProperties(prefix = "minio")
public class MinIOConfigurationProperties {
    // 端点
    private String endpoint;
    // 密钥 accessKey
    private String accessKey;
    // 密钥 secretKey
    private String secretKey;
    // 桶名
    private String bucketName;
}
