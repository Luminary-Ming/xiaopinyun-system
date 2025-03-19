package com.xiaopinyun.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 雪花算法配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "snowflake")
public class SnowflakeConfig {

    private long machineId;  // 机器ID
    private long dataCenterId;  // 数据中心ID

    @Bean
    public Snowflake snowflake() {
        return IdUtil.getSnowflake(machineId, dataCenterId);
    }
}
