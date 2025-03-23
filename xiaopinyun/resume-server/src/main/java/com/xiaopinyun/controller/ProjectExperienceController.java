package com.xiaopinyun.controller;

import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.vo.ProjectExperienceVO;
import com.xiaopinyun.service.ProjectExperienceService;
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
@RequestMapping("/projectExperience")
public class ProjectExperienceController {
    @Autowired
    private ProjectExperienceService projectExperienceService;

    /**
     * 根据 id 查询项目经历
     */
    @GetMapping("/{pkApplicant}")
    public Result<List<ProjectExperienceVO>> query(@PathVariable String pkApplicant) {
        return projectExperienceService.query(pkApplicant);
    }

    /**
     * 添加项目经历
     */
    @PostMapping
    public Result<ProjectExperienceVO> insert(@RequestBody ProjectExperienceVO projectExperienceVO) {
        return projectExperienceService.insert(projectExperienceVO);
    }

    /**
     * 修改项目经历
     */
    @PutMapping
    public Result<ProjectExperienceVO> update(@RequestBody ProjectExperienceVO projectExperienceVO) {
        return projectExperienceService.update(projectExperienceVO);
    }

    /**
     * 根据 id 删除项目经历
     */
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable String id) {
        return projectExperienceService.delete(id);
    }
}
