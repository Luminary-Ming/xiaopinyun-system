package com.xiaopinyun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 招聘人 / HR 个人信息
 */
@Data
@TableName("hr")
public class HR {
    // id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 头像
    private String profileImg;
    // 公司信息id
    private Integer cid;
    // 姓名
    private String name;
    // 性别  0男、1女
    private Integer sex;
    // 身份  例人事总监、HR、招聘专家、人事专员、技术总监、经理、校招顾问、项目总监
    private String identity;
    // 手机号
    private String telephone;
    // 电子邮箱
    private String email;
    // 状态  0-在线、1-昨日活跃、2-3天内活跃、3-7天内活跃、4-1月内活跃
    private Integer status;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中
    private Integer isCheck;
}