package com.xiaopinyun.bean.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 项目经历
 */
@Data
@TableName("project_experience")
public class ProjectExperience {
    // 主键
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    // 学生信息主键
    private Long pkApplicant;
    // 项目名称
    private String projectName;
    // 项目角色
    private String projectRole;
    // 项目开始时间 例2024.03 2024.03-2024.07
    private String startTime;
    // 项目结束时间 例2024.07
    private String endTime;
    // 项目描述（草了，describe还是个mysql保留字）
    private String description;
    // 是否已删除 0正常、1已删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer dr;
    // 时间戳
    @TableField(fill = FieldFill.INSERT_UPDATE)  // 新增更新自动填充
    private LocalDateTime ts;
}
