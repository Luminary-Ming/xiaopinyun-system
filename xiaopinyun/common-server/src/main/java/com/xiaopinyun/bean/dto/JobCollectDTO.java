package com.xiaopinyun.bean.dto;

import com.xiaopinyun.bean.vo.ApplicantVO;
import com.xiaopinyun.bean.vo.CompanyVO;
import com.xiaopinyun.bean.vo.HRVO;
import com.xiaopinyun.bean.vo.RecruitVO;
import lombok.Data;

@Data
public class JobCollectDTO {
    private String id;
    // 学生信息
    private ApplicantVO applicantVO;
    // 招聘信息
    private RecruitVO recruitVO;
    // HR
    private HRVO hrvo;
    // 公司
    private CompanyVO companyVO;
}
