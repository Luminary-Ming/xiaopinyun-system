package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.po.EducationalBackground;
import com.xiaopinyun.bean.vo.EducationalBackgroundVO;
import com.xiaopinyun.bean.vo.Result;

import java.util.List;

public interface EducationalBackgroundService extends IService<EducationalBackground> {

    /**
     * 根据学生（aid）查询教育背景
     */
    Result<List<EducationalBackgroundVO>> queryVOByAid(Integer aid);

    /**
     * 添加教育背景
     */
    Result<EducationalBackgroundVO> saveVO(EducationalBackground background);

    /**
     * 修改教育背景
     */
    Result<EducationalBackgroundVO> updateVO(EducationalBackground background);

    /**
     * 根据 id 删除教育背景
     */
    Result<Void> deleteVOById(Integer id);
}
