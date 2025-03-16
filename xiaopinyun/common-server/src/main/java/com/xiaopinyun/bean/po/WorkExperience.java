package com.xiaopinyun.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 工作 / 实习经历
 */
@Data
@TableName("work_experience")
public class WorkExperience {
    // id
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    // 学生id
    private Integer aid;
    // 公司名
    private String companyName;
    // 所属行业
    private String industryType;
    // 所属部门
    private String department;
    // 职位名称
    private String jobName;
    // 入职时间  例2024.11 2024.11-2025.05
    private String startTime;
    // 离职时间  例2025.05
    private String endTime;
    // 工作内容
    private String jobContent;
    // 是否已删除 0正常 1已删除
    @TableLogic
    private Integer dr;
    // 时间戳
    private String ts;
}
