package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.mapper.CompanyInformationMapper;
import com.xiaopinyun.pojo.Company;
import com.xiaopinyun.pojo.vo.PageResult;
import com.xiaopinyun.pojo.vo.Result;
import com.xiaopinyun.service.CompanyInformationService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyInformationServiceImpl extends ServiceImpl<CompanyInformationMapper, Company> implements CompanyInformationService {
    @Autowired
    private CompanyInformationMapper companyInformationMapper;

    /**
     * 根据 id 查询公司信息
     */
    @Override
    public Result<Company> selectCompanyById(Integer id) {
        if (id == null || id < 1) {
            return Result.fail(BizCode.FAIL);
        }
        Company company = companyInformationMapper.selectById(id);
        if (company != null) {
            return Result.success(BizCode.SELECT_SUCCESS, company);
        }
        return Result.fail(BizCode.NO_DATA);
    }

    /**
     * 条件查询、分页查询公司信息
     */
    @Override
    public Result<PageResult<List<Company>>> selectCompany(Integer currentPage, Integer pageSize, String companyName, String industryType, String benefit) {
        Page<Company> page = new Page<>();
        QueryWrapper<Company> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(companyName), "company_name", companyName)
                .like(StringUtils.isNotBlank(industryType), "industry_type", industryType)
                .like(StringUtils.isNotBlank(benefit), "benefit", benefit);
        Page<Company> companyPage = companyInformationMapper.selectPage(page, wrapper);
        if (companyPage.getRecords() != null) {
            PageResult<List<Company>> pageResult = new PageResult<>();
            pageResult.setTotal(companyPage.getTotal());
            pageResult.setData(companyPage.getRecords());
            return Result.success(BizCode.SELECT_SUCCESS, pageResult);
        }
        return Result.fail(BizCode.NO_DATA);
    }

    /**
     * 添加公司信息
     */
    @Override
    public Result<Company> addCompany(Company company) {
        if (company == null) {
            return Result.fail(BizCode.ADD_NULL);
        }
        // 添加需要审核
        company.setIsCheck(2);
        // 默认未删除
        company.setIsDelete(0);
        if (save(company)) {
            Company companyData = companyInformationMapper.selectById(company.getId());
            return Result.success(BizCode.ADD_SUCCESS, companyData);
        }
        return Result.fail(BizCode.ADD_FAIL);
    }

    /**
     * 修改公司信息
     */
    @Override
    public Result<Company> updateCompany(Company company) {
        // 如果没有要更新的数据直接返回更新成功
        if (company == null) {
            return Result.success(BizCode.UPDATE_SUCCESS);
        }
        // 更新需要审核
        company.setIsCheck(2);
        if (updateById(company)) {
            // 修改之后的数据
            Company companyData = companyInformationMapper.selectById(company.getId());
            return Result.success(BizCode.UPDATE_SUCCESS, companyData);
        }
        return Result.fail(BizCode.UPDATE_FAIL);
    }

    /**
     * 根据 id 删除公司信息
     */
    @Override
    public Result<Void> deleteCompanyById(Integer id) {
        if (id == null || id < 1) {
            return Result.fail(BizCode.FAIL);
        }
        if (removeById(id)) {
            return Result.success(BizCode.DELETE_SUCCESS);
        }
        return Result.fail(BizCode.DELETE_FAIL);
    }
}
