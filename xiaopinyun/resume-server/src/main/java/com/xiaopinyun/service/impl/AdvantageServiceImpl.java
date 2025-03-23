package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.Advantage;
import com.xiaopinyun.bean.vo.AdvantageVO;
import com.xiaopinyun.mapper.AdvantageMapper;
import com.xiaopinyun.service.AdvantageService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdvantageServiceImpl extends ServiceImpl<AdvantageMapper, Advantage> implements AdvantageService {
    @Autowired
    private AdvantageMapper advantageMapper;

    @Override
    public Result<AdvantageVO> query(String pkApplicant) {
        QueryWrapper<Advantage> wrapper = new QueryWrapper<>();
        wrapper.eq("pk_applicant", pkApplicant);
        Advantage advantage = advantageMapper.selectOne(wrapper);
        if (advantage != null) {
            return Result.ok(new AdvantageVO(advantage));
        }
        return Result.ok(BizCode.NO_DATA);
    }

    @Override
    public Result<AdvantageVO> update(AdvantageVO advantageVO) {
        if (advantageVO == null) {
            return Result.ok();
        }
        Advantage advantage = new Advantage();
        advantage.setId(Long.valueOf(advantageVO.getId()));
        advantage.setPkApplicant(Long.valueOf(advantageVO.getPkApplicant()));
        advantage.setMajorSkill(advantageVO.getMajorSkill());
        advantage.setHonor(advantageVO.getHonor());
        advantage.setSelfEvaluation(advantageVO.getSelfEvaluation());
        if(updateById(advantage)) {
            return Result.ok(advantageVO);
        }
        return Result.error();
    }
}
