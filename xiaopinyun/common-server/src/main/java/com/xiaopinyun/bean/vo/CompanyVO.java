package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.Company;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公司信息（展示层对象）
 */
@Data
@NoArgsConstructor
public class CompanyVO {
    // 主键
    private String id;
    // 头像
    private String profileImgUrl;
    // 公司名
    private String companyName;
    // 公司人数  0-20人、20-99人、100-499人、500-999人、10000人以上
    private String people;
    // 行业分类  互联网、电子商务、社交网络、金融科技、游戏娱乐、物流配送、内容创作与管理
    private String industryType;
    // 工作时间  例8:30 - 17:30
    private String workingHours;
    // 休假情况  0双休、1单休、2无休
    private String holiday;
    // 加班情况  0弹性工作、1偶尔加班、2经常加班
    private String overtime;
    // 员工福利  例五险一金、定期体检、年终奖、带薪年假、节日福利
    private String benefit;
    // 公司地址
    private String companyAddress;
    // 公司介绍
    private String introduce;
    // 公司全称
    private String companyFullName;
    // 法定代表人
    private String legalPerson;
    // 注册资本  单位万人民币
    private String capital;
    // 成立日期  例1998-12-15
    private String foundDate;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中
    private String checkStatus;

    public CompanyVO(Company company) {
        this.id = company.getId().toString();
        this.profileImgUrl = company.getProfileImg();
        this.companyName = company.getCompanyName();
        this.people = company.getPeople();
        this.industryType = company.getIndustryType();
        this.workingHours = company.getWorkingHours();
        if (company.getHoliday() == null) {
            this.holiday = null;
        } else {
            this.holiday = company.getHoliday().toString();
        }
        if (company.getOvertime() == null) {
            this.overtime = null;
        } else {
            this.overtime = company.getOvertime().toString();
        }
        this.benefit = company.getBenefit();
        this.companyAddress = company.getCompanyAddress();
        this.introduce = company.getIntroduce();
        this.companyFullName = company.getCompanyFullName();
        this.legalPerson = company.getLegalPerson();
        if (company.getCapital() == null) {
            this.capital = null;
        } else {
            this.capital = company.getCapital().toString();
        }
        this.foundDate = company.getFoundDate();
        this.checkStatus = company.getCheckStatus().toString();
    }
}
