package com.xiaopinyun.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 教育背景
 */
@Data
@TableName("educational_background")
public class EducationalBackground {
    // 主键
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    // 学生信息主键
    private Long pk_applicant;
    // 学校名称
    private String name;
    // 学历 0初中及以下 1中专 2高中 3大专 4本科 5硕士 6博士
    private Integer qualification;
    // 专业
    private String major;
    // 入学时间  例2021 2021-2025
    private String startTime;
    // 毕业时间  例2025
    private String endTime;
    // 主修课程
    private String majorCourse;
    // 是否已删除 0正常、1已删除
    @TableLogic
    private Integer dr;
    // 时间戳
    private String ts;
}
