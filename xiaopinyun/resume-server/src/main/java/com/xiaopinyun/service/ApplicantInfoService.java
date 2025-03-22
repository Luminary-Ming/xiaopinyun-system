package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.dto.ApplicantDTO;
import com.xiaopinyun.bean.dto.PageResult;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.Applicant;
import com.xiaopinyun.bean.vo.ApplicantVO;

import java.util.List;

public interface ApplicantInfoService extends IService<Applicant> {
    /**
     * 查询学生信息
     */
    Result<ApplicantDTO> queryById(String id);

    /**
     * 条件查询、分页查询学生信息
     */
    Result<PageResult<List<ApplicantDTO>>> query(Integer currentPage, Integer pageSize, String name, Integer sex, Integer status);

    /**
     * 新增学生信息
     */
    Result<ApplicantDTO> save(ApplicantVO applicantVO);

    /**
     * 新增一条空记录，不对外提供请求，仅处理内部逻辑
     */
    Applicant save();

    /**
     * 更新学生信息
     */
    Result<ApplicantDTO> update(ApplicantVO applicantVO);

    /**
     * 删除学生信息
     */
    Result<Void> deleteById(String id);
}
