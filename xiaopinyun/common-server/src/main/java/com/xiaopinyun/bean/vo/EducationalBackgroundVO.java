package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.EducationalBackground;
import lombok.Data;

/**
 * 教育背景（展示层对象）
 */
@Data
public class EducationalBackgroundVO {
    // id
    private Integer id;
    // 学校名称
    private String name;
    // 学历 0初中及以下 1中专 2高中 3大专 4本科 5硕士 6博士 --> 将数字转换为字符串
    private String qualification;
    // 专业
    private String major;
    // 入学时间  例2021 2021-2025
    private String startTime;
    // 毕业时间  例2025
    private String endTime;
    // 主修课程
    private String majorCourse;

    // 构造方法，用于从 EducationalBackground 对象转换
    public EducationalBackgroundVO(EducationalBackground educationalBackground) {
        this.id = educationalBackground.getId();
        this.name = educationalBackground.getName();
        this.qualification = convertQualification(educationalBackground.getQualification());
        this.major = educationalBackground.getMajor();
        this.startTime = educationalBackground.getStartTime();
        this.endTime = educationalBackground.getEndTime();
        this.majorCourse = educationalBackground.getMajorCourse();
    }

    // 私有方法，用于转换学历描述
    private String convertQualification(Integer qualification) {
        return switch (qualification) {
            case 0 -> "初中及以下";
            case 1 -> "中专";
            case 2 -> "高中";
            case 3 -> "大专";
            case 4 -> "本科";
            case 5 -> "硕士";
            case 6 -> "博士";
            default -> "请选择学历";
        };
    }
}
