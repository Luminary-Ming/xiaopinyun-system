package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.vo.ApplicantVO;
import com.xiaopinyun.mapper.ApplicantInformationMapper;
import com.xiaopinyun.bean.po.Applicant;
import com.xiaopinyun.bean.vo.PageResult;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.service.ApplicantInformationService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ApplicantInformationServiceImpl extends ServiceImpl<ApplicantInformationMapper, Applicant> implements ApplicantInformationService {

    @Autowired
    private ApplicantInformationMapper applicantInformationMapper;

    /**
     * 根据 id 查询学生信息
     */
    @Override
    public Result<ApplicantVO> selectApplicantById(Integer id) {
        if (id == null || id < 1) {
            return Result.fail(BizCode.FAIL);
        }
        Applicant applicant = applicantInformationMapper.selectById(id);
        if (applicant != null) {
            ApplicantVO applicantVO = new ApplicantVO(applicant);
            return Result.success(BizCode.SELECT_SUCCESS, applicantVO);
        }
        return Result.fail(BizCode.NO_DATA);
    }

    /**
     * 条件查询、分页查询学生信息
     */
    @Override
    public Result<PageResult<List<ApplicantVO>>> selectApplicant(Integer currentPage, Integer pageSize, String name, Integer sex, Integer status) {
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
            return Result.success(BizCode.SELECT_SUCCESS, pageResult);
        }
        return Result.fail(BizCode.NO_DATA);
    }

    /**
     * 添加学生信息
     */
    @Override
    public Result<ApplicantVO> addApplicant(Applicant applicant) {
        if (applicant == null) {
            return Result.fail(BizCode.ADD_NULL);
        }
        // 校验性别
        if (applicant.getSex() != 0 && applicant.getSex() != 1) {
            return Result.fail(BizCode.SEX_FAIL);
        }
        // 校验手机号
        Pattern compile = Pattern.compile("^1[3-9]\\d{9}$");
        Matcher matcher = compile.matcher(applicant.getTelephone());
        boolean matches = matcher.matches();
        if (matches) {
            return Result.fail(BizCode.TELEPHONE_FAIL);
        }
        // 校验电子邮箱

        // 添加需要审核
        applicant.setIsCheck(2);
        // 默认未就业
        applicant.setIsEmploy(0);
        // 默认未删除
        applicant.setIsDelete(0);
        if (save(applicant)) {
            // 添加之后的数据
            Applicant applicantData = applicantInformationMapper.selectById(applicant.getId());
            ApplicantVO applicantVO = new ApplicantVO(applicantData);
            return Result.success(BizCode.ADD_SUCCESS, applicantVO);
        }
        return Result.fail(BizCode.ADD_FAIL);
    }

    /**
     * 修改学生信息
     */
    @Override
    public Result<ApplicantVO> updateApplicant(Applicant applicant) {
        // 如果没有要更新的数据直接返回更新成功
        if (applicant == null) {
            return Result.success(BizCode.UPDATE_SUCCESS);
        }
        // 更新需要审核
        applicant.setIsCheck(2);
        if (updateById(applicant)) {
            // 修改之后的数据
            Applicant applicantData = applicantInformationMapper.selectById(applicant.getId());
            ApplicantVO applicantVO = new ApplicantVO(applicantData);
            return Result.success(BizCode.UPDATE_SUCCESS, applicantVO);
        }
        return Result.fail(BizCode.UPDATE_FAIL);
    }

    /**
     * 根据 id 删除学生信息
     */
    @Override
    public Result<Void> deleteApplicantById(Integer id) {
        if (id == null || id < 1) {
            return Result.fail(BizCode.FAIL);
        }
        if (removeById(id)) {
            return Result.success(BizCode.DELETE_SUCCESS);
        }
        return Result.fail(BizCode.DELETE_FAIL);
    }
}
