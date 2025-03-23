package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.JobExpectation;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 求职期望（展示层对象）
 */
@Data
@NoArgsConstructor
public class JobExpectationVO {
    // 主键
    private String id;
    // 学生信息主键
    private String pkApplicant;
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

    public JobExpectationVO(JobExpectation jobExpectation){
        this.id = jobExpectation.getId().toString();
        this.pkApplicant = jobExpectation.getPkApplicant().toString();
        this.jobType = jobExpectation.getJobType();
        this.expectedIndustry = jobExpectation.getExpectedIndustry();
        this.district = jobExpectation.getDistrict();
        this.expectedJob = jobExpectation.getExpectedJob();
        this.salary = jobExpectation.getSalary();
    }
}