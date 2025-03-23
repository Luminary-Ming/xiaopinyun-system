package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.Advantage;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdvantageVO {
    // 主键
    private String id;
    // 学生信息主键
    private String pkApplicant;
    // 专业技能
    private String majorSkill;
    // 获奖荣誉
    private String honor;
    // 自我评价
    private String selfEvaluation;
    // 是否核实，审核通过（管理员功能）  0审核不通过、1审核通过、2审核中
    private String checkStatus;

    public AdvantageVO(Advantage advantage) {
        this.id = advantage.getId().toString();
        this.pkApplicant = advantage.getPkApplicant().toString();
        this.majorSkill = advantage.getMajorSkill();
        this.honor = advantage.getHonor();
        this.selfEvaluation = advantage.getSelfEvaluation();
        this.checkStatus = advantage.getCheckStatus().toString();
    }
}
