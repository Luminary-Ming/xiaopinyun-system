package com.xiaopinyun.controller;

import com.xiaopinyun.bean.po.Applicant;
import com.xiaopinyun.bean.vo.ApplicantVO;
import com.xiaopinyun.bean.vo.PageResult;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.service.ApplicantInformationService;
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
@RequestMapping("/applicant")
public class ApplicantInformationController {
    @Autowired
    private ApplicantInformationService applicantInformationService;

    /**
     * 根据 id 查询学生信息
     */
    @GetMapping("/{id}")
    public Result<ApplicantVO> selectApplicantById(@PathVariable Integer id) {
        return applicantInformationService.queryVOById(id);
    }

    /**
     * 条件查询、分页查询学生信息
     */
    @GetMapping
    public Result<PageResult<List<ApplicantVO>>> selectApplicant(
            @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "sex", required = false) Integer sex,
            @RequestParam(name = "status", required = false) Integer status) {
        return applicantInformationService.queryVO(currentPage, pageSize, name, sex, status);
    }

    /**
     * 添加学生信息
     */
    @PostMapping
    public Result<ApplicantVO> addApplicant(@RequestBody Applicant applicant) {
        return applicantInformationService.saveVO(applicant);
    }

    /**
     * 修改学生信息
     */
    @PutMapping
    public Result<ApplicantVO> updateApplicant(@RequestBody Applicant applicant) {
        return applicantInformationService.updateVO(applicant);
    }

    /**
     * 根据 id 删除学生信息
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteApplicantById(@PathVariable Integer id) {
        return applicantInformationService.deleteVOById(id);
    }
}
