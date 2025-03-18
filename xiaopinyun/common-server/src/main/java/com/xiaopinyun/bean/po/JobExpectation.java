package com.xiaopinyun.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 求职期望
 */
@Data
@TableName("job_expectation")
public class JobExpectation {
    // 主键
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    // 简历主键
    private Long pk_resume;
    // 求职类型 0全职、1兼职、2实习
    private String jobType;
    // 期望行业 最多三个 例计算机软件、互联网、企业服务
    private String expectedIndustry;
    // 期望城市 最多三个 例天津，北京，大连
    private String district;
    // 期望职位
    private String expectedJob;
    // 薪资要求 例9k-15k
    private String salary;
    // 是否已删除 0正常、1已删除
    @TableLogic
    private Integer dr;
    // 时间戳
    private String ts;
}
