package com.xiaopinyun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.stream.Collectors;

@SpringBootApplication
@MapperScan("com.xiaopinyun.mapper")
// 开启 OpenFeign 的远程调用功能
@EnableFeignClients
public class UserApp {
    public static void main(String[] args) {
        SpringApplication.run(UserApp.class, args);
    }

    /**
     * 报错：feign.codec.DecodeException: No qualifying bean of type 'org.springframework.boot.autoconfigure.http.HttpMessageConverters' available:expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
     * Spring Cloud Gateway 是基于 WebFlux 的，是 ReactiveWeb，所以HttpMessageConverters不会自动注入。
     * 在启动类中配置以下 Bean，即可解决。
     */
    @Bean
    @ConditionalOnMissingBean
    public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
        return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
    }
}
