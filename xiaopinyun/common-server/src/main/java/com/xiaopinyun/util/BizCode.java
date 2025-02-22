package com.xiaopinyun.util;

import lombok.Getter;

/**
 * 自定义状态码
 */
@Getter
public enum BizCode {
    UNKNOWN(0, "未知错误"),

    OK(200, "操作成功"),
    NO_DATA(204, "没有数据"),

    PARAM_ERROR(400, "参数错误"),
    UNAUTHORIZED(401, "未授权"),
    PLEASE_WRITE(403, "请填写信息"),

    ERROR(500, "操作失败");

    private final Integer code;
    private final String message;

    BizCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
