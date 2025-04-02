package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.dto.ResumeSubmitDTO;
import com.xiaopinyun.bean.dto.SubmitInfoDTO;
import com.xiaopinyun.bean.po.Advantage;
import com.xiaopinyun.bean.po.Applicant;
import com.xiaopinyun.bean.po.Company;
import com.xiaopinyun.bean.po.Educational;
import com.xiaopinyun.bean.po.HR;
import com.xiaopinyun.bean.po.Recruit;
import com.xiaopinyun.bean.po.ResumeSubmit;
import com.xiaopinyun.bean.vo.AdvantageVO;
import com.xiaopinyun.bean.vo.ApplicantVO;
import com.xiaopinyun.bean.vo.CompanyVO;
import com.xiaopinyun.bean.vo.EducationalVO;
import com.xiaopinyun.bean.vo.HRVO;
import com.xiaopinyun.bean.vo.RecruitVO;
import com.xiaopinyun.bean.vo.ResumeSubmitVO;
import com.xiaopinyun.mapper.AdvantageMapper;
import com.xiaopinyun.mapper.ApplicantInfoMapper;
import com.xiaopinyun.mapper.CompanyInfoMapper;
import com.xiaopinyun.mapper.EducationalMapper;
import com.xiaopinyun.mapper.HRInfoMapper;
import com.xiaopinyun.mapper.RecruitMapper;
import com.xiaopinyun.mapper.ResumeSubmitMapper;
import com.xiaopinyun.service.ResumeSubmitService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeSubmitServiceImpl extends ServiceImpl<ResumeSubmitMapper, ResumeSubmit> implements ResumeSubmitService {
    @Autowired
    private ResumeSubmitMapper resumeSubmitMapper;
    @Autowired
    private ApplicantInfoMapper applicantInfoMapper;
    @Autowired
    private HRInfoMapper hrInfoMapper;
    @Autowired
    private CompanyInfoMapper companyInfoMapper;
    @Autowired
    private RecruitMapper recruitMapper;
    @Autowired
    private EducationalMapper educationalMapper;
    @Autowired
    private AdvantageMapper advantageMapper;

    @Override
    public Result<List<ResumeSubmitDTO>> queryByPkApplicant(String pkApplicant) {
        QueryWrapper<ResumeSubmit> wrapper = new QueryWrapper<>();
        wrapper.eq("pk_applicant", pkApplicant);
        List<ResumeSubmit> resumeSubmits = resumeSubmitMapper.selectList(wrapper);
        if (resumeSubmits.isEmpty()) {
            return Result.ok(BizCode.NO_DATA);
        }
        List<ResumeSubmitDTO> resumeSubmitDTOList = new ArrayList<>();
        for (ResumeSubmit resumeSubmit : resumeSubmits) {
            ResumeSubmitDTO resumeSubmitDTO = new ResumeSubmitDTO();
            resumeSubmitDTO.setId(resumeSubmit.getId().toString());

            Recruit recruit = recruitMapper.selectById(resumeSubmit.getPkRecruit());
            resumeSubmitDTO.setRecruitVO(new RecruitVO(recruit));

            Applicant applicant = applicantInfoMapper.selectById(resumeSubmit.getPkApplicant());
            resumeSubmitDTO.setApplicantVO(new ApplicantVO(applicant));

            HR hr = hrInfoMapper.selectById(recruit.getPkHr());
            resumeSubmitDTO.setHrvo(new HRVO(hr));

            Company company = companyInfoMapper.selectById(recruit.getPkCompany());
            resumeSubmitDTO.setCompanyVO(new CompanyVO(company));
            if (resumeSubmit.getPkAttachment() != null) {
                resumeSubmitDTO.setPkAttachment(resumeSubmit.getPkAttachment().toString());
            }
            resumeSubmitDTO.setFilterStatus(resumeSubmit.getFilterStatus().toString());
            resumeSubmitDTO.setReply(resumeSubmit.getReply());
            resumeSubmitDTOList.add(resumeSubmitDTO);
        }
        return Result.ok(resumeSubmitDTOList);
    }


    @Override
    public Result<List<ResumeSubmitDTO>> queryReply(String pkApplicant) {
        QueryWrapper<ResumeSubmit> wrapper = new QueryWrapper<>();
        wrapper.eq("pk_applicant", pkApplicant).isNotNull("reply");
        List<ResumeSubmit> resumeSubmits = resumeSubmitMapper.selectList(wrapper);
        if (resumeSubmits.isEmpty()) {
            return Result.ok(BizCode.NO_DATA);
        }
        List<ResumeSubmitDTO> resumeSubmitDTOList = new ArrayList<>();
        for (ResumeSubmit resumeSubmit : resumeSubmits) {
            ResumeSubmitDTO resumeSubmitDTO = new ResumeSubmitDTO();
            resumeSubmitDTO.setId(resumeSubmit.getId().toString());

            Recruit recruit = recruitMapper.selectById(resumeSubmit.getPkRecruit());
            resumeSubmitDTO.setRecruitVO(new RecruitVO(recruit));

            Applicant applicant = applicantInfoMapper.selectById(resumeSubmit.getPkApplicant());
            resumeSubmitDTO.setApplicantVO(new ApplicantVO(applicant));

            HR hr = hrInfoMapper.selectById(recruit.getPkHr());
            resumeSubmitDTO.setHrvo(new HRVO(hr));

            Company company = companyInfoMapper.selectById(recruit.getPkCompany());
            resumeSubmitDTO.setCompanyVO(new CompanyVO(company));
            if (resumeSubmit.getPkAttachment() != null) {
                resumeSubmitDTO.setPkAttachment(resumeSubmit.getPkAttachment().toString());
            }
            resumeSubmitDTO.setFilterStatus(resumeSubmit.getFilterStatus().toString());
            resumeSubmitDTO.setReply(resumeSubmit.getReply());
            resumeSubmitDTOList.add(resumeSubmitDTO);
        }
        return Result.ok(resumeSubmitDTOList);
    }

    @Override
    public Result<List<SubmitInfoDTO>> querySubmitInfo(String pkHr) {
        QueryWrapper<Recruit> wrapper = new QueryWrapper<>();
        wrapper.eq("pk_hr", pkHr);
        List<Recruit> recruits = recruitMapper.selectList(wrapper);
        if (recruits.isEmpty()) {
            return Result.ok(BizCode.NO_DATA);
        }
        List<SubmitInfoDTO> submitInfoDTOList = new ArrayList<>();
        for (Recruit recruit : recruits) {
            SubmitInfoDTO submitInfoDTO = new SubmitInfoDTO();
            submitInfoDTO.setRecruitVO(new RecruitVO(recruit));

            QueryWrapper<ResumeSubmit> resumeSubmitQueryWrapper = new QueryWrapper<>();
            resumeSubmitQueryWrapper.eq("pk_recruit", recruit.getId()).ne("filter_status", 3);
            ResumeSubmit resumeSubmit = resumeSubmitMapper.selectOne(resumeSubmitQueryWrapper);
            if (resumeSubmit == null) {
                continue;
            }
            submitInfoDTO.setResumeSubmitVO(new ResumeSubmitVO(resumeSubmit));

            Applicant applicant = applicantInfoMapper.selectById(resumeSubmit.getPkApplicant());
            submitInfoDTO.setApplicantVO(new ApplicantVO(applicant));

            QueryWrapper<Educational> educationalQueryWrapper = new QueryWrapper<>();
            educationalQueryWrapper.eq("pk_applicant", applicant.getId());
            Educational educational = educationalMapper.selectOne(educationalQueryWrapper);
            submitInfoDTO.setEducationalVO(new EducationalVO(educational));

            QueryWrapper<Advantage> advantageQueryWrapper = new QueryWrapper<>();
            advantageQueryWrapper.eq("pk_applicant", applicant.getId());
            Advantage advantage = advantageMapper.selectOne(advantageQueryWrapper);
            submitInfoDTO.setAdvantageVO(new AdvantageVO(advantage));

            submitInfoDTOList.add(submitInfoDTO);
        }
        if (submitInfoDTOList.isEmpty()) {
            return Result.ok(BizCode.NO_DATA);
        }
        return Result.ok(submitInfoDTOList);
    }

    @Override
    public Result<List<SubmitInfoDTO>> queryInterest(String pkHr) {
        QueryWrapper<Recruit> wrapper = new QueryWrapper<>();
        wrapper.eq("pk_hr", pkHr);
        List<Recruit> recruits = recruitMapper.selectList(wrapper);
        if (recruits.isEmpty()) {
            return Result.ok(BizCode.NO_DATA);
        }
        List<SubmitInfoDTO> submitInfoDTOList = new ArrayList<>();
        for (Recruit recruit : recruits) {
            SubmitInfoDTO submitInfoDTO = new SubmitInfoDTO();
            submitInfoDTO.setRecruitVO(new RecruitVO(recruit));

            QueryWrapper<ResumeSubmit> resumeSubmitQueryWrapper = new QueryWrapper<>();
            resumeSubmitQueryWrapper.eq("pk_recruit", recruit.getId()).eq("filter_status", 3);
            ResumeSubmit resumeSubmit = resumeSubmitMapper.selectOne(resumeSubmitQueryWrapper);
            if (resumeSubmit == null) {
                continue;
            }
            submitInfoDTO.setResumeSubmitVO(new ResumeSubmitVO(resumeSubmit));

            Applicant applicant = applicantInfoMapper.selectById(resumeSubmit.getPkApplicant());
            submitInfoDTO.setApplicantVO(new ApplicantVO(applicant));

            QueryWrapper<Educational> educationalQueryWrapper = new QueryWrapper<>();
            educationalQueryWrapper.eq("pk_applicant", applicant.getId());
            Educational educational = educationalMapper.selectOne(educationalQueryWrapper);
            submitInfoDTO.setEducationalVO(new EducationalVO(educational));

            QueryWrapper<Advantage> advantageQueryWrapper = new QueryWrapper<>();
            advantageQueryWrapper.eq("pk_applicant", applicant.getId());
            Advantage advantage = advantageMapper.selectOne(advantageQueryWrapper);
            submitInfoDTO.setAdvantageVO(new AdvantageVO(advantage));

            submitInfoDTOList.add(submitInfoDTO);
        }
        if (submitInfoDTOList.isEmpty()) {
            return Result.ok(BizCode.NO_DATA);
        }
        return Result.ok(submitInfoDTOList);
    }

    @Override
    public Result<ResumeSubmitDTO> submit(ResumeSubmitVO resumeSubmitVO) {
        QueryWrapper<ResumeSubmit> wrapper = new QueryWrapper<>();
        wrapper.eq("pk_recruit", resumeSubmitVO.getPkRecruit()).eq("pk_applicant", resumeSubmitVO.getPkApplicant());
        ResumeSubmit resumeSubmitData = resumeSubmitMapper.selectOne(wrapper);
        if (resumeSubmitData != null) {
            return Result.error("该职位已投递过简历");
        }

        ResumeSubmit resumeSubmit = new ResumeSubmit();
        resumeSubmit.setPkRecruit(Long.valueOf(resumeSubmitVO.getPkRecruit()));
        resumeSubmit.setPkApplicant(Long.valueOf(resumeSubmitVO.getPkApplicant()));
        resumeSubmit.setFilterStatus(0);
        if (save(resumeSubmit)) {
            ResumeSubmitDTO resumeSubmitDTO = new ResumeSubmitDTO();
            resumeSubmitDTO.setId(resumeSubmit.getId().toString());

            Recruit recruit = recruitMapper.selectById(resumeSubmit.getPkRecruit());
            resumeSubmitDTO.setRecruitVO(new RecruitVO(recruit));

            Applicant applicant = applicantInfoMapper.selectById(resumeSubmit.getPkApplicant());
            resumeSubmitDTO.setApplicantVO(new ApplicantVO(applicant));

            HR hr = hrInfoMapper.selectById(recruit.getPkHr());
            resumeSubmitDTO.setHrvo(new HRVO(hr));

            Company company = companyInfoMapper.selectById(recruit.getPkCompany());
            resumeSubmitDTO.setCompanyVO(new CompanyVO(company));
            resumeSubmitDTO.setFilterStatus("0");
            return Result.ok(resumeSubmitDTO);
        }
        return Result.error();
    }

    @Override
    public Result<Void> reply(ResumeSubmitVO resumeSubmitVO) {
        ResumeSubmit resumeSubmit = new ResumeSubmit();
        resumeSubmit.setId(Long.valueOf(resumeSubmitVO.getId()));
        resumeSubmit.setFilterStatus(Integer.valueOf(resumeSubmitVO.getFilterStatus()));
        resumeSubmit.setReply(resumeSubmitVO.getReply());
        if (updateById(resumeSubmit)) {
            return Result.ok();
        }
        return Result.error();
    }

    @Override
    public Result<SubmitInfoDTO> interest(SubmitInfoDTO submitInfoDTO) {
        ResumeSubmit resumeSubmit = new ResumeSubmit();
        resumeSubmit.setId(Long.valueOf(submitInfoDTO.getResumeSubmitVO().getId()));
        resumeSubmit.setFilterStatus(3);
        if (updateById(resumeSubmit)) {
            submitInfoDTO.getResumeSubmitVO().setFilterStatus("3");
            return Result.ok(submitInfoDTO);
        }
        return Result.error();
    }

    @Override
    public Result<SubmitInfoDTO> removeInterest(SubmitInfoDTO submitInfoDTO) {
        ResumeSubmit resumeSubmit = new ResumeSubmit();
        resumeSubmit.setId(Long.valueOf(submitInfoDTO.getResumeSubmitVO().getId()));
        resumeSubmit.setFilterStatus(0);
        if (updateById(resumeSubmit)) {
            submitInfoDTO.getResumeSubmitVO().setFilterStatus("0");
            return Result.ok(submitInfoDTO);
        }
        return Result.error();
    }
}
