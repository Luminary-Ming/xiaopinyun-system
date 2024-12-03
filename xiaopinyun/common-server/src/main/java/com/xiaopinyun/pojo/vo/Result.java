package com.xiaopinyun.pojo.vo;

import com.xiaopinyun.util.BizCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 统一结果集
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    // 响应状态码
    private Integer code;
    // 返回信息
    private String message;
    // 返回数据
    private T data;

    /**
     * 返回成功
     */
    public static <T> Result<T> success(BizCode bizCode, T data) {
        return new Result<>(bizCode.getCode(), bizCode.getMessage(), data);
    }

    /**
     * 返回失败
     */
    public static <T> Result<T> fail(BizCode bizCode, T data) {
        return new Result<>(bizCode.getCode(), bizCode.getMessage(), data);
    }

}
