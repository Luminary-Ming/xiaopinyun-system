package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.po.Company;
import com.xiaopinyun.bean.vo.CompanyVO;
import com.xiaopinyun.bean.dto.PageResult;
import com.xiaopinyun.bean.dto.Result;

import java.util.List;

public interface CompanyInfoService extends IService<Company> {
    /**
     * 查询公司信息
     */
    Result<CompanyVO> query(String id);

    /**
     * 条件查询、分页查询公司信息
     */
    Result<PageResult<List<CompanyVO>>> queryVO(Integer currentPage, Integer pageSize, String companyName, String industryType, String benefit);

    /**
     * 添加公司信息
     */
    Result<CompanyVO> insert(CompanyVO companyVO);

    /**
     * 修改公司信息
     */
    Result<CompanyVO> update(CompanyVO companyVO);

    /**
     * 根据 id 删除公司信息
     */
    Result<Void> delete(String id);
}
