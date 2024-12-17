package com.xiaopinyun.bean.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 简历
 */
@Data
@TableName("resume")
public class Resume {
    // id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 求职者id
    private Integer aid;
    // 专业技能
    private String majorSkill;
    // 教育背景
    private Integer[] eids;
    // 项目经历
    private Integer[] pids;
    // 实习经历
    private Integer[] jids;
    // 获奖荣誉
    private String honor;
    // 自我评价
    private String selfEvaluation;
    // 附件简历（链接地址）
    @TableField("resume_pdf")
    private String resumePDF;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中
    private Integer isCheck;
    // 是否已删除 0正常、1已删除
    @TableLogic
    private Integer isDelete;
}
