package com.xiaopinyun.bean.vo;

import lombok.Data;

/**
 * 在线简历（展示层对象）
 */
@Data
public class ResumeVO {
    // id
    private Integer id;
    // 个人信息
    private ApplicantVO applicantVO;
    // 专业技能
    private String majorSkill;
    // 求职期望 例1,2,3 --> 将id转换为对象数组
    private JobExpectationVO[] jobExpectationVO;
    // 教育背景 例1,2,3 --> 将id转换为对象数组
    private EducationalBackgroundVO[] educationalBackgroundVO;
    // 项目经历 例1,2,3 --> 将id转换为对象数组
    private ProjectExperienceVO[] projectExperienceVO;
    // 工作/实习经历 例1,2,3 --> 将id转换为对象数组
    private WorkExperienceVO[] workExperienceVO;
    // 获奖荣誉
    private String honor;
    // 自我评价
    private String selfEvaluation;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中 --> 将数字转换为字符串
    private String isCheck;
}