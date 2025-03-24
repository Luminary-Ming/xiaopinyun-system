package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.Attachment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    // 附件名称
    private String name;
    // 附件大小
    private String size;
    // 附件简历（仅支持PDF）
    private String resumePDF;
    // 时间戳
    private String ts;

    // 构造方法，用于从 Attachment 对象转换
    public AttachmentVO(Attachment attachment) {
        this.id = attachment.getId().toString();
        this.pkApplicant = attachment.getPkApplicant().toString();
        this.name = attachment.getName();
        this.size = attachment.getSize().toString();
        this.resumePDF = attachment.getResumePDF();
        LocalDateTime ts = attachment.getTs();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.ts =ts.format(formatter); ;
    }
}
