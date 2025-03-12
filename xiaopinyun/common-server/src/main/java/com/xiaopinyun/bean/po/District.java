package com.xiaopinyun.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 中国省市区
 */
@Data
@TableName("rc_district")
public class District {
    // 自增id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer districtId;
    // 父级关系
    private Integer pid;
    // 地区名称
    private String district;
    // 子属关系
    private Integer level;
    // 时间戳
    private String ts;
}
