package com.xiaopinyun.bean.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 在线简历
 */
@Data
@TableName("resume")
public class Resume {
    // 主键
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    // 学生信息主键
    private Long pk_applicant;
    // 专业技能
    private String majorSkill;
    // 求职期望
    // 教育背景
    // 项目经历
    // 工作/实习经历
    // 获奖荣誉
    private String honor;
    // 自我评价
    private String selfEvaluation;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中
    private Integer checkStatus;
    // 是否已删除 0正常、1已删除
    @TableLogic
    private Integer dr;
    // 时间戳
    private String ts;
}
