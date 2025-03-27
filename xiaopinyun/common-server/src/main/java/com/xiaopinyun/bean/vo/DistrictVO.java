package com.xiaopinyun.bean.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 中国省市区（展示层对象）
 */
@Data
@NoArgsConstructor
public class DistrictVO {
    // 省
    private String province;
    // 市（区）
    private String municipality;
}
