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
@TableName("projectExperience")
public class ProjectExperience {
    // id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 简历id
    private Integer rid;
    // 项目名称
    private String projectName;
    // 项目角色
    private String projectRole;
    // 项目开始时间
    private String startTime;
    // 项目结束时间
    private String endTime;
    // 项目描述
    private String describe;
    // 是否已删除 0正常、1已删除
    @TableLogic
    private Integer isDelete;
}
