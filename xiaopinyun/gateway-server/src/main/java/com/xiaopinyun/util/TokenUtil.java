package com.xiaopinyun.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;

import java.util.Date;
import java.util.Map;

/**
 * token 工具类
 */
public class TokenUtil {
    private static final byte[] KEY = "xiaopinyun".getBytes();  // 密钥
    private static final long EXPIRE = 7 * 24 * 60 * 60;  // 7天后过期

    /**
     * 根据 map 生成 token 默认：HS265(HmacSHA256)算法
     */
    public static String getToken(Map<String, Object> map) {
        JWT jwt = JWT.create();
        map.forEach(jwt::setPayload);  // 携带数据
        jwt.setKey(KEY);  // 密钥
        jwt.setExpiresAt(new Date(System.currentTimeMillis() + EXPIRE * 1000));  // 过期时间
        return jwt.sign();
    }

    /**
     * token 校验
     */
    public static boolean verify(String token) {
        if (StrUtil.isBlank(token)) {
            return false;
        }
        return JWT.of(token).setKey(KEY).validate(0);
    }
}
