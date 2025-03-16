package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.Attachment;
import lombok.Data;

/**
 * 附件（展示层对象）
 */
@Data
public class AttachmentVO {
    // 主键
    private Long id;
    // 附件简历（仅支持PDF）
    private String resumePDF;

    // 构造方法，用于从 Attachment 对象转换
    private AttachmentVO(Attachment attachment) {
        this.id = attachment.getId();
        this.resumePDF = attachment.getResumePDF();
    }
}
