package com.xiaopinyun.controller;

import com.xiaopinyun.bean.po.EducationalBackground;
import com.xiaopinyun.bean.vo.EducationalBackgroundVO;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.service.EducationalBackgroundService;
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
@RequestMapping("/background")
public class EducationalBackgroundController {
    @Autowired
    private EducationalBackgroundService backgroundService;

    /**
     * 根据学生（aid）查询教育背景
     */
    @GetMapping("/{aid}")
    public Result<List<EducationalBackgroundVO>> queryVOByAid(@PathVariable Integer aid) {
        return backgroundService.queryVOByAid(aid);
    }

    /**
     * 添加教育背景
     */
    @PostMapping
    public Result<EducationalBackgroundVO> saveVO(@RequestBody EducationalBackground background) {
        return backgroundService.saveVO(background);
    }

    /**
     * 修改教育背景
     */
    @PutMapping
    public Result<EducationalBackgroundVO> updateVO(@RequestBody EducationalBackground background) {
        return backgroundService.updateVO(background);
    }

    /**
     * 根据 id 删除教育背景
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteVOById(@PathVariable Integer id) {
        return backgroundService.deleteVOById(id);
    }
}
