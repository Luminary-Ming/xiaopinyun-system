package com.xiaopinyun.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
    private Integer id;
    // 投递的招聘职位
    private Integer recid;
    // 投递在线简历
    private Integer resid;
    // 投递附件简历
    private Integer aid;
    // 是否通过筛选（HR功能） 0退回、1通过
    private Integer isPass;
    // 简历退回回复（HR功能）
    private String reply;
    // 是否已取消投递（学生功能） 0正常、1已删除
    @TableLogic
    private Integer isDelete;
}
