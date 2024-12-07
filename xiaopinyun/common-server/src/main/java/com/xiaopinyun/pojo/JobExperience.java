package com.xiaopinyun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 实习经历
 */
@Data
@TableName("jobExperience")
public class JobExperience {
    // id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 简历id
    private Integer rid;
    // 公司名
    private String companyName;
    // 所属行业
    private String industryType;
    // 所属部门
    private String department;
    // 职位名称
    private String jobName;
    // 入职时间  例2024年11月
    private String startTime;
    // 离职时间  例2025年5月
    private String endTime;
    // 工作内容
    private String jobContent;
    // 是否已删除 0正常 1已删除
    @TableLogic
    private Integer isDelete;
}
