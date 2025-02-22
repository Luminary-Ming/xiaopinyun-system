package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.po.ProjectExperience;
import com.xiaopinyun.bean.vo.ProjectExperienceVO;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.mapper.ProjectExperienceMapper;
import com.xiaopinyun.service.ProjectExperienceService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 张金龙
 * @Date 2025/2/19 10:41
 * @Version 1.0
 */
@Service
public class ProjectExperienceServiceImpl extends ServiceImpl<ProjectExperienceMapper, ProjectExperience> implements ProjectExperienceService {
    @Autowired
    private ProjectExperienceMapper projectExperienceMapper;

    /**
     * 根据 id 查询项目经历
     */
    @Override
    public Result<ProjectExperienceVO> queryVOById(Integer id) {
        ProjectExperience projectExperience = projectExperienceMapper.selectById(id);
        if (projectExperience == null) {
            return Result.ok(BizCode.NO_DATA);
        }
        ProjectExperienceVO projectExperienceVO = new ProjectExperienceVO(projectExperience);
        return Result.ok(projectExperienceVO);
    }

    /**
     * 添加项目经历
     */
    @Override
    public Result<ProjectExperienceVO> saveVO(ProjectExperience projectExperience) {
        if (projectExperience == null) {
            return Result.paramError(BizCode.PLEASE_WRITE);
        }
        if (!save(projectExperience)) {
            return Result.error();
        }
        ProjectExperience projectExperienceData = projectExperienceMapper.selectById(projectExperience.getId());
        ProjectExperienceVO projectExperienceVO = new ProjectExperienceVO(projectExperienceData);
        return Result.ok(projectExperienceVO);
    }

    /**
     * 修改项目经历
     */
    @Override
    public Result<ProjectExperienceVO> updateVO(ProjectExperience projectExperience) {
        if (projectExperience == null) {
            return Result.ok();
        }
        if(!updateById(projectExperience)){
            return Result.error();
        }
        ProjectExperience projectExperienceData = projectExperienceMapper.selectById(projectExperience.getId());
        ProjectExperienceVO projectExperienceVO = new ProjectExperienceVO(projectExperienceData);
        return Result.ok(projectExperienceVO);
    }

    /**
     * 根据 id 删除项目经历
     */
    @Override
    public Result<Void> deleteVOById(Integer id) {
        if(!removeById(id)){
            return Result.error();
        }
        return Result.ok();
    }
}
