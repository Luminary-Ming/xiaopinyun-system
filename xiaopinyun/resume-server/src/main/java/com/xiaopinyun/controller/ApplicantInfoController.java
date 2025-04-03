package com.xiaopinyun.controller;

import com.xiaopinyun.bean.dto.ApplicantDTO;
import com.xiaopinyun.bean.dto.ApplicantManageDTO;
import com.xiaopinyun.bean.dto.PageResult;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.Applicant;
import com.xiaopinyun.bean.vo.ApplicantVO;
import com.xiaopinyun.service.ApplicantInfoService;
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

/**
 * 学生信息
 */
@RestController
@RequestMapping("/applicant")
public class ApplicantInfoController {
    @Autowired
    private ApplicantInfoService applicantInfoService;

    /**
     * 查询学生信息
     */
    @GetMapping("/{id}")
    public Result<ApplicantDTO> selectApplicantById(@PathVariable String id) {
        return applicantInfoService.queryById(id);
    }

    /**
     * 条件查询、分页查询学生信息
     */
    @GetMapping
    public Result<PageResult<List<ApplicantDTO>>> selectApplicant(
            @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "sex", required = false) Integer sex,
            @RequestParam(name = "status", required = false) Integer status) {
        return applicantInfoService.query(currentPage, pageSize, name, sex, status);
    }

    /**
     * 查询所有学生信息
     */
    @GetMapping("/queryAll")
    public Result<List<ApplicantManageDTO>> queryAll() {
        return applicantInfoService.queryAll();
    }

    /**
     * 添加学生信息
     */
    @PostMapping
    public Result<ApplicantDTO> addApplicant(@RequestBody ApplicantVO applicantVO) {
        return applicantInfoService.save(applicantVO);
    }

    @PostMapping("/save")
    public Applicant save() {
        return applicantInfoService.save();
    }

    /**
     * 修改学生信息
     */
    @PutMapping
    public Result<ApplicantDTO> updateApplicant(@RequestBody ApplicantVO applicantVO) {
        return applicantInfoService.update(applicantVO);
    }

    /**
     * 根据 id 删除学生信息
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteApplicantById(@PathVariable String id) {
        return applicantInfoService.deleteById(id);
    }
}
