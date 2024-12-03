package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.mapper.ApplicantInformationMaintenanceMapper;
import com.xiaopinyun.pojo.Applicant;
import com.xiaopinyun.pojo.vo.PageResult;
import com.xiaopinyun.pojo.vo.Result;
import com.xiaopinyun.service.ApplicantInformationMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ApplicantInformationMaintenanceServiceImpl extends ServiceImpl<ApplicantInformationMaintenanceMapper, Applicant> implements ApplicantInformationMaintenanceService {

    @Autowired
    private ApplicantInformationMaintenanceMapper applicantInformationMaintenanceMapper;

    /**
     * 根据 id 查询学生信息
     */
    @Override
    public Result<Applicant> selectApplicantById(Integer id) {

        return null;
    }

    /**
     * 条件查询、分页查询学生信息
     */
    @Override
    public Result<PageResult<List<Applicant>>> selectApplicant(Integer currentPage, Integer pageSize, String name, Integer sex, Integer status) {
        return null;
    }

    /**
     * 添加学生信息
     *
     * @param applicant
     */
    @Override
    public Result<Void> addApplicant(Applicant applicant) {
        return null;
    }

    /**
     * 修改学生信息
     */
    @Override
    public Result<Void> updateApplicant(Applicant applicant) {
        return null;
    }

    /**
     * 根据 id 删除学生信息
     */
    @Override
    public Result<Void> deleteApplicantById(Integer id) {
        return null;
    }
}
