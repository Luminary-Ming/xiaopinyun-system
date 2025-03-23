package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.WorkExperience;
import com.xiaopinyun.bean.vo.WorkExperienceVO;

import java.util.List;

/**
 * @Author 张金龙
 * @Date 2025/2/19 10:42
 * @Version 1.0
 */
public interface WorkExperienceService extends IService<WorkExperience> {
    /**
     * 查询工作经历
     */
    Result<List<WorkExperienceVO>> query(String pkApplicant);

    /**
     * 添加工作经历
     */
    Result<WorkExperienceVO> insert(WorkExperienceVO workExperienceVO);

    /**
     * 修改工作经历
     */
    Result<WorkExperienceVO> update(WorkExperienceVO workExperienceVO);

    /**
     * 删除工作经历
     */
    Result<Void> delete(String id);
}
