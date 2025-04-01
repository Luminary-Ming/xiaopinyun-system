package com.xiaopinyun.bean.dto;

import com.xiaopinyun.bean.vo.ApplicantVO;
import com.xiaopinyun.bean.vo.CompanyVO;
import com.xiaopinyun.bean.vo.HRVO;
import com.xiaopinyun.bean.vo.RecruitVO;
import lombok.Data;

@Data
public class ResumeSubmitDTO {
    // 主键
    private String id;
    // 招聘信息
    private RecruitVO recruitVO;
    // 学生信息
    private ApplicantVO applicantVO;
    // hr信息
    private HRVO hrvo;
    // 公司信息
    private CompanyVO companyVO;
    // 投递附件简历
    private String pkAttachment;
    // 筛选状态（HR功能） 0、默认、1退回、2通过、3感兴趣
    private String filterStatus;
    // 简历退回回复（HR功能）
    private String reply;
}
