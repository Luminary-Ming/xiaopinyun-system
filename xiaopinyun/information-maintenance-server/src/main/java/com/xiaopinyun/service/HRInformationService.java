package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.po.HR;
import com.xiaopinyun.bean.vo.PageResult;
import com.xiaopinyun.bean.vo.Result;

import java.util.List;

public interface HRInformationService extends IService<HR> {

    /**
     * 根据 id 查询 HR 信息
     */
    Result<HR> selectHRById(Integer id);

    /**
     * 条件查询、分页查询 HR 信息
     */
    Result<PageResult<List<HR>>> selectHR(Integer currentPage, Integer pageSize, String name, Integer sex, Integer status);

    /**
     * 添加 HR 信息
     */
    Result<HR> addHR(HR hr);

    /**
     * 修改 HR 信息
     */
    Result<HR> updateHR(HR hr);

    /**
     * 根据 id 删除 HR 信息
     */
    Result<Void> deleteHRById(Integer id);
}
