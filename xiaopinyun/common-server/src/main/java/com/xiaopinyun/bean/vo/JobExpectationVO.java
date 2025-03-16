package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.JobExpectation;
import lombok.Data;

/**
 * 求职期望（展示层对象）
 */
@Data
public class JobExpectationVO {
    // 主键
    private Long id;
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

    // 构造方法，用于从 JobExpectation 对象转换
    public JobExpectationVO(JobExpectation jobExpectation){
        this.id = jobExpectation.getId();
        this.jobType = jobExpectation.getJobType();
        this.expectedIndustry = jobExpectation.getExpectedIndustry();
        this.expectedJob = jobExpectation.getExpectedJob();
        this.salary = jobExpectation.getSalary();
    }
}