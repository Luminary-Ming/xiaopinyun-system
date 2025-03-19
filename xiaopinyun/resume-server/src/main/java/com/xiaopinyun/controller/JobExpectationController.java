package com.xiaopinyun.controller;

import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.JobExpectation;
import com.xiaopinyun.bean.vo.JobExpectationVO;
import com.xiaopinyun.service.JobExpectationService;
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
@RequestMapping("/jobExpectation")
public class JobExpectationController {
    @Autowired
    private JobExpectationService jobExpectationService;

    /**
     * 根据学生（aid）查询求职期望
     */
    @GetMapping("/{aid}")
    public Result<List<JobExpectationVO>> queryVOByAid(@PathVariable Integer aid) {
        return jobExpectationService.queryVOByAid(aid);
    }

    /**
     * 添加求职期望
     */
    @PostMapping
    public Result<JobExpectationVO> saveVO(@RequestBody JobExpectation jobExpectation) {
        return jobExpectationService.saveVO(jobExpectation);
    }

    /**
     * 修改求职期望
     */
    @PutMapping
    public Result<JobExpectationVO> updateVO(@RequestBody JobExpectation jobExpectation) {
        return jobExpectationService.updateVO(jobExpectation);
    }

    /**
     * 根据 id 删除求职期望
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteVOById(@PathVariable Integer id) {
        return jobExpectationService.deleteVOById(id);
    }
}
