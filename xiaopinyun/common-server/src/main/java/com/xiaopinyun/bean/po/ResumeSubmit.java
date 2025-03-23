package com.xiaopinyun.bean.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 简历投递
 */
@Data
@TableName("resume_submit")
public class ResumeSubmit {
    // 主键
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    // 招聘信息主键
    private Long pkRecruit;
    // 学生信息主键
    private Long pkApplicant;
    // 投递附件简历
    private Long pkAttachment;
    // 筛选状态（HR功能） 0、默认、1退回、2通过、3感兴趣
    private Integer filterStatus;
    // 简历退回回复（HR功能）
    private String reply;
    // 时间戳
    @TableField(fill = FieldFill.INSERT_UPDATE)  // 新增更新自动填充
    private LocalDateTime ts;
}
