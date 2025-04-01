package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.dto.JobCollectDTO;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.Applicant;
import com.xiaopinyun.bean.po.Company;
import com.xiaopinyun.bean.po.HR;
import com.xiaopinyun.bean.po.JobCollect;
import com.xiaopinyun.bean.po.Recruit;
import com.xiaopinyun.bean.vo.ApplicantVO;
import com.xiaopinyun.bean.vo.CompanyVO;
import com.xiaopinyun.bean.vo.HRVO;
import com.xiaopinyun.bean.vo.JobCollectVO;
import com.xiaopinyun.bean.vo.RecruitVO;
import com.xiaopinyun.mapper.ApplicantInfoMapper;
import com.xiaopinyun.mapper.CompanyInfoMapper;
import com.xiaopinyun.mapper.HRInfoMapper;
import com.xiaopinyun.mapper.JobCollectMapper;
import com.xiaopinyun.mapper.RecruitMapper;
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
    private RecruitMapper recruitMapper;
    @Autowired
    private HRInfoMapper hrInfoMapper;
    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    @Override
    public Result<List<JobCollectDTO>> query(String pkApplicant) {
        QueryWrapper<JobCollect> Wrapper = new QueryWrapper<>();
        Wrapper.eq("pk_applicant", pkApplicant);
        List<JobCollect> jobCollects = jobCollectMapper.selectList(Wrapper);
        if (!jobCollects.isEmpty()) {
            List<JobCollectDTO> JobCollectDTOList = new ArrayList<>();
            for (JobCollect jobCollect : jobCollects) {
                JobCollectDTO jobCollectDTO = new JobCollectDTO();
                jobCollectDTO.setId(jobCollect.getId().toString());
                Applicant applicant = applicantInfoMapper.selectById(jobCollect.getPkApplicant());
                jobCollectDTO.setApplicantVO(new ApplicantVO(applicant));

                Recruit recruit = recruitMapper.selectById(jobCollect.getPkRecruit());
                jobCollectDTO.setRecruitVO(new RecruitVO(recruit));

                HR hr = hrInfoMapper.selectById(recruit.getPkHr());
                jobCollectDTO.setHrvo(new HRVO(hr));

                Company company = companyInfoMapper.selectById(recruit.getPkCompany());
                jobCollectDTO.setCompanyVO(new CompanyVO(company));
                JobCollectDTOList.add(jobCollectDTO);
            }
            return Result.ok(JobCollectDTOList);
        }
        return Result.ok(BizCode.NO_DATA);
    }

    @Override
    public Result<JobCollectDTO> insert(JobCollectVO jobCollectVO) {
        JobCollect jobCollect = new JobCollect();
        jobCollect.setPkApplicant(Long.valueOf(jobCollectVO.getPkApplicant()));
        jobCollect.setPkRecruit(Long.valueOf(jobCollectVO.getPkRecruit()));
        // 是否已经收藏
        QueryWrapper<JobCollect> Wrapper = new QueryWrapper<>();
        Wrapper.eq("pk_applicant", jobCollectVO.getPkApplicant()).eq("pk_recruit", jobCollectVO.getPkRecruit());
        JobCollect jobCollectData = jobCollectMapper.selectOne(Wrapper);
        if (jobCollectData != null) {
            return Result.error("该职位已经收藏了！");
        }
        if (save(jobCollect)) {
            JobCollectDTO jobCollectDTO = new JobCollectDTO();
            jobCollectDTO.setId(jobCollect.getId().toString());
            Applicant applicant = applicantInfoMapper.selectById(jobCollect.getPkApplicant());
            jobCollectDTO.setApplicantVO(new ApplicantVO(applicant));
            Recruit recruit = recruitMapper.selectById(jobCollect.getPkRecruit());
            jobCollectDTO.setRecruitVO(new RecruitVO(recruit));
            HR hr = hrInfoMapper.selectById(recruit.getPkHr());
            jobCollectDTO.setHrvo(new HRVO(hr));
            Company company = companyInfoMapper.selectById(recruit.getPkCompany());
            jobCollectDTO.setCompanyVO(new CompanyVO(company));
            return Result.ok(jobCollectDTO);
        }
        return Result.error();
    }

    @Override
    public Result<Void> delete(String id) {
        if (removeById(id)) {
            return Result.ok();
        }
        return Result.error();
    }
}