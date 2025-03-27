package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.po.HR;
import com.xiaopinyun.bean.vo.HRVO;
import com.xiaopinyun.bean.dto.PageResult;
import com.xiaopinyun.bean.dto.Result;

import java.util.List;

public interface HRInfoService extends IService<HR> {

    /**
     * 查询 HR 信息
     */
    Result<HRVO> query(String id);

    /**
     * 条件查询、分页查询 HR 信息
     */
    Result<PageResult<List<HRVO>>> queryVO(Integer currentPage, Integer pageSize, String name, Integer sex, Integer status);

    /**
     * 添加 HR 信息
     */
    Result<HRVO> insert(HRVO hrVO);

    /**
     * 修改 HR 信息
     */
    Result<HRVO> update(HRVO hrVO);

    /**
     * 根据 id 删除 HR 信息
     */
    Result<Void> delete(String id);
}
