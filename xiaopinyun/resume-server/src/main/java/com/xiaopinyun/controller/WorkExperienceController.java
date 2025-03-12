package com.xiaopinyun.controller;

import com.xiaopinyun.bean.po.WorkExperience;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.bean.vo.WorkExperienceVO;
import com.xiaopinyun.service.WorkExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 张金龙
 * @Date 2025/2/19 10:42
 * @Version 1.0
 */
@RestController
@RequestMapping("/workExperience")
public class WorkExperienceController {
    @Autowired
    private WorkExperienceService workExperienceService;

    /**
     * 根据 id 查询工作经历
     */
    @GetMapping("/{id}")
    public Result<WorkExperienceVO> queryVOById(@PathVariable Integer id) {
        return workExperienceService.queryVOById(id);
    }

    /**
     * 添加工作经历
     */
    @PostMapping
    public Result<WorkExperienceVO> saveVO(@RequestBody WorkExperience workExperience) {
        return workExperienceService.saveVO(workExperience);
    }

    /**
     * 修改工作经历
     */
    @PutMapping
    public Result<WorkExperienceVO> updateVO(@RequestBody WorkExperience workExperience) {
        return workExperienceService.updateVO(workExperience);
    }

    /**
     * 根据 id 删除工作经历
     */
    @GetMapping("/{id}")
    public Result<Void> deleteVOById(@PathVariable Integer id) {
        return workExperienceService.deleteVOById(id);
    }
}
