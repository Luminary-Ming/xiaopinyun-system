package com.xiaopinyun.controller;

import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.vo.WorkExperienceVO;
import com.xiaopinyun.service.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/workExperience")
public class WorkExperienceController {
    @Autowired
    private WorkExperienceService workExperienceService;

    /**
     * 根据 id 查询工作经历
     */
    @GetMapping("/{PKApplicant}")
    public Result<List<WorkExperienceVO>> query(@PathVariable String PKApplicant) {
        return workExperienceService.query(PKApplicant);
    }

    /**
     * 添加工作经历
     */
    @PostMapping
    public Result<WorkExperienceVO> insert(@RequestBody WorkExperienceVO workExperienceVO) {
        return workExperienceService.insert(workExperienceVO);
    }

    /**
     * 修改工作经历
     */
    @PutMapping
    public Result<WorkExperienceVO> update(@RequestBody WorkExperienceVO workExperienceVO) {
        return workExperienceService.update(workExperienceVO);
    }

    /**
     * 根据 id 删除工作经历
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable String id) {
        return workExperienceService.delete(id);
    }
}
