package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.Educational;
import com.xiaopinyun.bean.vo.EducationalVO;

public interface EducationalService extends IService<Educational> {

    /**
     * 查询教育背景
     */
    Result<EducationalVO> query(String pkApplicant);


    /**
     * 修改教育背景
     */
    Result<EducationalVO> update(EducationalVO backgroundVO);

}
