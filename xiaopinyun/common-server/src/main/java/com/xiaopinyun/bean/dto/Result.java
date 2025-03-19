package com.xiaopinyun.bean.dto;

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
    // 操作是否成功
    private boolean success;
    // 响应状态码
    private Integer code;
    // 返回信息
    private String message;
    // 返回数据
    private T data;

    /**
     * 未知错误
     */
    public static <T> Result<T> unkown() {
        return fail(BizCode.UNKNOWN, null);
    }

    /**
     * 操作成功
     */
    public static <T> Result<T> ok() {
        return success(BizCode.OK, null);
    }

    /**
     * 操作成功
     */
    public static <T> Result<T> ok(BizCode bizCode) {
        return success(bizCode, null);
    }

    /**
     * 操作成功，返回数据
     */
    public static <T> Result<T> ok(T data) {
        return success(BizCode.OK, data);
    }

    /**
     * 操作成功，返回数据
     */
    public static <T> Result<T> ok(T data, BizCode bizCode) {
        return success(bizCode, data);
    }

    /**
     * 操作成功，返回数据，自定义返回信息
     */
    public static <T> Result<T> ok(T data, String message) {
        return new Result<>(true, BizCode.OK.getCode(), message, data);
    }

    /**
     * 操作成功，自定义返回信息
     */
    public static <T> Result<T> ok(String message) {
        return new Result<>(true, BizCode.OK.getCode(), message, null);
    }

    /**
     * 参数错误
     */
    public static <T> Result<T> paramError(BizCode bizCode) {
        return fail(bizCode, null);
    }

    /**
     * 参数错误，自定义状态码返回信息
     */
    public static <T> Result<T> paramError(String message) {
        return new Result<>(false, BizCode.PARAM_ERROR.getCode(), message, null);
    }

    /**
     * 异常报错
     */
    public static <T> Result<T> error() {
        return fail(BizCode.ERROR, null);
    }

    /**
     * 异常报错
     */
    public static <T> Result<T> error(BizCode bizCode) {
        return fail(bizCode, null);
    }

    /**
     * 异常报错，返回数据
     */
    public static <T> Result<T> error(T data) {
        return fail(BizCode.ERROR, data);
    }

    /**
     * 异常报错，返回数据
     */
    public static <T> Result<T> error(T data, BizCode bizCode) {
        return fail(bizCode, data);
    }

    /**
     * 异常报错，自定义状态码返回信息
     */
    public static <T> Result<T> error(String message) {
        return new Result<>(false, BizCode.ERROR.getCode(), message, null);
    }

    /**
     * 异常报错，返回数据，自定义状态码返回信息
     */
    public static <T> Result<T> error(T data, String message) {
        return new Result<>(false, BizCode.ERROR.getCode(), message, data);
    }

    /**
     * 返回成功
     */
    public static <T> Result<T> success(BizCode bizCode, T data) {
        return new Result<>(true, bizCode.getCode(), bizCode.getMessage(), data);
    }

    /**
     * 返回失败
     */
    public static <T> Result<T> fail(BizCode bizCode, T data) {
        return new Result<>(false, bizCode.getCode(), bizCode.getMessage(), data);
    }
}
