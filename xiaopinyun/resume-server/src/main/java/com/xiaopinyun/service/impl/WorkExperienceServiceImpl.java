package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.WorkExperience;
import com.xiaopinyun.bean.vo.WorkExperienceVO;
import com.xiaopinyun.mapper.WorkExperienceMapper;
import com.xiaopinyun.service.WorkExperienceService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 张金龙
 * @Date 2025/2/19 10:42
 * @Version 1.0
 */
@Service
public class WorkExperienceServiceImpl extends ServiceImpl<WorkExperienceMapper, WorkExperience> implements WorkExperienceService {
    @Autowired
    private WorkExperienceMapper workExperienceMapper;

    /**
     * 根据 id 查询工作经历
     */
    @Override
    public Result<List<WorkExperienceVO>> query(String pkApplicant) {
        QueryWrapper<WorkExperience> wrapper = new QueryWrapper<>();
        wrapper.eq("pk_applicant", pkApplicant);
        List<WorkExperience> workExperienceList = workExperienceMapper.selectList(wrapper);
        List<WorkExperienceVO> workExperienceVOList = new ArrayList<>();
        for (WorkExperience workExperience : workExperienceList) {
            WorkExperienceVO workExperienceVO = new WorkExperienceVO(workExperience);
            workExperienceVOList.add(workExperienceVO);
        }
        return Result.ok(workExperienceVOList);
    }

    /**
     * 添加工作经历
     */
    @Override
    public Result<WorkExperienceVO> insert(WorkExperienceVO workExperienceVO) {
        if (workExperienceVO == null) {
            return Result.paramError(BizCode.PLEASE_WRITE);
        }
        WorkExperience workExperience = new WorkExperience();
        workExperience.setPkApplicant(Long.valueOf(workExperienceVO.getPkApplicant()));
        workExperience.setCompanyName(workExperienceVO.getCompanyName());
        workExperience.setIndustryType(workExperienceVO.getIndustryType());
        workExperience.setDepartment(workExperienceVO.getDepartment());
        workExperience.setJobName(workExperienceVO.getJobName());
        workExperience.setStartTime(workExperienceVO.getStartTime());
        workExperience.setEndTime(workExperienceVO.getEndTime());
        workExperience.setJobContent(workExperienceVO.getJobContent());
        if (!save(workExperience)) {
            return Result.error();
        }
        WorkExperienceVO workExperienceVOData = new WorkExperienceVO(workExperience);
        return Result.ok(workExperienceVOData);
    }

    /**
     * 修改工作经历
     */
    @Override
    public Result<WorkExperienceVO> update(WorkExperienceVO workExperienceVO) {
        if (workExperienceVO == null) {
            return Result.ok();
        }
        WorkExperience workExperience = new WorkExperience();
        workExperience.setId(Long.valueOf(workExperienceVO.getId()));
        workExperience.setPkApplicant(Long.valueOf(workExperienceVO.getPkApplicant()));
        workExperience.setCompanyName(workExperienceVO.getCompanyName());
        workExperience.setIndustryType(workExperienceVO.getIndustryType());
        workExperience.setDepartment(workExperienceVO.getDepartment());
        workExperience.setJobName(workExperienceVO.getJobName());
        workExperience.setStartTime(workExperienceVO.getStartTime());
        workExperience.setEndTime(workExperienceVO.getEndTime());
        workExperience.setJobContent(workExperienceVO.getJobContent());
        if (!updateById(workExperience)) {
            return Result.error();
        }
        return Result.ok(workExperienceVO);
    }

    /**
     * 根据 id 删除工作经历
     */
    @Override
    public Result<Void> delete(String id) {
        if (!removeById(id)) {
            return Result.error();
        }
        return Result.ok();
    }
}
