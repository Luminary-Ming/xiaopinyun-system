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
        if (aid == null || aid < 1) {
            return Result.fail(BizCode.FAIL);
        }
        QueryWrapper<EducationalBackground> wrapper = new QueryWrapper<>();
        wrapper.eq("aid", aid);
        List<EducationalBackground> educationalBackgrounds = backgroundMapper.selectList(wrapper);
        List<EducationalBackgroundVO> educationalBackgroundVOS = new ArrayList<>();
        for (EducationalBackground educationalBackground : educationalBackgrounds) {
            EducationalBackgroundVO educationalBackgroundVO = new EducationalBackgroundVO(educationalBackground);
            educationalBackgroundVOS.add(educationalBackgroundVO);
        }
        return Result.success(BizCode.SELECT_SUCCESS, educationalBackgroundVOS);
    }

    /**
     * 添加教育背景
     */
    @Override
    public Result<EducationalBackgroundVO> saveVO(EducationalBackground background) {
        if (background == null) {
            return Result.fail(BizCode.ADD_NULL);
        }
        // 校验对象字段
        if (check(background).getCode() != 2000) {
            return check(background);
        }
        if (save(background)) {
            EducationalBackground educationalBackground = backgroundMapper.selectById(background.getId());
            EducationalBackgroundVO educationalBackgroundVO = new EducationalBackgroundVO(educationalBackground);
            return Result.success(BizCode.ADD_SUCCESS, educationalBackgroundVO);
        }
        return Result.fail(BizCode.ADD_FAIL);
    }

    /**
     * 修改教育背景
     */
    @Override
    public Result<EducationalBackgroundVO> updateVO(EducationalBackground background) {
        // 没有更新数据直接返回成功
        if (background == null) {
            return Result.success(BizCode.UPDATE_SUCCESS);
        }
        if (updateById(background)) {
            EducationalBackground educationalBackground = backgroundMapper.selectById(background.getId());
            EducationalBackgroundVO educationalBackgroundVO = new EducationalBackgroundVO(educationalBackground);
            return Result.success(BizCode.UPDATE_SUCCESS, educationalBackgroundVO);
        }
        return Result.fail(BizCode.UPDATE_FAIL);
    }

    /**
     * 根据 id 删除教育背景
     */
    @Override
    public Result<Void> deleteVOById(Integer id) {
        if (id == null || id < 1) {
            return Result.fail(BizCode.FAIL);
        }
        if (removeById(id)) {
            return Result.success(BizCode.DELETE_SUCCESS);
        }
        return Result.fail(BizCode.DELETE_FAIL);
    }

    /**
     * 校验字段
     */
    private Result<EducationalBackgroundVO> check(EducationalBackground background) {
        // 校验学历
        if (!Pattern.matches("[0-6]", background.getQualification().toString())) {
            return Result.fail(BizCode.QUALIFICATION_FORMAT_FAIL);
        }
        // 入学时间
        if (!Pattern.matches("[0-9]{4}", background.getStartTime())) {
            return Result.fail(BizCode.TIME_FORMAT_FAIL);
        }
        // 毕业时间
        if (!Pattern.matches("[0-9]{4}", background.getEndTime())) {
            return Result.fail(BizCode.TIME_FORMAT_FAIL);
        }
        return Result.success(BizCode.SUCCESS);
    }
}
