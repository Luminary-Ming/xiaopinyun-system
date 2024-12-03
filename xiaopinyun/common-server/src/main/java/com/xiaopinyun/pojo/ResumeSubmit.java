package com.xiaopinyun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 简历投递
 */
@Data
@TableName("resumeSubmit")
public class ResumeSubmit {
    // 自增id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 招聘职位id
    private Integer recid;
    // 简历id
    private Integer resid;
    // 是否通过筛选（HR功能） 0退回、1通过
    private Integer isPass;
    // 简历退回回复（HR功能）
    private String reply;
    // 是否已取消投递（学生功能） 0正常、1已删除
    @TableLogic
    private Integer isDelete;
}
