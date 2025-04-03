package com.xiaopinyun.bean.dto;

import lombok.Data;

@Data
public class ApplicantManageDTO {
    private ResumeDTO resumeDTO;
    private String ts;  // 注册时间
}
