package com.xiaopinyun.bean.dto;

import com.xiaopinyun.bean.vo.AdvantageVO;
import com.xiaopinyun.bean.vo.ApplicantVO;
import com.xiaopinyun.bean.vo.EducationalVO;
import com.xiaopinyun.bean.vo.RecruitVO;
import com.xiaopinyun.bean.vo.ResumeSubmitVO;
import lombok.Data;

@Data
public class SubmitInfoDTO {
    private ResumeSubmitVO resumeSubmitVO;
    private RecruitVO recruitVO;
    private ApplicantVO applicantVO;
    private EducationalVO educationalVO;
    private AdvantageVO advantageVO;
}
