package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.ProjectExperience;
import com.xiaopinyun.bean.vo.ProjectExperienceVO;

import java.util.List;

public interface ProjectExperienceService extends IService<ProjectExperience> {

    /**
     * 查询项目经历
     */
    Result<List<ProjectExperienceVO>> query(String pkApplicant);

    /**
     * 添加项目经历
     */
    Result<ProjectExperienceVO> insert(ProjectExperienceVO projectExperienceVO);

    /**
     * 修改项目经历
     */
    Result<ProjectExperienceVO> update(ProjectExperienceVO projectExperienceVO);

    /**
     * 删除项目经历
     */
    Result<Void> delete(String id);
}
