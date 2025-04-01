package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.dto.ResumeSubmitDTO;
import com.xiaopinyun.bean.po.ResumeSubmit;
import com.xiaopinyun.bean.vo.ResumeSubmitVO;

import java.util.List;

public interface ResumeSubmitService extends IService<ResumeSubmit> {
    Result<List<ResumeSubmitDTO>> queryByPkApplicant(String pkApplicant);
    Result<List<ResumeSubmitDTO>> queryByPkRecruit(String pkRecruit);
    Result<List<ResumeSubmitDTO>> queryReply(String pkApplicant);
    Result<ResumeSubmitDTO> submit(ResumeSubmitVO resumeSubmitVO);
}
