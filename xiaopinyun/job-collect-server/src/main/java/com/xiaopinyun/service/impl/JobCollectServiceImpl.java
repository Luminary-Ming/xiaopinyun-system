package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.dto.JobCollectDTO;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.JobCollect;
import com.xiaopinyun.bean.vo.JobCollectVO;
import com.xiaopinyun.mapper.ApplicantInfoMapper;
import com.xiaopinyun.mapper.JobCollectMapper;
import com.xiaopinyun.service.JobCollectService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class JobCollectServiceImpl extends ServiceImpl<JobCollectMapper, JobCollect> implements JobCollectService {
    @Autowired
    private JobCollectMapper jobCollectMapper;
    @Autowired
    private ApplicantInfoMapper applicantInfoMapper;
    @Autowired
    private

    @Override
    public Result<List<JobCollectDTO>> query(String pkApplicant) {
        QueryWrapper<JobCollect> Wrapper = new QueryWrapper<>();
        Wrapper.eq("pk_applicant",pkApplicant);
        List<JobCollect> jobCollects = jobCollectMapper.selectList(Wrapper);
        if (!jobCollects.isEmpty()) {
            List<JobCollectDTO> JobCollectDTOList = new ArrayList<>();
            for (JobCollect jobCollect : jobCollects) {
                JobCollectDTO jobCollectDTO = new JobCollectDTO();
                jobCollectDTO.setId(jobCollect.getId().toString());

                jobCollect.getPkApplicant();
//                jobCollectDTO.setApplicantVO();
//
//                jobCollectDTO.setRecruitVO();
//
//                jobCollectDTO.setHrvo();
//
//                jobCollectDTO.setCompanyVO();
                JobCollectDTOList.add(jobCollectDTO);
            }
            return Result.ok(JobCollectDTOList);
        }
        return Result.ok(BizCode.NO_DATA);
    }

    @Override
    public Result<Void> insert(JobCollectVO jobCollectVO) {
        return null;
    }

    @Override
    public Result<Void> delete(String id) {
        return null;
    }
}