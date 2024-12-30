package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.po.JobExpectation;
import com.xiaopinyun.bean.vo.JobExpectationVO;
import com.xiaopinyun.bean.vo.Result;

import java.util.List;

public interface JobExpectationService extends IService<JobExpectation> {
    /**
     * 根据学生（aid）查询求职期望
     */
    Result<List<JobExpectationVO>> queryVOByAid(Integer aid);

    /**
     * 添加求职期望
     */
    Result<JobExpectationVO> saveVO(JobExpectation jobExpectation);

    /**
     * 修改求职期望
     */
    Result<JobExpectationVO> updateVO(JobExpectation jobExpectation);

    /**
     * 根据 id 删除求职期望
     */
    Result<Void> deleteVOById(Integer id);
}
