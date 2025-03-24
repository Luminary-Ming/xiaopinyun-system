package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.Applicant;
import lombok.Data;
import lombok.NoArgsConstructor;
// 字段选择：VO 对象只包含前端展示所需的字段，以减少不必要的数据传输和增强安全性；

/**
 * 求职者 / 大学生 个人信息（展示层对象）
 */
@Data
@NoArgsConstructor
public class ApplicantVO {
    // 主键（原先的Long类型会被JS的 Number 类型限制，精度会丢失）
    private String id;
    // 头像
    private String profileImgUrl;
    // 姓名
    private String name;
    // 性别  0男、1女
    private String sex;
    // 出生年月 例2024-11
    private String birthday;
    // 居住地
    private String address;
    // 求职状态 0离校-随时到岗、1在校-月内到岗、2在校-看看机会、3在校-暂不考虑
    private String status;
    // 手机号
    private String telephone;
    // 电子邮箱
    private String email;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中
    private String checkStatus;
    // 是否就业（管理员统计就业率）  0未就业、1已就业
    private String employStatus;

    // 构造方法，用于从 Applicant 对象转换
    public ApplicantVO(Applicant applicant) {
        this.id = applicant.getId().toString();
        if (applicant.getProfileImg() == null) {
            this.profileImgUrl = "/src/assets/images/profile-img/default.png";
        } else {
            this.profileImgUrl = applicant.getProfileImg();
        }
        this.name = applicant.getName();
        if (applicant.getSex() == null) {
            this.sex = null;
        } else {
            this.sex = applicant.getSex().toString();
        }
        this.birthday = applicant.getBirthday();
        this.address = applicant.getAddress();
        if (applicant.getStatus() == null) {
            this.status = null;
        } else {
            this.status = applicant.getStatus().toString();
        }
        this.telephone = applicant.getTelephone();
        this.email = applicant.getEmail();
        this.checkStatus = applicant.getCheckStatus().toString();
        this.employStatus = applicant.getEmployStatus().toString();
    }
}
