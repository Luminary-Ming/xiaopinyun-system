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
 * 个人优势（专业技能、获奖荣誉、自我评价）
 */
@Data
@TableName("advantage")
public class Advantage {
    // 主键
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    // 学生信息主键
    private Long pk_applicant;
    // 专业技能
    private String majorSkill;
    // 获奖荣誉
    private String honor;
    // 自我评价
    private String selfEvaluation;
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
