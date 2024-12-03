package com.xiaopinyun.exception;

import com.xiaopinyun.util.BizCode;
import lombok.Getter;

/**
 * 自定义异常
 */
@Getter
public class BizException extends RuntimeException {
    private final Integer code;
    private final String message;

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BizException(BizCode bizCode) {
        super(bizCode.getMessage());
        this.code = bizCode.getCode();
        this.message = bizCode.getMessage();
    }
}
