package com.xiaopinyun.pojo.vo;

import com.xiaopinyun.pojo.JobCollect;
import lombok.Data;

/**
 * 岗位收藏
 */
@Data
public class JobCollectVO extends JobCollect {
    // HR名字
    private String HRName;
    // HR身份
    private String HRIdentity;
    // 招聘岗位
    private String jobName;
    // 工作地点
    private String jobAddress;
    // 薪资
    private Double salary;
    // 工作要求 经验不限、5天/周、在校/应届
    private String requirement;
    // 学历要求 0大专以上 1本科 2硕士
    private Integer education;
    // 公司名
    private String companyName;
    // 公司人数
    private Integer people;
    // 行业分类   互联网、电子商务、社交网络、金融科技、游戏娱乐、物流配送、内容创作与管理
    private String industryType;
    // HR是否已将该职位删除  该职位已被HR删除
    private String jobStatus;
}
