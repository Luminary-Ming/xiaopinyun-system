package com.xiaopinyun.bean.dto;

import com.xiaopinyun.bean.vo.ApplicantVO;
import lombok.Data;

/**
 * DTO：根据前端需求补充字段或定制字段（有时可聚合多个 VO 的数据）
 */
@Data
public class ApplicantDTO {
    private ApplicantVO applicantVO;
    // 身份  25年应届生
    private String identity;
    // 学历  本科
    private String education;
}
