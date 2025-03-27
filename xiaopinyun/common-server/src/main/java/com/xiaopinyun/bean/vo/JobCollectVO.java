package com.xiaopinyun.bean.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 岗位收藏（展示层对象）
 */
@Data
@NoArgsConstructor
public class JobCollectVO {
    // 主键
    private Long id;
    // HR 姓名
    private String HRName;
    // HR 身份
    private String HRIdentity;
    // 招聘标题
    private String recruitTitle;
    // 工作地点
    private String address;
    // 薪资 例9k-15k
    private String salary;
    // 工作要求 经验不限、5天/周、在校/应届
    private String requirement;
    // 学历要求 0大专以上、1本科、2硕士
    private String education;
    // 公司头像
    private String profileImg;
    // 行业分类  互联网、电子商务、社交网络、金融科技、游戏娱乐、物流配送、内容创作与管理
    private String industryType;
    // 公司人数
    private Integer people;
}
