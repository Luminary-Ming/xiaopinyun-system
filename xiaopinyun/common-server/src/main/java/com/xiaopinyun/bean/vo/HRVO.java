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
    // 性别  0男、1女
    private Integer sex;
    // 所属公司
    private Long pk_company;
    // 身份  例人事总监、HR、招聘专家、人事专员、技术总监、经理、校招顾问、项目总监
    private String identity;
    // 手机号
    private String telephone;
    // 电子邮箱
    private String email;
    // 状态  0-在线、1-昨日活跃、2-3天内活跃、3-7天内活跃、4-1月内活跃
    private Integer status;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中
    private Integer checkStatus;

    public HRVO(HR hr) {
        this.id = hr.getId();
        this.profileImgUrl = hr.getProfileImg();
        this.name = hr.getName();
        this.sex = hr.getSex();
        this.identity = hr.getIdentity();
        this.telephone = hr.getTelephone();
        this.email = hr.getEmail();
        this.status = hr.getStatus();
        this.checkStatus = hr.getCheckStatus();
    }
}
