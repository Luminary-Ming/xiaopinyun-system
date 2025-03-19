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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class EducationalServiceImpl extends ServiceImpl<EducationalMapper, Educational> implements EducationalService {
    @Autowired
    private EducationalMapper backgroundMapper;

    /**
     * 根据学生（aid）查询教育背景
     */
    @Override
    public Result<List<EducationalVO>> queryVOByAid(Integer aid) {
        QueryWrapper<Educational> wrapper = new QueryWrapper<>();
        wrapper.eq("aid", aid);
        List<Educational> educationalBackgrounds = backgroundMapper.selectList(wrapper);
        List<EducationalVO> educationalVOS = new ArrayList<>();
        for (Educational educationalBackground : educationalBackgrounds) {
            EducationalVO educationalVO = new EducationalVO(educationalBackground);
            educationalVOS.add(educationalVO);
        }
        return Result.ok(educationalVOS);
    }

    /**
     * 添加教育背景
     */
    @Override
    public Result<EducationalVO> saveVO(Educational background) {
        if (background == null) {
            return Result.paramError(BizCode.PLEASE_WRITE);
        }
        // 校验对象字段
        if (check(background).isSuccess()) {
            return check(background);
        }
        if (save(background)) {
            Educational educationalBackground = backgroundMapper.selectById(background.getId());
            EducationalVO educationalVO = new EducationalVO(educationalBackground);
            return Result.ok(educationalVO);
        }
        return Result.error();
    }

    /**
     * 修改教育背景
     */
    @Override
    public Result<EducationalVO> updateVO(Educational background) {
        // 没有更新数据直接返回成功
        if (background == null) {
            return Result.ok();
        }
        // 校验对象字段
        if (check(background).isSuccess()) {
            return check(background);
        }
        if (updateById(background)) {
            Educational educationalBackground = backgroundMapper.selectById(background.getId());
            EducationalVO educationalVO = new EducationalVO(educationalBackground);
            return Result.ok(educationalVO);
        }
        return Result.error();
    }

    /**
     * 根据 id 删除教育背景
     */
    @Override
    public Result<Void> deleteVOById(Integer id) {
        if (removeById(id)) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 校验字段
     */
    private Result<EducationalVO> check(Educational background) {
        // 校验学历
        if (!Pattern.matches("[0-6]", background.getQualification().toString())) {
            return Result.paramError("学历格式不正确");
        }
        // 入学时间
        if (!Pattern.matches("[0-9]{4}", background.getStartTime())) {
            return Result.paramError("时间格式不正确");
        }
        // 毕业时间
        if (!Pattern.matches("[0-9]{4}", background.getEndTime())) {
            return Result.paramError("时间格式不正确");
        }
        return Result.ok();
    }
}
