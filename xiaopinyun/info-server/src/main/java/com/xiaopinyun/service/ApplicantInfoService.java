package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.po.Applicant;
import com.xiaopinyun.bean.vo.ApplicantVO;
import com.xiaopinyun.bean.dto.PageResult;
import com.xiaopinyun.bean.dto.Result;

import java.util.List;

public interface ApplicantInfoService extends IService<Applicant> {
    /**
     * 根据 id 查询学生信息
     */
    Result<ApplicantVO> queryVOById(Integer id);

    /**
     * 条件查询、分页查询学生信息
     */
    Result<PageResult<List<ApplicantVO>>> queryVO(Integer currentPage, Integer pageSize, String name, Integer sex, Integer status);

    /**
     * 添加学生信息
     */
    Result<ApplicantVO> saveVO(Applicant applicant);

    /**
     * 修改学生信息
     */
    Result<ApplicantVO> updateVO(Applicant applicant);

    /**
     * 根据 id 删除学生信息
     */
    Result<Void> deleteVOById(Integer id);
}
