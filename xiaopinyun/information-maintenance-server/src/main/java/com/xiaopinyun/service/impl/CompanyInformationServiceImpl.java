package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.po.Company;
import com.xiaopinyun.bean.vo.CompanyVO;
import com.xiaopinyun.bean.vo.PageResult;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.mapper.CompanyInformationMapper;
import com.xiaopinyun.service.CompanyInformationService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CompanyInformationServiceImpl extends ServiceImpl<CompanyInformationMapper, Company> implements CompanyInformationService {
    @Autowired
    private CompanyInformationMapper companyInformationMapper;

    /**
     * 根据 id 查询公司信息
     */
    @Override
    public Result<CompanyVO> queryVOById(Integer id) {
        Company company = companyInformationMapper.selectById(id);
        if (company != null) {
            CompanyVO companyVO = new CompanyVO(company);
            return Result.ok(companyVO);
        }
        return Result.ok(BizCode.NO_DATA);
    }

    /**
     * 条件查询、分页查询公司信息
     */
    @Override
    public Result<PageResult<List<CompanyVO>>> queryVO(Integer currentPage, Integer pageSize, String companyName, String industryType, String benefit) {
        Page<Company> page = new Page<>();
        QueryWrapper<Company> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(companyName), "company_name", companyName)
                .like(StringUtils.isNotBlank(industryType), "industry_type", industryType)
                .like(StringUtils.isNotBlank(benefit), "benefit", benefit);
        Page<Company> companyPage = companyInformationMapper.selectPage(page, wrapper);
        if (companyPage.getRecords() != null) {
            PageResult<List<CompanyVO>> pageResult = new PageResult<>();
            pageResult.setTotal(companyPage.getTotal());
            List<Company> CompanyList = companyPage.getRecords();
            List<CompanyVO> companyVOList = new ArrayList<>();
            for (Company company : CompanyList) {
                CompanyVO companyVO = new CompanyVO(company);
                companyVOList.add(companyVO);
            }
            pageResult.setData(companyVOList);
            return Result.ok(pageResult);
        }
        return Result.ok(BizCode.NO_DATA);
    }

    /**
     * 添加公司信息
     */
    @Override
    public Result<CompanyVO> saveVO(Company company) {
        if (company == null) {
            return Result.paramError(BizCode.PLEASE_WRITE);
        }
        // 校验对象的字段
        if (checkCompany(company).isSuccess()) {
            return checkCompany(company);
        }
        // 添加需要审核
        company.setCheckStatus(2);
        // 默认未删除
        company.setDr(0);
        if (save(company)) {
            Company companyData = companyInformationMapper.selectById(company.getId());
            CompanyVO companyVO = new CompanyVO(companyData);
            return Result.ok(companyVO);
        }
        return Result.error();
    }

    /**
     * 修改公司信息
     */
    @Override
    public Result<CompanyVO> updateVO(Company company) {
        // 如果没有要更新的数据直接返回更新成功
        if (company == null) {
            return Result.ok();
        }
        // 校验对象的字段
        if (checkCompany(company).isSuccess()) {
            return checkCompany(company);
        }
        // 更新需要审核
        company.setCheckStatus(2);
        if (updateById(company)) {
            // 修改之后的数据
            Company companyData = companyInformationMapper.selectById(company.getId());
            CompanyVO companyVO = new CompanyVO(companyData);
            return Result.ok(companyVO);
        }
        return Result.error();
    }

    /**
     * 根据 id 删除公司信息
     */
    @Override
    public Result<Void> deleteVOById(Integer id) {
        if (removeById(id)) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 校验所传入的字段
     */
    private Result<CompanyVO> checkCompany(Company company) {
        // 校检工作时间
        if (!Pattern.matches("\\b(?:[01]\\d|2[0-3]):[0-5]\\d\\s*-\\s*(?:[01]\\d|2[0-3]):[0-5]\\d\\b", company.getWorkingHours())) {
            return Result.paramError("工作时间格式错误");
        }
        // 校检休假情况
        if (!Pattern.matches("[0-2]", company.getHoliday().toString())) {
            return Result.paramError("休假情况错误");
        }
        // 校检加班情况
        if (!Pattern.matches("[0-2]", company.getOvertime().toString())) {
            return Result.paramError("加班情况错误");
        }
        // 检验注册资本
        if (!Pattern.matches("^\\d+(\\.\\d+)?$", company.getCapital().toString())) {
            return Result.paramError("注册资本只能为数值");
        }
        // 检验成立日期
        if (!Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", company.getFoundDate())) {
            return Result.paramError("成立日期格式不正确");
        }
        return Result.ok();
    }
}
