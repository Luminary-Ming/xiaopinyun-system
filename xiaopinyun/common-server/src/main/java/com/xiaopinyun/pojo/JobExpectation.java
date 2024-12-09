package com.xiaopinyun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 求职期望
 */
@Data
@TableName("jobExpectation")
public class JobExpectation {
    // id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 简历id
    private Integer rid;
    // 求职类型
    private String jobType;
    // 期望行业
    private String expectedIndustry;
    // 期望城市 最多三个
    private String[] district;
    // 期望职位
    private String expectedJob;
    // 薪资要求
    private Double Salary;
    // 是否已删除 0正常、1已删除
    @TableLogic
    private Integer isDelete;
}
