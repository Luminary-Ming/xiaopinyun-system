package com.xiaopinyun.controller;

import com.xiaopinyun.bean.po.Company;
import com.xiaopinyun.bean.vo.PageResult;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.service.CompanyInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyInformationController {
    @Autowired
    private CompanyInformationService companyInformationService;

    /**
     * 根据 id 查询公司信息
     */
    @GetMapping("/{id}")
    public Result<Company> selectCompanyById(@PathVariable Integer id) {
        return companyInformationService.selectCompanyById(id);
    }

    /**
     * 条件查询、分页查询公司信息
     */
    @GetMapping
    public Result<PageResult<List<Company>>> selectCompany(
            @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "companyName", required = false) String companyName,
            @RequestParam(name = "industryType", required = false) String industryType,
            @RequestParam(name = "benefit", required = false) String benefit) {
        return companyInformationService.selectCompany(currentPage, pageSize, companyName, industryType, benefit);
    }

    /**
     * 添加公司信息
     */
    @PostMapping
    public Result<Company> addCompany(@RequestBody Company company) {
        return companyInformationService.addCompany(company);
    }

    /**
     * 修改公司信息
     */
    @PutMapping
    public Result<Company> updateCompany(@RequestBody Company company) {
        return companyInformationService.updateCompany(company);
    }

    /**
     * 根据 id 删除公司信息
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteCompanyById(@PathVariable Integer id) {
        return companyInformationService.deleteCompanyById(id);
    }
}
