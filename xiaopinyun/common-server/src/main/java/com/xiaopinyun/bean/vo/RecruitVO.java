package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.Recruit;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 招聘信息 / 职位信息（展示层对象）
 */
@Data
@NoArgsConstructor
public class RecruitVO {
    // 主键
    private String id;
    // 公司信息主键
    private String pkCompany;
    // HR信息主键（拿name、status）
    private String pkHr;
    // 招聘标题 例Java工程师-2025届(J14894)
    private String title;
    // 薪资 例9k-15k
    private String salary;
    // 员工福利 例五险一金、定期体检、年终奖、带薪年假、节日福利
    private String benefit;
    // 工作地点
    private String address;
    // 工作要求 经验不限、5天/周、在校/应届
    private String requirement;
    // 学历要求 0大专以上、1本科、2硕士
    private String education;
    // 岗位详情
    private String jobInformation;
    // 是否发布 0不发布（职位关闭）、1发布（招聘中）
    private String publishStatus;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中
    private String checkStatus;
    // 是否推荐到首页（管理员功能）  0不推荐、1推荐
    private String recommendStatus;

    public RecruitVO(Recruit recruit) {
        this.id = recruit.getId().toString();
        if (recruit.getPkCompany() != null) {
            this.pkCompany = recruit.getPkCompany().toString();
        }
        if (recruit.getPkHr() != null) {
            this.pkHr = recruit.getPkHr().toString();
        }
        this.title = recruit.getTitle();
        this.salary = recruit.getSalary();
        this.benefit = recruit.getBenefit();
        this.address = recruit.getAddress();
        this.requirement = recruit.getRequirement();
        if (recruit.getEducation() != null) {
            this.education = recruit.getEducation().toString();
        }
        this.jobInformation = recruit.getJobInformation();
        if (recruit.getPublishStatus() != null) {
            this.publishStatus = recruit.getPublishStatus().toString();
        }
        if (recruit.getCheckStatus() != null) {
            this.checkStatus = recruit.getCheckStatus().toString();
        }
        if (recruit.getRecommendStatus() != null) {
            this.recommendStatus = recruit.getRecommendStatus().toString();
        }
    }
}
