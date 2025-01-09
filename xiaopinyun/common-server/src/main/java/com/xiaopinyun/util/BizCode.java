package com.xiaopinyun.util;

import lombok.Getter;

/**
 * 自定义状态码
 */
@Getter
public enum BizCode {
    UNKNOWN(0, "未知错误"),
    SUCCESS(2000, "操作成功"),
    FAIL(5000, "操作失败"),

    USER_NOT_EXIST(1001, "用户不存在"),
    USER_PASSWORD_ERROR(1002, "登录密码错误"),
    USER_NOT_LOGIN(1003, "用户未登录"),
    USER_NOT_AUTHORIZED(1004, "用户未授权"),
    USER_NOT_PERMISSION(1005, "用户无权限"),

    SELECT_SUCCESS(2001, "查询成功"),
    ADD_SUCCESS(2002, "添加成功"),
    UPDATE_SUCCESS(2003, "更新成功"),
    DELETE_SUCCESS(2004, "删除成功"),
    FILE_UPLOAD_SUCCESS(2005, "文件上传成功"),
    FILE_DOWNLOAD_SUCCESS(2006, "文件下载成功"),
    FILE_PREVIEW_SUCCESS(2007, "文件预览成功"),
    FILE_DELETE_SUCCESS(2008, "文件删除成功"),


    NO_DATA(5001, "没有查询到数据"),
    ADD_FAIL(5002, "添加失败"),
    UPDATE_FAIL(5003, "更新失败"),
    DELETE_FAIL(5004, "删除失败"),
    NOT_VALID_DATA(5005, "不是有效数据"),
    ADD_NULL(5006, "请填写信息"),
    FILE_UPLOAD_FAIL(5007, "文件上传失败"),
    FILE_DOWNLOAD_FAIL(5008, "文件下载失败"),
    FILE_PREVIEW_FAIL(5009, "文件预览失败"),
    FILE_DELETE_FAIL(5010, "文件删除失败"),
    FILE_NOT_EXIST(5011, "文件不存在"),
    SEX_FAIL(5012, "性别错误"),
    BIRTHDAY_FORMAT_FAIL(5013,"出生年月格式错误"),
    TELEPHONE_FORMAT_FAIL(5014, "手机号格式错误"),
    EMAIL_FORMAT_FAIL(5015,"邮箱格式错误"),
    STATUS_FAIL(5016,"求职状态错误"),
    WORKINGHOURS_FORMAT_FAIL(5017,"工作时间格式错误"),
    HOLIDAY_FAIL(5018,"休假情况错误"),
    OVERTIME_FAIL(5019,"加班情况错误"),
    CAPITAL_FAIL(5020,"注册资本只能为数值"),
    FOUNDDATE_FORMAT_FAIL(5021,"成立日期格式不正确"),
    QUALIFICATION_FORMAT_FAIL(5022,"学历格式不正确"),
    TIME_FORMAT_FAIL(5023,"时间格式不正确")
    ;


    private final Integer code;
    private final String message;


    BizCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
