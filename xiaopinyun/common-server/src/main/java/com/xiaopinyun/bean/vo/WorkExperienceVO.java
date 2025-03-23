package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.WorkExperience;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 工作 / 实习经历（展示层对象）
 */
@Data
@NoArgsConstructor
public class WorkExperienceVO {
    // 主键
    private String id;
    // 学生信息主键
    private String pkApplicant;
    // 公司名
    private String companyName;
    // 所属行业
    private String industryType;
    // 所属部门
    private String department;
    // 职位名称
    private String jobName;
    // 入职时间  例2024.11 2024.11-2025.05
    private String startTime;
    // 离职时间  例2025.05
    private String endTime;
    // 工作内容
    private String jobContent;

    // 构造方法，用于从 WorkExperience 对象转换
    public WorkExperienceVO(WorkExperience workExperience){
        this.id = workExperience.getId().toString();
        this.pkApplicant = workExperience.getPkApplicant().toString();
        this.companyName  =workExperience.getCompanyName();
        this.industryType = workExperience.getIndustryType();
        this.department = workExperience.getDepartment();
        this.jobName = workExperience.getJobName();
        this.startTime = workExperience.getStartTime();
        this.endTime = workExperience.getEndTime();
        this.jobContent = workExperience.getJobContent();
    }
}
