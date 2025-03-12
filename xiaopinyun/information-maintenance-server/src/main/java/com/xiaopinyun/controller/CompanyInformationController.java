package com.xiaopinyun.controller;

import com.xiaopinyun.bean.po.Company;
import com.xiaopinyun.bean.vo.CompanyVO;
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
    public Result<CompanyVO> selectCompanyById(@PathVariable Integer id) {
        return companyInformationService.queryVOById(id);
    }

    /**
     * 条件查询、分页查询公司信息
     */
    @GetMapping
    public Result<PageResult<List<CompanyVO>>> selectCompany(
            @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "companyName", required = false) String companyName,
            @RequestParam(name = "industryType", required = false) String industryType,
            @RequestParam(name = "benefit", required = false) String benefit) {
        return companyInformationService.queryVO(currentPage, pageSize, companyName, industryType, benefit);
    }

    /**
     * 添加公司信息
     */
    @PostMapping
    public Result<CompanyVO> addCompany(@RequestBody Company company) {
        return companyInformationService.saveVO(company);
    }

    /**
     * 修改公司信息
     */
    @PutMapping
    public Result<CompanyVO> updateCompany(@RequestBody Company company) {
        return companyInformationService.updateVO(company);
    }

    /**
     * 根据 id 删除公司信息
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteCompanyById(@PathVariable Integer id) {
        return companyInformationService.deleteVOById(id);
    }
}
