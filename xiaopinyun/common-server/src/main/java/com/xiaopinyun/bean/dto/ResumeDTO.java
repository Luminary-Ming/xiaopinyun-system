package com.xiaopinyun.bean.dto;

import com.xiaopinyun.bean.vo.ApplicantVO;
import com.xiaopinyun.bean.vo.EducationalBackgroundVO;
import com.xiaopinyun.bean.vo.JobExpectationVO;
import com.xiaopinyun.bean.vo.ProjectExperienceVO;
import com.xiaopinyun.bean.vo.WorkExperienceVO;
import lombok.Data;

import java.util.List;

@Data
public class ResumeDTO {
    // 主键
    private Long id;
    // 个人信息
    private ApplicantVO applicantVO;
    // 专业技能
    private String majorSkill;
    // 求职期望
    private List<JobExpectationVO> jobExpectationVO;
    // 教育背景
    private List<EducationalBackgroundVO> educationalBackgroundVO;
    // 项目经历
    private List<ProjectExperienceVO> projectExperienceVO;
    // 工作/实习经历
    private List<WorkExperienceVO> workExperienceVO;
    // 获奖荣誉
    private String honor;
    // 自我评价
    private String selfEvaluation;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中 --> 将数字转换为字符串
    private String checkStatus;

}
