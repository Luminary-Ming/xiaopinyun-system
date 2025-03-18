package com.xiaopinyun.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 项目经历
 */
@Data
@TableName("project_experience")
public class ProjectExperience {
    // 主键
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    // 简历主键
    private Long pk_resume;
    // 项目名称
    private String projectName;
    // 项目角色
    private String projectRole;
    // 项目开始时间 例2024.03 2024.03-2024.07
    private String startTime;
    // 项目结束时间 例2024.07
    private String endTime;
    // 项目描述
    private String describe;
    // 是否已删除 0正常、1已删除
    @TableLogic
    private Integer dr;
    // 时间戳
    private String ts;
}
