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
 * 招聘人 / HR 个人信息
 */
@Data
@TableName("hr")
public class HR {
    // 主键
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    // 头像
    private String profileImg;
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
    @TableField(fill = FieldFill.INSERT)
    private Integer status;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer checkStatus;
    // 是否已删除 0正常、1已删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer dr;
    // 时间戳
    @TableField(fill = FieldFill.INSERT_UPDATE)  // 新增更新自动填充
    private LocalDateTime ts;
}
