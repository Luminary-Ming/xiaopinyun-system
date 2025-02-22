package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.po.WorkExperience;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.bean.vo.WorkExperienceVO;

/**
 * @Author 张金龙
 * @Date 2025/2/19 10:42
 * @Version 1.0
 */
public interface WorkExperienceService extends IService<WorkExperience> {
    /**
     * 根据 id 查询工作经历
     */
    Result<WorkExperienceVO> queryVOById(Integer id);

    /**
     * 添加工作经历
     */
    Result<WorkExperienceVO> saveVO(WorkExperience workExperience);

    /**
     * 修改工作经历
     */
    Result<WorkExperienceVO> updateVO(WorkExperience workExperience);

    /**
     * 根据 id 删除工作经历
     */
    Result<Void> deleteVOById(Integer id);
}
