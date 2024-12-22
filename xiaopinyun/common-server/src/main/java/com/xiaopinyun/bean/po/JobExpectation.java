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
    // id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 求职类型
    private String jobType;
    // 期望行业 最多三个 例计算机软件、互联网、企业服务
    private String expectedIndustry;
    // 工作城市 最多三个 例天津，北京，大连
    private String district;
    // 期望职位
    private String expectedJob;
    // 薪资要求 例9k-15k
    private String Salary;
    // 是否已删除 0正常、1已删除
    @TableLogic
    private Integer isDelete;
}
