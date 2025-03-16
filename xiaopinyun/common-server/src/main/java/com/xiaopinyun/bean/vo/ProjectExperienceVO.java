package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.ProjectExperience;
import lombok.Data;

/**
 * 项目经历（展示层对象）
 */
@Data
public class ProjectExperienceVO {
    // 主键
    private Long id;
    // 项目名称
    private String projectName;
    // 项目角色
    private String projectRole;
    // 项目开始时间 例2024.03 2024.03-2024.07
    private String startTime;
    // 项目结束时间 例2024.07
    private String endTime;
    // 项目描述
    private String describe;

    // 构造方法，用于从 ProjectExperience 对象转换
    public ProjectExperienceVO(ProjectExperience projectExperience){
        this.id = projectExperience.getId();
        this.projectName = projectExperience.getProjectName();
        this.projectRole = projectExperience.getProjectRole();
        this.startTime = projectExperience.getStartTime();
        this.endTime = projectExperience.getEndTime();
        this.describe = projectExperience.getDescribe();
    }
}
