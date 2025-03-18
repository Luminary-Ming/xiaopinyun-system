package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.HR;
import lombok.Data;

/**
 * 招聘人 / HR 个人信息（展示层对象）
 */
@Data
public class HRVO {
    // 主键
    private Long id;
    // 头像
    private String profileImgUrl;
    // 姓名
    private String name;
    // 性别  0男、1女 --> 将数字转换为字符串
    private String sex;
    // 所属公司
    private Long pk_company;
    // 身份  例人事总监、HR、招聘专家、人事专员、技术总监、经理、校招顾问、项目总监
    private String identity;
    // 手机号 --> 进行脱敏处理，中间四位 * 号代替
    private String telephone;
    // 电子邮箱
    private String email;
    // 状态  0-在线、1-昨日活跃、2-3天内活跃、3-7天内活跃、4-1月内活跃 --> 将数字转换为字符串
    private String status;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中 --> 将数字转换为字符串
    private String checkStatus;

    public HRVO(HR hr) {
        this.id = hr.getId();
        this.profileImgUrl = hr.getProfileImg();
        this.name = hr.getName();
        this.sex = convertSex(hr.getSex());
        this.identity = hr.getIdentity();
        this.telephone = hr.getTelephone();
        this.email = hr.getEmail();
        this.status = convertStatus(hr.getStatus());
        this.checkStatus = convertCheckStatus(hr.getCheckStatus());
    }

    // 私有方法，用于转换性别描述
    private String convertSex(Integer sex) {
        return sex == 0 ? "男" : "女";
    }

    // 私有方法，用于转换状态描述
    private String convertStatus(Integer status) {
        // 增强 switch 语法，Java14 开始使用
        return switch (status) {
            case 0 -> "在线";
            case 1 -> "昨日活跃";
            case 2 -> "3天内活跃";
            case 3 -> "7天内活跃";
            case 4 -> "1月内活跃";
            default -> "未知状态";
        };
    }

    // 私有方法，用于转换审核状态描述
    private String convertCheckStatus(Integer isCheck) {
        // 根据实际需求进行转换
        return switch (isCheck) {
            case 0 -> "未审核";
            case 1 -> "审核通过";
            case 2 -> "审核中";
            default -> "未知状态";
        };
    }
}
