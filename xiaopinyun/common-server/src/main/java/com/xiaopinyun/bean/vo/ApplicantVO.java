package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.Applicant;
import lombok.Data;
// 字段选择：VO 对象只包含前端展示所需的字段，以减少不必要的数据传输和增强安全性；
// 数据格式：VO 对象中的字段数据格式应根据前端展示需求进行调整，如日期字段可能需要格式化为特定的字符串格式。
// 计算属性：VO 对象中可以包含一些计算属性，这些属性是根据其他字段的值计算得出的，用于满足特定的展示需求。

/**
 * 求职者 / 大学生 个人信息（展示层对象）
 */
@Data
public class ApplicantVO {
    // 主键
    private Integer id;
    // 头像
    private String profileImgUrl;
    // 姓名
    private String name;
    // 性别  0男、1女 --> 将数字转换为字符串
    private String sex;
    // 出生年月 例2024-11 --> 格式化为2024年10月
    private String birthday;
    // 居住地
    private String address;
    // 求职状态 0离校-随时到岗、1在校-月内到岗、2在校-看看机会、3在校-暂不考虑 --> 将数字转换为对应的描述
    private String status;
    // 手机号 --> 进行脱敏处理，中间四位 * 号代替
    private String telephone;
    // 电子邮箱
    private String email;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中 --> 将数字转换为对应的描述
    private String checkStatus;
    // 是否就业（管理员统计就业率）  0未就业、1已就业 --> 将数字转换为对应的描述
    private String employStatus;

    // 构造方法，用于从 Applicant 对象转换
    public ApplicantVO(Applicant applicant) {
        this.id = applicant.getId();
        this.profileImgUrl = applicant.getProfileImg();
        this.name = applicant.getName();
        this.sex = convertSex(applicant.getSex());
        this.birthday = convertBirthday(applicant.getBirthday());
        this.address = applicant.getAddress();
        this.status = convertStatus(applicant.getStatus());
        this.telephone = convertTelephone(applicant.getTelephone());
        this.email = applicant.getEmail();
        this.checkStatus = convertCheckStatus(applicant.getCheckStatus());
        this.employStatus = convertEmployStatus(applicant.getEmployStatus());
    }

    // 私有方法，用于转换性别描述
    private String convertSex(Integer sex) {
        return sex == 0 ? "男" : "女";
    }

    // 私有方法，用于格式化出生年月
    private String convertBirthday(String birthday) {
        String[] parts = birthday.split("-");
        return parts[0] + "年" + parts[1] + "月";
    }

    // 私有方法，用于处理手机号显示（脱敏） 173****3365
    private String convertTelephone(String telephone) {
        return telephone.substring(0, 3) + "****" + telephone.substring(telephone.length() - 4);
    }

    // 私有方法，用于转换求职状态描述
    private String convertStatus(Integer status) {
        // 增强 switch 语法，Java14 开始使用
        return switch (status) {
            case 0 -> "离校-随时到岗";
            case 1 -> "在校-月内到岗";
            case 2 -> "在校-看看机会";
            case 3 -> "在校-暂不考虑";
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

    // 私有方法，用于转换就业状态描述
    private String convertEmployStatus(Integer isEmploy) {
        // 根据实际需求进行转换
        return switch (isEmploy) {
            case 0 -> "未就业";
            case 1 -> "已就业";
            default -> "未知状态";
        };
    }
}
