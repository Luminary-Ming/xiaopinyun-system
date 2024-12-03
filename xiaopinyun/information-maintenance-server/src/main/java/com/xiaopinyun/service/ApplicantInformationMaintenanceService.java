package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.pojo.Applicant;
import com.xiaopinyun.pojo.vo.PageResult;
import com.xiaopinyun.pojo.vo.Result;

import java.util.List;

public interface ApplicantInformationMaintenanceService extends IService<Applicant> {
    /**
     * 根据 id 查询学生信息
     */
    Result<Applicant> selectApplicantById(Integer id);

    /**
     * 条件查询、分页查询学生信息
     */
    Result<PageResult<List<Applicant>>> selectApplicant(Integer currentPage, Integer pageSize, String name, Integer sex, Integer status);

    /**
     * 添加学生信息
     */
    Result<Void> addApplicant(Applicant applicant);

    /**
     * 修改学生信息
     */
    Result<Void> updateApplicant(Applicant applicant);

    /**
     * 根据 id 删除学生信息
     */
    Result<Void> deleteApplicantById(Integer id);
}
