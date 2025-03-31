package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.dto.JobCollectDTO;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.JobCollect;
import com.xiaopinyun.bean.vo.JobCollectVO;

import java.util.List;

public interface JobCollectService extends IService<JobCollect> {
    Result<List<JobCollectDTO>> query(String pkApplicant);
    Result<Void> insert(JobCollectVO jobCollectVO);
    Result<Void> delete(String id);
}
