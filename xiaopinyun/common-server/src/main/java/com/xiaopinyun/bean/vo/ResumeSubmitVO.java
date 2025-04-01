package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.ResumeSubmit;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 简历投递
 */
@Data
@NoArgsConstructor
public class ResumeSubmitVO {
    // 主键
    private String id;
    // 招聘信息主键
    private String pkRecruit;
    // 学生信息主键
    private String pkApplicant;
    // 投递附件简历
    private String pkAttachment;
    // 筛选状态（HR功能） 0、默认、1退回、2通过、3感兴趣
    private String filterStatus;
    // 简历退回回复（HR功能）
    private String reply;

    public ResumeSubmitVO(ResumeSubmit resumeSubmit){
        this.id = resumeSubmit.getId().toString();
        this.pkRecruit = resumeSubmit.getPkRecruit().toString();
        this.pkApplicant = resumeSubmit.getPkApplicant().toString();
        this.pkAttachment = resumeSubmit.getPkAttachment().toString();
        this.filterStatus = resumeSubmit.getFilterStatus().toString();
        this.reply = resumeSubmit.getReply();
    }
}