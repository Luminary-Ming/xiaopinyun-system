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
     * 查询求职期望
     */
    @Override
    public Result<List<JobExpectationVO>> query(String pkApplicant) {
        QueryWrapper<JobExpectation> wrapper = new QueryWrapper<>();
        wrapper.eq("pk_applicant", pkApplicant);
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
    public Result<JobExpectationVO> insert(JobExpectationVO jobExpectationVO) {
        if (jobExpectationVO == null) {
            return Result.paramError(BizCode.PLEASE_WRITE);
        }
        JobExpectation jobExpectation = new JobExpectation();
        jobExpectation.setPkApplicant(Long.valueOf(jobExpectationVO.getPkApplicant()));
        jobExpectation.setJobType(jobExpectationVO.getJobType());
        jobExpectation.setExpectedIndustry(jobExpectationVO.getExpectedIndustry());
        jobExpectation.setDistrict(jobExpectationVO.getDistrict());
        jobExpectation.setExpectedJob(jobExpectationVO.getExpectedJob());
        jobExpectation.setSalary(jobExpectationVO.getSalary());
        if (save(jobExpectation)) {
            JobExpectationVO jobExpectationVOData = new JobExpectationVO(jobExpectation);
            return Result.ok(jobExpectationVOData);
        }
        return Result.error();
    }

    /**
     * 修改求职期望
     */
    @Override
    public Result<JobExpectationVO> update(JobExpectationVO jobExpectationVO) {
        // 如果没有更新数据直接返回更新成功
        if (jobExpectationVO == null) {
            return Result.ok();
        }
        JobExpectation jobExpectation = new JobExpectation();
        jobExpectation.setId(Long.valueOf(jobExpectationVO.getId()));
        jobExpectation.setPkApplicant(Long.valueOf(jobExpectationVO.getPkApplicant()));
        jobExpectation.setJobType(jobExpectationVO.getJobType());
        jobExpectation.setExpectedIndustry(jobExpectationVO.getExpectedIndustry());
        jobExpectation.setDistrict(jobExpectationVO.getDistrict());
        jobExpectation.setExpectedJob(jobExpectationVO.getExpectedJob());
        jobExpectation.setSalary(jobExpectationVO.getSalary());
        if (updateById(jobExpectation)) {
            return Result.ok(jobExpectationVO);
        }
        return Result.error();
    }

    /**
     * 删除求职期望
     */
    @Override
    public Result<Void> delete(String id) {
        if (removeById(id)) {
            return Result.ok();
        }
        return Result.error();
    }
}
