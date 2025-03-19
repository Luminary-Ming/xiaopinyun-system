package com.xiaopinyun.bean.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdvantageVO {
    // 主键
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
    private Integer checkStatus;
    // 是否已删除 0正常、1已删除
    private Integer dr;
    // 时间戳
    private LocalDateTime ts;
}
