package com.xiaopinyun.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 简历投递
 */
@Data
@TableName("resume_submit")
public class ResumeSubmit {
    // 主键
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    // 招聘信息主键
    private Long pk_recruit;
    // 简历主键
    private Long pk_resume;
    // 投递附件简历
    private Long aid;
    // 筛选状态（HR功能） 0、默认、1退回、2通过、3感兴趣
    private Integer filterStatus;
    // 简历退回回复（HR功能）
    private String reply;
    // 时间戳
    private String ts;
}
