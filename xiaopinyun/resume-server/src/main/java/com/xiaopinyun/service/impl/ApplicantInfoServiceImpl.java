package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.dto.ApplicantDTO;
import com.xiaopinyun.bean.dto.ApplicantManageDTO;
import com.xiaopinyun.bean.dto.PageResult;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.dto.ResumeDTO;
import com.xiaopinyun.bean.po.Advantage;
import com.xiaopinyun.bean.po.Applicant;
import com.xiaopinyun.bean.po.Educational;
import com.xiaopinyun.bean.vo.ApplicantVO;
import com.xiaopinyun.bean.vo.EducationalVO;
import com.xiaopinyun.mapper.AdvantageMapper;
import com.xiaopinyun.mapper.ApplicantInfoMapper;
import com.xiaopinyun.mapper.EducationalMapper;
import com.xiaopinyun.service.ApplicantInfoService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ApplicantInfoServiceImpl extends ServiceImpl<ApplicantInfoMapper, Applicant> implements ApplicantInfoService {

    @Autowired
    private ApplicantInfoMapper applicantInfoMapper;
    @Autowired
    private EducationalMapper educationalMapper;
    @Autowired
    private AdvantageMapper advantageMapper;

    /**
     * 根据 id 查询学生信息
     */
    @Override
    public Result<ApplicantDTO> queryById(String id) {
        Applicant applicant = applicantInfoMapper.selectById(id);
        if (applicant != null) {
            ApplicantVO applicantVO = new ApplicantVO(applicant);

            ApplicantDTO applicantDTO = new ApplicantDTO();
            applicantDTO.setApplicantVO(applicantVO);

            QueryWrapper<Educational> wrapper = new QueryWrapper<>();
            wrapper.eq("pk_applicant", id);
            List<Educational> educationalList = educationalMapper.selectList(wrapper);
            for (Educational educational : educationalList) {
                // 如果没有填写教育背景，默认本科应届生
                if (educational.getName() == null) {
                    applicantDTO.setEducation("本科");  // 学历
                    applicantDTO.setIdentity("应届生");  // 身份
                    continue;
                }
                EducationalVO educationalVO = new EducationalVO(educational);
                applicantDTO.setEducation(educationalVO.getQualification());  // 学历
                String endTime = educationalVO.getEndTime();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
                String dateTime = LocalDateTime.now().format(dateTimeFormatter);
                if (Integer.parseInt(endTime) <= Integer.parseInt(dateTime)) {
                    applicantDTO.setIdentity(endTime + "应届生");  // 身份
                } else {
                    applicantDTO.setIdentity(endTime + "毕业生");
                }
            }
            return Result.ok(applicantDTO);
        }
        return Result.ok(BizCode.NO_DATA);
    }

    /**
     * 条件查询、分页查询学生信息
     */
    @Override
    public Result<PageResult<List<ApplicantDTO>>> query(Integer currentPage, Integer pageSize, String name, Integer sex, Integer status) {
        Page<Applicant> page = new Page<>();
        QueryWrapper<Applicant> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name)
                .like(sex == 0 || sex == 1, "sex", sex)
                .like(status >= 0 && status <= 3, "status", status);
        Page<Applicant> applicantPage = applicantInfoMapper.selectPage(page, wrapper);
        if (applicantPage.getRecords() != null) {
            PageResult<List<ApplicantVO>> pageResult = new PageResult<>();
            pageResult.setTotal(applicantPage.getTotal());
            List<Applicant> applicantList = applicantPage.getRecords();
            List<ApplicantVO> applicantVOList = new ArrayList<>();
            for (Applicant applicant : applicantList) {
                ApplicantVO applicantVO = new ApplicantVO(applicant);
                applicantVOList.add(applicantVO);
            }
            pageResult.setData(applicantVOList);
            return null;
        }
        return Result.ok(BizCode.NO_DATA);
    }

    /**
     * 查询所有学生信息
     */
    @Override
    public Result<List<ApplicantManageDTO>> queryAll() {
        QueryWrapper<Applicant> wrapper = new QueryWrapper<>();
        wrapper.eq("dr", 0);
        List<Applicant> applicantList = applicantInfoMapper.selectList(wrapper);
        if (applicantList != null) {
            List<ApplicantManageDTO> applicantManageDTOList = new ArrayList<>();
            for (Applicant applicant : applicantList) {
                ApplicantManageDTO applicantManageDTO = new ApplicantManageDTO();
                ResumeDTO resumeDTO = new ResumeDTO();
                resumeDTO.setId(applicant.getId());
                resumeDTO.setApplicantVO(new ApplicantVO(applicant));

                QueryWrapper<Advantage> advantageQueryWrapper = new QueryWrapper<>();
                advantageQueryWrapper.eq("pk_applicant", applicant.getId());
                Advantage advantage = advantageMapper.selectOne(advantageQueryWrapper);
                resumeDTO.setMajorSkill(advantage.getMajorSkill());
                resumeDTO.setHonor(advantage.getHonor());
                resumeDTO.setSelfEvaluation(advantage.getSelfEvaluation());

                QueryWrapper<Educational> educationalQueryWrapper = new QueryWrapper<>();
                educationalQueryWrapper.eq("pk_applicant", applicant.getId());
                Educational educational = educationalMapper.selectOne(educationalQueryWrapper);
                List<EducationalVO> educationalList = new ArrayList<>();
                educationalList.add(new EducationalVO(educational));
                resumeDTO.setEducationalVO(educationalList);
                applicantManageDTO.setResumeDTO(resumeDTO);

                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                applicantManageDTO.setTs(applicant.getTs().format(dateTimeFormatter));
                applicantManageDTOList.add(applicantManageDTO);
            }
            return Result.ok(applicantManageDTOList);
        }
        return Result.ok(BizCode.NO_DATA);
    }

    /**
     * 新增学生信息
     */
    @Override
    public Result<ApplicantDTO> save(ApplicantVO applicantVO) {
        if (applicantVO == null) {
            return Result.paramError(BizCode.PLEASE_WRITE);
        }
        // 校验对象的字段
        if (!checkApplicant(applicantVO).isSuccess()) {
            return checkApplicant(applicantVO);
        }
        Applicant applicant = new Applicant();
        convertApplicantVO(applicantVO, applicant);
        if (save(applicant)) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 新增一条空记录，不对外提供请求，仅处理内部逻辑
     */
    @Override
    public Applicant save() {
        Applicant applicant = new Applicant();
        applicant.setDr(0);
        applicantInfoMapper.insert(applicant);
        return applicant;
    }

    /**
     * 修改学生信息
     */
    @Override
    public Result<ApplicantDTO> update(ApplicantVO applicantVO) {
        // 如果没有要更新的数据直接返回更新成功
        if (applicantVO == null) {
            return Result.ok();
        }
        // 校验对象的字段
        if (!checkApplicant(applicantVO).isSuccess()) {
            return checkApplicant(applicantVO);
        }
        Applicant applicant = new Applicant();
        applicant.setId(Long.valueOf(applicantVO.getId()));
        convertApplicantVO(applicantVO, applicant);
        if (updateById(applicant)) {
            // 修改之后的数据
            return queryById(applicant.getId().toString());
        }
        return Result.error();
    }

    private void convertApplicantVO(ApplicantVO applicantVO, Applicant applicant) {
        applicant.setProfileImg(applicantVO.getProfileImgUrl());
        applicant.setName(applicantVO.getName());
        applicant.setSex(Integer.valueOf(applicantVO.getSex()));
        applicant.setBirthday(applicantVO.getBirthday());
        applicant.setAddress(applicantVO.getAddress());
        applicant.setStatus(Integer.valueOf(applicantVO.getStatus()));
        applicant.setTelephone(applicantVO.getTelephone());
        applicant.setEmail(applicantVO.getEmail());
        applicant.setCheckStatus(Integer.valueOf(applicantVO.getCheckStatus()));
    }

    /**
     * 根据 id 删除学生信息
     */
    @Override
    public Result<Void> deleteById(String id) {
        if (removeById(id)) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 校验所传入的字段
     */
    private Result<ApplicantDTO> checkApplicant(ApplicantVO applicant) {
        // 校检性别
        if (!Pattern.matches("[0-1]", applicant.getSex().toString())) {
            return Result.paramError("性别错误");
        }
        // 校验手机号
        if (!Pattern.matches("^1[3-9]\\d{9}$", applicant.getTelephone())) {
            return Result.paramError("手机号格式错误");
        }
        // 校验电子邮箱
        if (!Pattern.matches("[a-zA-Z0-9]+@[A-Za-z0-9]+\\.[a-z0-9]{2,}", applicant.getEmail())) {
            return Result.paramError("邮箱格式错误");
        }
        // 校验出生年月
        if (!Pattern.matches("\\b\\d{4}-(0[1-9]|1[0-2])\\b", applicant.getBirthday())) {
            return Result.paramError("出生年月格式错误");
        }
        // 校检求职状态
        if (!Pattern.matches("[0-3]", applicant.getStatus().toString())) {
            return Result.paramError("求职状态错误");
        }
        return Result.ok();
    }
}
