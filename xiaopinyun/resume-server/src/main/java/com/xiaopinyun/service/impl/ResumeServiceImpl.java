package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.po.Applicant;
import com.xiaopinyun.bean.po.EducationalBackground;
import com.xiaopinyun.bean.po.JobExpectation;
import com.xiaopinyun.bean.po.ProjectExperience;
import com.xiaopinyun.bean.po.Resume;
import com.xiaopinyun.bean.po.WorkExperience;
import com.xiaopinyun.bean.vo.ApplicantVO;
import com.xiaopinyun.bean.vo.EducationalBackgroundVO;
import com.xiaopinyun.bean.vo.JobExpectationVO;
import com.xiaopinyun.bean.vo.ProjectExperienceVO;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.bean.vo.ResumeVO;
import com.xiaopinyun.bean.vo.WorkExperienceVO;
import com.xiaopinyun.mapper.ApplicantInformationMapper;
import com.xiaopinyun.mapper.EducationalBackgroundMapper;
import com.xiaopinyun.mapper.JobExpectationMapper;
import com.xiaopinyun.mapper.ProjectExperienceMapper;
import com.xiaopinyun.mapper.ResumeMapper;
import com.xiaopinyun.mapper.WorkExperienceMapper;
import com.xiaopinyun.service.ResumeService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements ResumeService {
    @Autowired
    private ResumeMapper resumeMapper;
    @Autowired
    private ApplicantInformationMapper applicantMapper;
    @Autowired
    private JobExpectationMapper jobExpectationMapper;
    @Autowired
    private EducationalBackgroundMapper backgroundMapper;
    @Autowired
    private ProjectExperienceMapper projectExperienceMapper;
    @Autowired
    private WorkExperienceMapper workExperienceMapper;


    /**
     * 根据 id 查询简历信息
     */
    @Override
    public Result<ResumeVO> queryVOById(Integer id) {
        Resume resume = resumeMapper.selectById(id);
        if (resume == null) {
            return Result.ok(BizCode.NO_DATA);
        }
        ResumeVO resumeVO = new ResumeVO(resume);

        Applicant applicant = applicantMapper.selectById(resume.getAid());
        ApplicantVO applicantVO = new ApplicantVO(applicant);
        resumeVO.setApplicantVO(applicantVO);  // 学生信息

        String jids = resume.getJids();
        String[] jidsArr = jids.split(",");
        List<String> jidList = Arrays.asList(jidsArr);
        List<JobExpectation> jobExpectationList = jobExpectationMapper.selectByIds(jidList);
        List<JobExpectationVO> jobExpectationVOList = new ArrayList<>();
        for (JobExpectation jobExpectation : jobExpectationList) {
            JobExpectationVO jobExpectationVO = new JobExpectationVO(jobExpectation);
            jobExpectationVOList.add(jobExpectationVO);
        }
        resumeVO.setJobExpectationVO(jobExpectationVOList);  // 求职期望

        String eids = resume.getEids();
        String[] eidsArr = eids.split(",");
        List<String> eidsList = Arrays.asList(eidsArr);
        List<EducationalBackground> educationalBackgroundList = backgroundMapper.selectByIds(eidsList);
        List<EducationalBackgroundVO> educationalBackgroundVOList = new ArrayList<>();
        for (EducationalBackground educationalBackground : educationalBackgroundList) {
            EducationalBackgroundVO educationalBackgroundVO = new EducationalBackgroundVO(educationalBackground);
            educationalBackgroundVOList.add(educationalBackgroundVO);
        }
        resumeVO.setEducationalBackgroundVO(educationalBackgroundVOList);  // 教育背景

        String pids = resume.getPids();
        String[] pidsArr = pids.split(",");
        List<String> pidsList = Arrays.asList(pidsArr);
        List<ProjectExperience> projectExperienceList = projectExperienceMapper.selectByIds(pidsList);
        List<ProjectExperienceVO> projectExperienceVOList = new ArrayList<>();
        for (ProjectExperience projectExperience : projectExperienceList) {
            ProjectExperienceVO projectExperienceVO = new ProjectExperienceVO(projectExperience);
            projectExperienceVOList.add(projectExperienceVO);
        }
        resumeVO.setProjectExperienceVO(projectExperienceVOList);  // 项目经历

        String wids = resume.getWids();
        String[] widsArr = wids.split(",");
        List<String> widsList = Arrays.asList(widsArr);
        List<WorkExperience> workExperienceList = workExperienceMapper.selectByIds(widsList);
        List<WorkExperienceVO> workExperienceVOList = new ArrayList<>();
        for (WorkExperience workExperience : workExperienceList) {
            WorkExperienceVO workExperienceVO = new WorkExperienceVO(workExperience);
            workExperienceVOList.add(workExperienceVO);
        }
        resumeVO.setWorkExperienceVO(workExperienceVOList);  // 工作经验
        return Result.ok(resumeVO);
    }

    /**
     * 添加简历信息
     */
    @Override
    public Result<ResumeVO> saveVO(Resume resume) {
        if (resume == null) {
            return Result.paramError(BizCode.PLEASE_WRITE);
        }
        if (!save(resume)) {
            return Result.error();
        }
        Resume resumeData = resumeMapper.selectById(resume.getId());
        ResumeVO resumeVO = new ResumeVO(resumeData);
        return Result.ok(resumeVO);
    }

    /**
     * 更新简历信息
     */
    @Override
    public Result<ResumeVO> updateVO(Resume resume) {
        if (resume == null) {
            return Result.ok();
        }
        if (!updateById(resume)) {
            return Result.error();
        }
        Resume resumeData = resumeMapper.selectById(resume.getId());
        ResumeVO resumeVO = new ResumeVO(resumeData);
        return Result.ok(resumeVO);
    }

    /**
     * 根据 id 删除简历信息
     */
    @Override
    public Result<Void> deleteVOById(Integer id) {
        if(!removeById(id)){
            return Result.error();
        }
        return Result.ok();
    }
}
