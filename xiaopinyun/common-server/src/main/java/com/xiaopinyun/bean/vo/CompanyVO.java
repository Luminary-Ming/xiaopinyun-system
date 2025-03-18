package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.Company;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 公司信息（展示层对象）
 */
@Data
public class CompanyVO {
    // 主键
    private Long id;
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
    // 休假情况  0双休、1单休、2无休 --> 将数字转换为字符串
    private String holiday;
    // 加班情况  0弹性工作、1偶尔加班、2经常加班 --> 将数字转换为字符串
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
    // 注册资本 --> 将资金精度转换为字符串
    private String capital;
    // 成立日期  例1998-12-15
    private String foundDate;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中 --> 将数字转换为字符串
    private String checkStatus;

    // 构造方法，用于从 Company 对象转换
    public CompanyVO(Company company) {
        this.id = company.getId();
        this.profileImgUrl = company.getProfileImg();
        this.companyName = company.getCompanyName();
        this.people = company.getPeople();
        this.industryType = company.getIndustryType();
        this.workingHours = company.getWorkingHours();
        this.holiday = convertHoliday(company.getHoliday());
        this.overtime = convertOvertime(company.getOvertime());
        this.benefit = company.getBenefit();
        this.companyAddress = company.getCompanyAddress();
        this.introduce = company.getIntroduce();
        this.companyFullName = company.getCompanyFullName();
        this.legalPerson = company.getLegalPerson();
        this.capital = convertCapital(company.getCapital());
        this.foundDate = company.getFoundDate();
        this.checkStatus = convertCheckStatus(company.getCheckStatus());
    }

    // 私有方法，用于转换加班情况描述
    private String convertHoliday(Integer holiday) {
        return switch (holiday) {
            case 0 -> "双休";
            case 1 -> "单休";
            case 2 -> "无休";
            default -> "未知情况";
        };
    }

    // 私有方法，用于转换休假情况描述
    private String convertOvertime(Integer holiday) {
        return switch (holiday) {
            case 0 -> "弹性工作";
            case 1 -> "偶尔加班";
            case 2 -> "经常加班";
            default -> "未知情况";
        };
    }

    // 私有方法，用于转换注册资本
    private String convertCapital(BigDecimal capital) {
        return capital.toString();
    }

    // 私有方法，用于转换审核状态描述
    private String convertCheckStatus(Integer isCheck) {
        // 根据实际需求进行转换
        return switch (isCheck) {
            case 0 -> "未审核";
            case 1 -> "审核通过";
            case 2 -> "审核中";
            default -> "未知状态";
        };
    }
}
