package com.xiaopinyun.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 公司信息
 */
@Data
@TableName("company")
public class Company {
    // 主键
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    // 头像
    private String profileImg;
    // 公司名
    private String companyName;
    // 公司人数 0-20人、20-99人、100-499人、500-999人、10000人以上
    private String people;
    // 行业分类   互联网、电子商务、社交网络、金融科技、游戏娱乐、物流配送、内容创作与管理
    private String industryType;
    // 工作时间 例08:30 - 17:30
    private String workingHours;
    // 休假情况   0双休、1单休、2无休
    private Integer holiday;
    // 加班情况   0弹性工作、1偶尔加班、2经常加班
    private Integer overtime;
    // 员工福利   例五险一金、定期体检、年终奖、带薪年假、节日福利
    private String benefit;
    // 公司地址
    private String companyAddress;
    // 公司介绍
    private String introduce;
    // 公司全称
    @TableField("company_full_name")
    private String companyFullName;
    // 法定代表人
    private String legalPerson;
    // 注册资本
    private BigDecimal capital;
    // 成立日期  例1998-12-15
    private String foundDate;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中
    private Integer checkStatus;
    // 是否已删除 0正常、1已删除
    @TableLogic
    private Integer dr;
    // 时间戳
    private String ts;
}
