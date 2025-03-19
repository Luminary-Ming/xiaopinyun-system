package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.Educational;
import com.xiaopinyun.bean.vo.EducationalVO;

import java.util.List;

public interface EducationalService extends IService<Educational> {

    /**
     * 根据学生（aid）查询教育背景
     */
    Result<List<EducationalVO>> queryVOByAid(Integer aid);

    /**
     * 添加教育背景
     */
    Result<EducationalVO> saveVO(Educational background);

    /**
     * 修改教育背景
     */
    Result<EducationalVO> updateVO(Educational background);

    /**
     * 根据 id 删除教育背景
     */
    Result<Void> deleteVOById(Integer id);
}
