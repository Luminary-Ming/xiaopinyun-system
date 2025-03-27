package com.xiaopinyun.bean.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 招聘信息 / 职位信息（展示层对象）
 */
@Data
@NoArgsConstructor
public class RecruitVO {
    // 主键
    private Long id;
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
    // 学历要求 0大专以上、1本科、2硕士 --> 将数字转换为对应的描述
    private String education;
    // 岗位详情
    private String jobInformation;
    // HR 姓名
    private String HRName;
    //状态 0-在线、1-昨日活跃、2-3天内活跃、3-7天内活跃、4-1月内活跃 --> 将数字转换为对应的描述
    private String status;
    // 所属公司
    private String companyName;
    // HR 身份 例人事总监、HR、招聘专家、人事专员、技术总监、经理、校招顾问、项目总监
    private String identity;
    // 公司介绍
    private String introduce;
    // 公司名称
    private String companyFullName;
    // 法定代表人
    private String legalPerson;
    // 行业分类互联网、电子商务、社交网络、金融科技、游戏娱乐、物流配送、内容创作与管理
    private String industryType;
    // 成立日期 例1998-12-15
    private String foundDate;
    // 注册资本
    private String capital;
    // 工作地址
    private String companyAddress;
    // 是否发布 0不发布（职位关闭）、1发布（招聘中）
    private Integer publishStatus;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中
    private Integer checkStatus;
    // 是否推荐到首页（管理员功能）  0不推荐、1推荐
    private Integer recommendStatus;
}
