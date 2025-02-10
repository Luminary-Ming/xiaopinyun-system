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
    ERROR(500, "操作失败"),

    ADD_FAIL(500,""),


    FILE_UPLOAD_SUCCESS(2005, ""),
    FILE_DOWNLOAD_SUCCESS(2006, "文件下载成功"),
    FILE_PREVIEW_SUCCESS(2007, "文件预览成功"),
    FILE_DELETE_SUCCESS(2008, "文件删除成功"),

    BIRTHDAY_FORMAT_FAIL(5013, ""),
    TELEPHONE_FORMAT_FAIL(5014, ""),
    EMAIL_FORMAT_FAIL(5015, ""),
    STATUS_FAIL(5016, ""),
    WORKINGHOURS_FORMAT_FAIL(5017, ""),
    HOLIDAY_FAIL(5018, ""),
    OVERTIME_FAIL(5019, ""),
    CAPITAL_FAIL(5020, ""),
    FOUNDDATE_FORMAT_FAIL(5021, ""),
    QUALIFICATION_FORMAT_FAIL(5022, "学历格式不正确"),
    TIME_FORMAT_FAIL(5023, "时间格式不正确"),
    NOT_VALID_DATA(5005, "不是有效数据"),
    ADD_NULL(5006, ""),

    FILE_UPLOAD_FAIL(5007, "文件上传失败"),
    FILE_DOWNLOAD_FAIL(5008, "文件下载失败"),
    FILE_PREVIEW_FAIL(5009, "文件预览失败"),
    FILE_DELETE_FAIL(5010, "文件删除失败"),
    FILE_NOT_EXIST(5011, "文件不存在");


    private final Integer code;
    private final String message;


    BizCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
