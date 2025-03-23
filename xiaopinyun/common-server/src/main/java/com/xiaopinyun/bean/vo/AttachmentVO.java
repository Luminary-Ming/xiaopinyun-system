package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.Attachment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 附件（展示层对象）
 */
@Data
@NoArgsConstructor
public class AttachmentVO {
    // 主键
    private String id;
    // 学生信息主键
    private String pkApplicant;
    // 附件简历（仅支持PDF）
    private String resumePDF;
    // 时间戳
    private LocalDateTime ts;

    // 构造方法，用于从 Attachment 对象转换
    private AttachmentVO(Attachment attachment) {
        this.id = attachment.getId().toString();
        this.resumePDF = attachment.getResumePDF();
        this.pkApplicant = attachment.getPkApplicant().toString();
        this.ts = attachment.getTs();
    }
}
