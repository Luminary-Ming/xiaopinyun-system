package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.po.Company;
import com.xiaopinyun.bean.vo.PageResult;
import com.xiaopinyun.bean.vo.Result;

import java.util.List;

public interface CompanyInformationService extends IService<Company> {
    /**
     * 根据 id 查询公司信息
     */
    Result<Company> selectCompanyById(Integer id);

    /**
     * 条件查询、分页查询公司信息
     */
    Result<PageResult<List<Company>>> selectCompany(Integer currentPage, Integer pageSize, String companyName, String industryType, String benefit);

    /**
     * 添加公司信息
     */
    Result<Company> addCompany(Company company);

    /**
     * 修改公司信息
     */
    Result<Company> updateCompany(Company company);

    /**
     * 根据 id 删除公司信息
     */
    Result<Void> deleteCompanyById(Integer id);
}
