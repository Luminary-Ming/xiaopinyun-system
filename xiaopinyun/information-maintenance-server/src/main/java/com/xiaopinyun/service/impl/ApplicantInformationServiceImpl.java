package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.mapper.ApplicantInformationMapper;
import com.xiaopinyun.pojo.Applicant;
import com.xiaopinyun.pojo.vo.PageResult;
import com.xiaopinyun.pojo.vo.Result;
import com.xiaopinyun.service.ApplicantInformationService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantInformationServiceImpl extends ServiceImpl<ApplicantInformationMapper, Applicant> implements ApplicantInformationService {

    @Autowired
    private ApplicantInformationMapper applicantInformationMapper;

    /**
     * 根据 id 查询学生信息
     */
    @Override
    public Result<Applicant> selectApplicantById(Integer id) {
        if (id == null || id < 1) {
            return Result.fail(BizCode.FAIL);
        }
        Applicant applicant = applicantInformationMapper.selectById(id);
        if (applicant != null) {
            return Result.success(BizCode.SELECT_SUCCESS, applicant);
        }
        return Result.fail(BizCode.NO_DATA);
    }

    /**
     * 条件查询、分页查询学生信息
     */
    @Override
    public Result<PageResult<List<Applicant>>> selectApplicant(Integer currentPage, Integer pageSize, String name, Integer sex, Integer status) {
        Page<Applicant> page = new Page<>();
        QueryWrapper<Applicant> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name)
                .like(sex == 0 || sex == 1, "sex", sex)
                .like(status >= 0 && status <= 3, "status", status);
        Page<Applicant> applicantPage = applicantInformationMapper.selectPage(page, wrapper);
        if (applicantPage.getRecords() != null) {
            PageResult<List<Applicant>> pageResult = new PageResult<>();
            pageResult.setTotal(applicantPage.getTotal());
            pageResult.setData(applicantPage.getRecords());
            return Result.success(BizCode.SELECT_SUCCESS, pageResult);
        }
        return Result.fail(BizCode.NO_DATA);
    }

    /**
     * 添加学生信息
     */
    @Override
    public Result<Void> addApplicant(Applicant applicant) {
        if (applicant == null) {
            return Result.fail(BizCode.ADD_NULL);
        }
        // 添加需要审核
        applicant.setIsCheck(2);
        // 默认未就业
        applicant.setIsEmploy(0);
        if (save(applicant)) {
            return Result.success(BizCode.ADD_SUCCESS);
        }
        return Result.fail(BizCode.ADD_FAIL);
    }

    /**
     * 修改学生信息
     */
    @Override
    public Result<Void> updateApplicant(Applicant applicant) {
        // 如果没有要更新的数据直接返回更新成功
        if (applicant == null) {
            return Result.success(BizCode.UPDATE_SUCCESS);
        }
        // 更新需要审核
        applicant.setIsCheck(2);
        if (updateById(applicant)) {
            return Result.success(BizCode.UPDATE_SUCCESS);
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
