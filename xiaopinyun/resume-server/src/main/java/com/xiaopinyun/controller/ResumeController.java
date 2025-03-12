package com.xiaopinyun.controller;

import com.xiaopinyun.bean.po.Resume;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.bean.vo.ResumeVO;
import com.xiaopinyun.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    /**
     * 根据 id 查询简历信息
     */
    @GetMapping("/{id}")
    public Result<ResumeVO> queryVOById(@PathVariable Integer id) {
        return resumeService.queryVOById(id);
    }

    /**
     * 添加简历信息
     */
    @PostMapping
    public Result<ResumeVO> saveVO(@RequestBody Resume resume) {
        return resumeService.saveVO(resume);
    }

    /**
     * 更新简历信息
     */
    @PutMapping
    public Result<ResumeVO> updateVO(@RequestBody Resume resume) {
        return resumeService.updateVO(resume);
    }

    /**
     * 根据 id 删除简历信息
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteVOById(@PathVariable Integer id) {
        return resumeService.deleteVOById(id);
    }
}
