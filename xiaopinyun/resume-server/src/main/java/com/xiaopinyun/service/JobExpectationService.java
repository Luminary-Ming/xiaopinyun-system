package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.JobExpectation;
import com.xiaopinyun.bean.vo.JobExpectationVO;

import java.util.List;

public interface JobExpectationService extends IService<JobExpectation> {
    /**
     * 查询求职期望
     */
    Result<List<JobExpectationVO>> query(String pkApplicant);

    /**
     * 添加求职期望
     */
    Result<JobExpectationVO> insert(JobExpectationVO jobExpectationVO);

    /**
     * 修改求职期望
     */
    Result<JobExpectationVO> update(JobExpectationVO jobExpectationVO);

    /**
     * 删除求职期望
     */
    Result<Void> delete(String id);
}
