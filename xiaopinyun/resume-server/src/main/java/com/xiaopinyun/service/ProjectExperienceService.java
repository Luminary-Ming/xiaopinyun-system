package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.ProjectExperience;
import com.xiaopinyun.bean.vo.ProjectExperienceVO;

public interface ProjectExperienceService extends IService<ProjectExperience> {

    /**
     * 根据 id 查询项目经历
     */
    Result<ProjectExperienceVO> queryVOById(Integer id);

    /**
     * 添加项目经历
     */
    Result<ProjectExperienceVO> saveVO(ProjectExperience projectExperience);

    /**
     * 修改项目经历
     */
    Result<ProjectExperienceVO> updateVO(ProjectExperience projectExperience);

    /**
     * 根据 id 删除项目经历
     */
    Result<Void> deleteVOById(Integer id);
}
