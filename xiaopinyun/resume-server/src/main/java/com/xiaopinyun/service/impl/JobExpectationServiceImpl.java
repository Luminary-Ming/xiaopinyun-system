package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.JobExpectation;
import com.xiaopinyun.bean.vo.JobExpectationVO;
import com.xiaopinyun.mapper.JobExpectationMapper;
import com.xiaopinyun.service.JobExpectationService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobExpectationServiceImpl extends ServiceImpl<JobExpectationMapper, JobExpectation> implements JobExpectationService {
    @Autowired
    private JobExpectationMapper jobExpectationMapper;

    /**
     * 根据学生（aid）查询求职期望
     */
    @Override
    public Result<List<JobExpectationVO>> queryVOByAid(Integer aid) {
        QueryWrapper<JobExpectation> wrapper = new QueryWrapper<>();
        wrapper.eq("aid", aid);
        List<JobExpectation> jobExpectationList = jobExpectationMapper.selectList(wrapper);
        List<JobExpectationVO> jobExpectationVOList = new ArrayList<>();
        for (JobExpectation jobExpectation : jobExpectationList) {
            JobExpectationVO jobExpectationVO = new JobExpectationVO(jobExpectation);
            jobExpectationVOList.add(jobExpectationVO);
        }
        return Result.ok(jobExpectationVOList);
    }

    /**
     * 添加求职期望
     */
    @Override
    public Result<JobExpectationVO> saveVO(JobExpectation jobExpectation) {
        if (jobExpectation == null) {
            return Result.paramError(BizCode.PLEASE_WRITE);
        }
        if (save(jobExpectation)) {
            JobExpectation jobExpectationData = jobExpectationMapper.selectById(jobExpectation.getId());
            JobExpectationVO jobExpectationVO = new JobExpectationVO(jobExpectationData);
            return Result.ok(jobExpectationVO);
        }
        return Result.error();
    }

    /**
     * 修改求职期望
     */
    @Override
    public Result<JobExpectationVO> updateVO(JobExpectation jobExpectation) {
        // 如果没有更新数据直接返回更新成功
        if (jobExpectation == null) {
            return Result.ok();
        }
        if (updateById(jobExpectation)) {
            JobExpectation jobExpectationData = jobExpectationMapper.selectById(jobExpectation.getId());
            JobExpectationVO jobExpectationVO = new JobExpectationVO(jobExpectationData);
            return Result.ok(jobExpectationVO);
        }
        return Result.error();
    }

    /**
     * 根据 id 删除求职期望
     */
    @Override
    public Result<Void> deleteVOById(Integer id) {
        if (removeById(id)) {
            return Result.ok();
        }
        return Result.error();
    }
}
