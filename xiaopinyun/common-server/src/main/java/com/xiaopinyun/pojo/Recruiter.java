package com.xiaopinyun.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 招聘信息 / 职位信息
 */
@Data
@TableName("recruiter")
public class Recruiter {
    // id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 公司信息id
    private Integer cid;
    // HRid
    private Integer hid;
    // 招聘岗位
    private String job;
    // 薪资
    private Double salary;
    // 工作地点
    private String address;
    // 工作要求 经验不限、5天/周、在校/应届
    private String requirement;
    // 学历要求 0大专以上、1本科、2硕士
    private Integer education;
    // 岗位详情
    private String jobInformation;
    // 员工福利
    private String benefit;
    // 是否发布  0不发布、1发布
    private Integer isPublish;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过
    private Integer isCheck;
    // 是否推荐到首页（管理员功能）  0不推荐、1推荐
    private Integer isRecommend;
    // 是否已删除 0正常、1已删除
    @TableLogic
    private Integer isDelete;
}
