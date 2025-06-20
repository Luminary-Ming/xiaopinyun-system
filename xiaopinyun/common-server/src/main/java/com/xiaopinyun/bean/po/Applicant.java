package com.xiaopinyun.bean.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 求职者 / 大学生 个人信息
 */
@Data
@TableName("applicant")
public class Applicant {
    // 主键
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    // 头像
    private String profileImg;
    // 姓名
    private String name;
    // 性别  0男、1女
    private Integer sex;
    // 出生年月 例2024-11
    private String birthday;
    // 居住地
    private String address;
    // 求职状态 0离校-随时到岗、1在校-月内到岗、2在校-看看机会、3在校-暂不考虑
    private Integer status;
    // 手机号
    private String telephone;
    // 电子邮箱
    private String email;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer checkStatus;
    // 是否就业（管理员统计就业率）  0未就业、1已就业
    @TableField(fill = FieldFill.INSERT)
    private Integer employStatus;
    // 是否已删除 0正常、1已删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer dr;
    // 时间戳
    @TableField(fill = FieldFill.INSERT_UPDATE)  // 新增更新自动填充
    private LocalDateTime ts;
}
