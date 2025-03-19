package com.xiaopinyun.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页结果集
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    // 查询总记录数
    private Long total;
    // 返回数据
    private T data;
}
