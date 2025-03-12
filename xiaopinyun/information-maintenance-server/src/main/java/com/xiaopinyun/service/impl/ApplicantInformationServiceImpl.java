package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.po.Applicant;
import com.xiaopinyun.bean.vo.ApplicantVO;
import com.xiaopinyun.bean.vo.PageResult;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.mapper.ApplicantInformationMapper;
import com.xiaopinyun.service.ApplicantInformationService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ApplicantInformationServiceImpl extends ServiceImpl<ApplicantInformationMapper, Applicant> implements ApplicantInformationService {

    @Autowired
    private ApplicantInformationMapper applicantInformationMapper;

    /**
     * 根据 id 查询学生信息
     */
    @Override
    public Result<ApplicantVO> queryVOById(Integer id) {
        Applicant applicant = applicantInformationMapper.selectById(id);
        if (applicant != null) {
            ApplicantVO applicantVO = new ApplicantVO(applicant);
            return Result.ok(applicantVO);
        }
        return Result.ok(BizCode.NO_DATA);
    }

    /**
     * 条件查询、分页查询学生信息
     */
    @Override
    public Result<PageResult<List<ApplicantVO>>> queryVO(Integer currentPage, Integer pageSize, String name, Integer sex, Integer status) {
        Page<Applicant> page = new Page<>();
        QueryWrapper<Applicant> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name)
                .like(sex == 0 || sex == 1, "sex", sex)
                .like(status >= 0 && status <= 3, "status", status);
        Page<Applicant> applicantPage = applicantInformationMapper.selectPage(page, wrapper);
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
            return Result.ok(pageResult);
        }
        return Result.ok(BizCode.NO_DATA);
    }

    /**
     * 添加学生信息
     */
    @Override
    public Result<ApplicantVO> saveVO(Applicant applicant) {
        if (applicant == null) {
            return Result.paramError(BizCode.PLEASE_WRITE);
        }
        // 校验对象的字段
        if (checkApplicant(applicant).isSuccess()) {
            return checkApplicant(applicant);
        }
        // 添加需要审核
        applicant.setCheckStatus(2);
        // 默认未就业
        applicant.setEmployStatus(0);
        // 默认未删除
        applicant.setDr(0);
        if (save(applicant)) {
            // 添加之后的数据
            Applicant applicantData = applicantInformationMapper.selectById(applicant.getId());
            ApplicantVO applicantVO = new ApplicantVO(applicantData);
            return Result.ok(applicantVO);
        }
        return Result.error();
    }

    /**
     * 修改学生信息
     */
    @Override
    public Result<ApplicantVO> updateVO(Applicant applicant) {
        // 如果没有要更新的数据直接返回更新成功
        if (applicant == null) {
            return Result.ok();
        }
        // 校验对象的字段
        if (checkApplicant(applicant).isSuccess()) {
            return checkApplicant(applicant);
        }
        // 更新需要审核
        applicant.setCheckStatus(2);
        if (updateById(applicant)) {
            // 修改之后的数据
            Applicant applicantData = applicantInformationMapper.selectById(applicant.getId());
            ApplicantVO applicantVO = new ApplicantVO(applicantData);
            return Result.ok(applicantVO);
        }
        return Result.error();
    }

    /**
     * 根据 id 删除学生信息
     */
    @Override
    public Result<Void> deleteVOById(Integer id) {
        if (removeById(id)) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 校验所传入的字段
     */
    private Result<ApplicantVO> checkApplicant(Applicant applicant) {
        // 校验性别
        if (applicant.getSex() != 0 && applicant.getSex() != 1) {
            return Result.paramError("性别参数错误");
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
