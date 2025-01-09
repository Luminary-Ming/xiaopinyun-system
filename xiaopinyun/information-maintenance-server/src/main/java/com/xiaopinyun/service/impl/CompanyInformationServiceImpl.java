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
        if (id == null || id < 1) {
            return Result.fail(BizCode.FAIL);
        }
        Company company = companyInformationMapper.selectById(id);
        if (company != null) {
            CompanyVO companyVO = new CompanyVO(company);
            return Result.success(BizCode.SELECT_SUCCESS, companyVO);
        }
        return Result.fail(BizCode.NO_DATA);
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
            return Result.success(BizCode.SELECT_SUCCESS, pageResult);
        }
        return Result.fail(BizCode.NO_DATA);
    }

    /**
     * 添加公司信息
     */
    @Override
    public Result<CompanyVO> saveVO(Company company) {
        if (company == null) {
            return Result.fail(BizCode.ADD_NULL);
        }
        // 校验对象的字段
        if (checkCompany(company).getCode() != 2000) {
            return checkCompany(company);
        }
        // 添加需要审核
        company.setIsCheck(2);
        // 默认未删除
        company.setIsDelete(0);
        if (save(company)) {
            Company companyData = companyInformationMapper.selectById(company.getId());
            CompanyVO companyVO = new CompanyVO(companyData);
            return Result.success(BizCode.ADD_SUCCESS, companyVO);
        }
        return Result.fail(BizCode.ADD_FAIL);
    }

    /**
     * 修改公司信息
     */
    @Override
    public Result<CompanyVO> updateVO(Company company) {
        // 如果没有要更新的数据直接返回更新成功
        if (company == null) {
            return Result.success(BizCode.UPDATE_SUCCESS);
        }
        // 校验对象的字段
        if (checkCompany(company).getCode() != 2000) {
            return checkCompany(company);
        }
        // 更新需要审核
        company.setIsCheck(2);
        if (updateById(company)) {
            // 修改之后的数据
            Company companyData = companyInformationMapper.selectById(company.getId());
            CompanyVO companyVO = new CompanyVO(companyData);
            return Result.success(BizCode.UPDATE_SUCCESS, companyVO);
        }
        return Result.fail(BizCode.UPDATE_FAIL);
    }

    /**
     * 根据 id 删除公司信息
     */
    @Override
    public Result<Void> deleteVOById(Integer id) {
        if (id == null || id < 1) {
            return Result.fail(BizCode.FAIL);
        }
        if (removeById(id)) {
            return Result.success(BizCode.DELETE_SUCCESS);
        }
        return Result.fail(BizCode.DELETE_FAIL);
    }

    /**
     * 校验所传入的字段
     */
    private Result<CompanyVO> checkCompany(Company company) {
        // 校检工作时间
        if (!Pattern.matches("\\b(?:[01]\\d|2[0-3]):[0-5]\\d\\s*-\\s*(?:[01]\\d|2[0-3]):[0-5]\\d\\b", company.getWorkingHours())) {
            return Result.fail(BizCode.WORKINGHOURS_FORMAT_FAIL);
        }
        // 校检休假情况
        if (!Pattern.matches("[0-2]", company.getHoliday().toString())) {
            return Result.fail(BizCode.HOLIDAY_FAIL);
        }
        // 校检加班情况
        if (!Pattern.matches("[0-2]", company.getOvertime().toString())) {
            return Result.fail(BizCode.OVERTIME_FAIL);
        }
        // 检验注册资本
        if (!Pattern.matches("^\\d+(\\.\\d+)?$", company.getCapital().toString())) {
            return Result.fail(BizCode.CAPITAL_FAIL);
        }
        // 检验成立日期
        if (!Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", company.getFoundDate())) {
            return Result.fail(BizCode.FOUNDDATE_FORMAT_FAIL);
        }
        return Result.success(BizCode.SUCCESS);
    }
}
