package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.Educational;
import com.xiaopinyun.bean.vo.EducationalVO;
import com.xiaopinyun.mapper.EducationalMapper;
import com.xiaopinyun.service.EducationalService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationalServiceImpl extends ServiceImpl<EducationalMapper, Educational> implements EducationalService {
    @Autowired
    private EducationalMapper backgroundMapper;

    /**
     * 查询教育背景
     */
    @Override
    public Result<EducationalVO> query(String pkApplicant) {
        QueryWrapper<Educational> wrapper = new QueryWrapper<>();
        wrapper.eq("pk_applicant", pkApplicant);
        Educational educational = backgroundMapper.selectOne(wrapper);
        if (educational != null) {
            EducationalVO educationalVO = new EducationalVO(educational);
            return Result.ok(educationalVO);
        }
        return Result.ok(BizCode.NO_DATA);
    }


    /**
     * 修改教育背景
     */
    @Override
    public Result<EducationalVO> update(EducationalVO educationalVO) {
        // 没有更新数据直接返回成功
        if (educationalVO == null) {
            return Result.ok();
        }
        Educational educational = new Educational();
        educational.setId(Long.valueOf(educationalVO.getId()));
        educational.setPkApplicant(Long.valueOf(educationalVO.getPkApplicant()));
        educational.setName(educationalVO.getName());
        educational.setQualification(Integer.valueOf(educationalVO.getQualification()));
        educational.setMajor(educationalVO.getMajor());
        educational.setStartTime(educationalVO.getStartTime());
        educational.setEndTime(educationalVO.getEndTime());
        educational.setMajorCourse(educationalVO.getMajorCourse());
        if (updateById(educational)) {
            return Result.ok(educationalVO);
        }
        return Result.error();
    }
}
