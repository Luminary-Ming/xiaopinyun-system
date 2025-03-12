package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.Resume;
import lombok.Data;

import java.util.List;

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
    private List<JobExpectationVO> jobExpectationVO;
    // 教育背景 例1,2,3 --> 将id转换为对象数组
    private List<EducationalBackgroundVO> educationalBackgroundVO;
    // 项目经历 例1,2,3 --> 将id转换为对象数组
    private List<ProjectExperienceVO> projectExperienceVO;
    // 工作/实习经历 例1,2,3 --> 将id转换为对象数组
    private List<WorkExperienceVO> workExperienceVO;
    // 获奖荣誉
    private String honor;
    // 自我评价
    private String selfEvaluation;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中 --> 将数字转换为字符串
    private String checkStatus;

    public ResumeVO(Resume resume) {
        this.id = resume.getId();
        this.majorSkill = resume.getMajorSkill();
        this.honor = resume.getHonor();
        this.selfEvaluation = resume.getSelfEvaluation();
        this.checkStatus = convertCheckStatus(resume.getCheckStatus());
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
}