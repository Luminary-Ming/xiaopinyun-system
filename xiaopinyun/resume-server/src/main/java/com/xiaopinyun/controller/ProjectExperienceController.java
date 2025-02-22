package com.xiaopinyun.controller;

import com.xiaopinyun.bean.po.ProjectExperience;
import com.xiaopinyun.bean.vo.ProjectExperienceVO;
import com.xiaopinyun.bean.vo.Result;
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

/**
 * @Author 张金龙
 * @Date 2025/2/19 10:41
 * @Version 1.0
 */
@RestController
@RequestMapping("/projectExperience")
public class ProjectExperienceController {
    @Autowired
    private ProjectExperienceService projectExperienceService;

    /**
     * 根据 id 查询项目经历
     */
    @GetMapping("/{id}")
    public Result<ProjectExperienceVO> queryVOById(@PathVariable Integer id) {
        return projectExperienceService.queryVOById(id);
    }

    /**
     * 添加项目经历
     */
    @PostMapping
    public Result<ProjectExperienceVO> saveVO(@RequestBody ProjectExperience projectExperience) {
        return projectExperienceService.saveVO(projectExperience);
    }

    /**
     * 修改项目经历
     */
    @PutMapping
    public Result<ProjectExperienceVO> updateVO(@RequestBody ProjectExperience projectExperience) {
        return projectExperienceService.updateVO(projectExperience);
    }

    /**
     * 根据 id 删除项目经历
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteVOById(@PathVariable Integer id) {
        return projectExperienceService.deleteVOById(id);
    }
}
