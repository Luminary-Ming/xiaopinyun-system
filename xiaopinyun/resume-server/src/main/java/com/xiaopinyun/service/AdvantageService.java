package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.Advantage;
import com.xiaopinyun.bean.vo.AdvantageVO;

public interface AdvantageService extends IService<Advantage> {
    Result<AdvantageVO> query(String pkApplicant);
    Result<AdvantageVO> update(AdvantageVO advantageVO);
}
