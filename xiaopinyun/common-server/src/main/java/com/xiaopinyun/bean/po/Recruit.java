package com.xiaopinyun.bean.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 招聘信息 / 职位信息
 */
@Data
@TableName("recruit")
public class Recruit {
    // 主键
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    // 公司信息主键
    private Long pkApplicant;
    // HR信息主键
    private Long pkHr;
    // 招聘标题
    private String title;
    // 薪资 例9k-15k
    private String salary;
    // 工作地点
    private String address;
    // 工作要求 经验不限、5天/周、在校/应届
    private String requirement;
    // 学历要求 0大专以上、1本科、2硕士
    private Integer education;
    // 岗位详情
    private String jobInformation;
    // 员工福利 例五险一金、定期体检、年终奖、带薪年假、节日福利
    private String benefit;
    // 是否发布  0不发布（职位关闭）、1发布（招聘中）
    private Integer publishStatus;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Integer checkStatus;
    // 是否推荐到首页（管理员功能）  0不推荐、1推荐
    private Integer recommendStatus;
    // 是否已删除 0正常、1已删除
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer dr;
    // 时间戳
    @TableField(fill = FieldFill.INSERT_UPDATE)  // 新增更新自动填充
    private LocalDateTime ts;
}
