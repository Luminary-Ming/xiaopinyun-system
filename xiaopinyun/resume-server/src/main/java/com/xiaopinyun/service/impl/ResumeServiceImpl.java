//package com.xiaopinyun.service.impl;
//
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import com.xiaopinyun.bean.po.Advantage;
//import com.xiaopinyun.bean.po.EducationalBackground;
//import com.xiaopinyun.bean.vo.Result;
//import com.xiaopinyun.bean.vo.ResumeVO;
//import com.xiaopinyun.mapper.ApplicantInfoMapper;
//import com.xiaopinyun.mapper.EducationalMapper;
//import com.xiaopinyun.mapper.JobExpectationMapper;
//import com.xiaopinyun.mapper.ProjectExperienceMapper;
//import com.xiaopinyun.mapper.ResumeMapper;
//import com.xiaopinyun.mapper.WorkExperienceMapper;
//import com.xiaopinyun.service.ResumeService;
//import com.xiaopinyun.util.BizCode;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Advantage> implements ResumeService {
//    @Autowired
//    private ResumeMapper resumeMapper;
//    @Autowired
//    private ApplicantInfoMapper applicantMapper;
//    @Autowired
//    private JobExpectationMapper jobExpectationMapper;
//    @Autowired
//    private EducationalMapper backgroundMapper;
//    @Autowired
//    private ProjectExperienceMapper projectExperienceMapper;
//    @Autowired
//    private WorkExperienceMapper workExperienceMapper;
//
//
//    /**
//     * 根据 id 查询简历信息
//     */
//    @Override
//    public Result<ResumeVO> queryVOById(Integer id) {
//        Advantage advantage = resumeMapper.selectById(id);
//        if (advantage == null) {
//            return Result.ok(BizCode.NO_DATA);
//        }
//        ResumeVO resumeVO = new ResumeVO(advantage);
//
////        Applicant applicant = applicantMapper.selectById(advantage.getAid());
////        ApplicantVO applicantVO = new ApplicantVO(applicant);
////        resumeVO.setApplicantVO(applicantVO);  // 学生信息
////
////        String jids = advantage.getJids();
////        String[] jidsArr = jids.split(",");
////        List<String> jidList = Arrays.asList(jidsArr);
////        List<JobExpectation> jobExpectationList = jobExpectationMapper.selectByIds(jidList);
////        List<JobExpectationVO> jobExpectationVOList = new ArrayList<>();
////        for (JobExpectation jobExpectation : jobExpectationList) {
////            JobExpectationVO jobExpectationVO = new JobExpectationVO(jobExpectation);
////            jobExpectationVOList.add(jobExpectationVO);
////        }
////        resumeVO.setJobExpectationVO(jobExpectationVOList);  // 求职期望
////
////        String eids = advantage.getEids();
////        String[] eidsArr = eids.split(",");
////        List<String> eidsList = Arrays.asList(eidsArr);
////        List<EducationalBackground> educationalBackgroundList = backgroundMapper.selectByIds(eidsList);
////        List<EducationalBackgroundVO> educationalBackgroundVOList = new ArrayList<>();
////        for (EducationalBackground educationalBackground : educationalBackgroundList) {
////            EducationalBackgroundVO educationalBackgroundVO = new EducationalBackgroundVO(educationalBackground);
////            educationalBackgroundVOList.add(educationalBackgroundVO);
////        }
////        resumeVO.setEducationalBackgroundVO(educationalBackgroundVOList);  // 教育背景
////
////        String pids = advantage.getPids();
////        String[] pidsArr = pids.split(",");
////        List<String> pidsList = Arrays.asList(pidsArr);
////        List<ProjectExperience> projectExperienceList = projectExperienceMapper.selectByIds(pidsList);
////        List<ProjectExperienceVO> projectExperienceVOList = new ArrayList<>();
////        for (ProjectExperience projectExperience : projectExperienceList) {
////            ProjectExperienceVO projectExperienceVO = new ProjectExperienceVO(projectExperience);
////            projectExperienceVOList.add(projectExperienceVO);
////        }
////        resumeVO.setProjectExperienceVO(projectExperienceVOList);  // 项目经历
////
////        String wids = advantage.getWids();
////        String[] widsArr = wids.split(",");
////        List<String> widsList = Arrays.asList(widsArr);
////        List<WorkExperience> workExperienceList = workExperienceMapper.selectByIds(widsList);
////        List<WorkExperienceVO> workExperienceVOList = new ArrayList<>();
////        for (WorkExperience workExperience : workExperienceList) {
////            WorkExperienceVO workExperienceVO = new WorkExperienceVO(workExperience);
////            workExperienceVOList.add(workExperienceVO);
////        }
////        resumeVO.setWorkExperienceVO(workExperienceVOList);  // 工作经验
//        return Result.ok(resumeVO);
//    }
//
//    /**
//     * 添加简历信息
//     */
//    @Override
//    public Result<ResumeVO> saveVO(Advantage advantage) {
//        if (advantage == null) {
//            return Result.paramError(BizCode.PLEASE_WRITE);
//        }
//        if (!save(advantage)) {
//            return Result.error();
//        }
//        Advantage advantageData = resumeMapper.selectById(advantage.getId());
//        ResumeVO resumeVO = new ResumeVO(advantageData);
//        return Result.ok(resumeVO);
//    }
//
//    /**
//     * 更新简历信息
//     */
//    @Override
//    public Result<ResumeVO> updateVO(Advantage advantage) {
//        if (advantage == null) {
//            return Result.ok();
//        }
//        if (!updateById(advantage)) {
//            return Result.error();
//        }
//        Advantage advantageData = resumeMapper.selectById(advantage.getId());
//        ResumeVO resumeVO = new ResumeVO(advantageData);
//        return Result.ok(resumeVO);
//    }
//
//    /**
//     * 根据 id 删除简历信息
//     */
//    @Override
//    public Result<Void> deleteVOById(Integer id) {
//        if(!removeById(id)){
//            return Result.error();
//        }
//        return Result.ok();
//    }
//}
