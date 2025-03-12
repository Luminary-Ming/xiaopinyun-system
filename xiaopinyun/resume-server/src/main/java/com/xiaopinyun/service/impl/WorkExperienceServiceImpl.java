package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.po.WorkExperience;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.bean.vo.WorkExperienceVO;
import com.xiaopinyun.mapper.WorkExperienceMapper;
import com.xiaopinyun.service.WorkExperienceService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Result<WorkExperienceVO> queryVOById(Integer id) {
        WorkExperience workExperience = workExperienceMapper.selectById(id);
        if (workExperience == null) {
            return Result.ok(BizCode.NO_DATA);
        }
        WorkExperienceVO workExperienceVO = new WorkExperienceVO(workExperience);
        return Result.ok(workExperienceVO);
    }

    /**
     * 添加工作经历
     */
    @Override
    public Result<WorkExperienceVO> saveVO(WorkExperience workExperience) {
        if (workExperience == null) {
            return Result.paramError(BizCode.PLEASE_WRITE);
        }
        if (!save(workExperience)) {
            return Result.error();
        }
        WorkExperience workExperienceData = workExperienceMapper.selectById(workExperience.getId());
        WorkExperienceVO workExperienceVO = new WorkExperienceVO(workExperienceData);
        return Result.ok(workExperienceVO);
    }

    /**
     * 修改工作经历
     */
    @Override
    public Result<WorkExperienceVO> updateVO(WorkExperience workExperience) {
        if (workExperience == null) {
            return Result.ok();
        }
        if (!updateById(workExperience)) {
            return Result.error();
        }
        WorkExperience workExperienceData = workExperienceMapper.selectById(workExperience.getId());
        WorkExperienceVO workExperienceVO = new WorkExperienceVO(workExperienceData);
        return Result.ok(workExperienceVO);
    }

    /**
     * 根据 id 删除工作经历
     */
    @Override
    public Result<Void> deleteVOById(Integer id) {
        if (!removeById(id)) {
            return Result.error();
        }
        return Result.ok();
    }
}
