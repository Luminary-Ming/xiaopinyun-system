package com.xiaopinyun.bean.dto;

import com.xiaopinyun.bean.vo.CompanyVO;
import com.xiaopinyun.bean.vo.HRVO;
import com.xiaopinyun.bean.vo.RecruitVO;
import lombok.Data;

/**
 * 首页推荐职位
 */
@Data
public class RecommendJobDTO {
    private RecruitVO recruitVO;
    private HRVO hrvo;
    private CompanyVO companyVO;
}
