package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.ProjectExperience;
import com.xiaopinyun.bean.vo.ProjectExperienceVO;
import com.xiaopinyun.mapper.ProjectExperienceMapper;
import com.xiaopinyun.service.ProjectExperienceService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public Result<List<ProjectExperienceVO>> query(String pkApplicant) {
        QueryWrapper<ProjectExperience> wrapper = new QueryWrapper<>();
        wrapper.eq("pk_applicant", pkApplicant);
        List<ProjectExperience> projectExperienceList = projectExperienceMapper.selectList(wrapper);
        List<ProjectExperienceVO> projectExperienceVOList = new ArrayList<>();
        for (ProjectExperience projectExperience : projectExperienceList) {
            ProjectExperienceVO projectExperienceVO = new ProjectExperienceVO(projectExperience);
            projectExperienceVOList.add(projectExperienceVO);
        }
        return Result.ok(projectExperienceVOList);
    }

    /**
     * 添加项目经历
     */
    @Override
    public Result<ProjectExperienceVO> insert(ProjectExperienceVO projectExperienceVO) {
        if (projectExperienceVO == null) {
            return Result.paramError(BizCode.PLEASE_WRITE);
        }
        ProjectExperience projectExperience = new ProjectExperience();
        projectExperience.setPkApplicant(Long.valueOf(projectExperienceVO.getPkApplicant()));
        projectExperience.setProjectName(projectExperienceVO.getProjectName());
        projectExperience.setProjectRole(projectExperienceVO.getProjectRole());
        projectExperience.setStartTime(projectExperienceVO.getStartTime());
        projectExperience.setEndTime(projectExperienceVO.getEndTime());
        projectExperience.setDescription(projectExperienceVO.getDescription());
        if (save(projectExperience)) {
            ProjectExperienceVO projectExperienceVOData = new ProjectExperienceVO(projectExperience);
            return Result.ok(projectExperienceVOData);

        }
        return Result.error();
    }

    /**
     * 修改项目经历
     */
    @Override
    public Result<ProjectExperienceVO> update(ProjectExperienceVO projectExperienceVO) {
        if (projectExperienceVO == null) {
            return Result.ok();
        }
        ProjectExperience projectExperience = new ProjectExperience();
        projectExperience.setId(Long.valueOf(projectExperienceVO.getId()));
        projectExperience.setPkApplicant(Long.valueOf(projectExperienceVO.getPkApplicant()));
        projectExperience.setProjectName(projectExperienceVO.getProjectName());
        projectExperience.setProjectRole(projectExperienceVO.getProjectRole());
        projectExperience.setStartTime(projectExperienceVO.getStartTime());
        projectExperience.setEndTime(projectExperienceVO.getEndTime());
        projectExperience.setDescription(projectExperienceVO.getDescription());
        if (updateById(projectExperience)) {
            return Result.ok(projectExperienceVO);
        }
        return Result.error();
    }

    /**
     * 根据 id 删除项目经历
     */
    @Override
    public Result<Void> delete(String id) {
        if (!removeById(id)) {
            return Result.error();
        }
        return Result.ok();
    }
}
