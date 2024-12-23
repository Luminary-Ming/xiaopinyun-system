package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.po.JobExpectation;
import com.xiaopinyun.bean.vo.JobExpectationVO;
import com.xiaopinyun.bean.vo.Result;

import java.util.List;

public interface JobExpectationService extends IService<JobExpectation> {
    /**
     * 查询求职期望
     */
    Result<List<JobExpectationVO>> queryVOById(Integer aid);

    /**
     * 添加求职期望
     */
    Result<JobExpectationVO> saveVO(JobExpectationVO jobExpectationVO);

    /**
     * 修改求职期望
     */

    /**
     * 删除求职期望
     */
}
