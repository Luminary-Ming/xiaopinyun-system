package com.xiaopinyun.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

/**
 * 附件
 */
@Data
public class Attachment {
    // 主键
    private Integer id;
    // 附件简历（仅支持PDF）
    @TableField("resume_pdf")
    private String resumePDF;
    // 是否已删除 0正常、1已删除
    @TableLogic
    private Integer dr;
    // 时间戳
    private String ts;
}
