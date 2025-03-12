package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.po.EducationalBackground;
import com.xiaopinyun.bean.vo.EducationalBackgroundVO;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.mapper.EducationalBackgroundMapper;
import com.xiaopinyun.service.EducationalBackgroundService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class EducationalBackgroundServiceImpl extends ServiceImpl<EducationalBackgroundMapper, EducationalBackground> implements EducationalBackgroundService {
    @Autowired
    private EducationalBackgroundMapper backgroundMapper;

    /**
     * 根据学生（aid）查询教育背景
     */
    @Override
    public Result<List<EducationalBackgroundVO>> queryVOByAid(Integer aid) {
        QueryWrapper<EducationalBackground> wrapper = new QueryWrapper<>();
        wrapper.eq("aid", aid);
        List<EducationalBackground> educationalBackgrounds = backgroundMapper.selectList(wrapper);
        List<EducationalBackgroundVO> educationalBackgroundVOS = new ArrayList<>();
        for (EducationalBackground educationalBackground : educationalBackgrounds) {
            EducationalBackgroundVO educationalBackgroundVO = new EducationalBackgroundVO(educationalBackground);
            educationalBackgroundVOS.add(educationalBackgroundVO);
        }
        return Result.ok(educationalBackgroundVOS);
    }

    /**
     * 添加教育背景
     */
    @Override
    public Result<EducationalBackgroundVO> saveVO(EducationalBackground background) {
        if (background == null) {
            return Result.paramError(BizCode.PLEASE_WRITE);
        }
        // 校验对象字段
        if (check(background).isSuccess()) {
            return check(background);
        }
        if (save(background)) {
            EducationalBackground educationalBackground = backgroundMapper.selectById(background.getId());
            EducationalBackgroundVO educationalBackgroundVO = new EducationalBackgroundVO(educationalBackground);
            return Result.ok(educationalBackgroundVO);
        }
        return Result.error();
    }

    /**
     * 修改教育背景
     */
    @Override
    public Result<EducationalBackgroundVO> updateVO(EducationalBackground background) {
        // 没有更新数据直接返回成功
        if (background == null) {
            return Result.ok();
        }
        // 校验对象字段
        if (check(background).isSuccess()) {
            return check(background);
        }
        if (updateById(background)) {
            EducationalBackground educationalBackground = backgroundMapper.selectById(background.getId());
            EducationalBackgroundVO educationalBackgroundVO = new EducationalBackgroundVO(educationalBackground);
            return Result.ok(educationalBackgroundVO);
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
    private Result<EducationalBackgroundVO> check(EducationalBackground background) {
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
