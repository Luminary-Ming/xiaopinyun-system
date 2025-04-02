package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.dto.ResumeSubmitDTO;
import com.xiaopinyun.bean.dto.SubmitInfoDTO;
import com.xiaopinyun.bean.po.ResumeSubmit;
import com.xiaopinyun.bean.vo.ResumeSubmitVO;

import java.util.List;

public interface ResumeSubmitService extends IService<ResumeSubmit> {
    Result<List<ResumeSubmitDTO>> queryByPkApplicant(String pkApplicant);
    Result<List<ResumeSubmitDTO>> queryReply(String pkApplicant);
    Result<List<SubmitInfoDTO>> querySubmitInfo(String pkHr);
    Result<List<SubmitInfoDTO>> queryInterest(String pkHr);
    Result<ResumeSubmitDTO> submit(ResumeSubmitVO resumeSubmitVO);
    Result<Void> reply(ResumeSubmitVO resumeSubmitVO);
    Result<SubmitInfoDTO> interest(SubmitInfoDTO submitInfoDTO);
    Result<SubmitInfoDTO> removeInterest(SubmitInfoDTO submitInfoDTO);
}
