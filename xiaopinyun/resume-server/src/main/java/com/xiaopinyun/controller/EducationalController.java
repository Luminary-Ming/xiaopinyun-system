package com.xiaopinyun.controller;

import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.vo.EducationalVO;
import com.xiaopinyun.service.EducationalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/background")
public class EducationalController {
    @Autowired
    private EducationalService backgroundService;

    /**
     * 根据学生（aid）查询教育背景
     */
    @GetMapping("/{pkApplicant}")
    public Result<EducationalVO> query(@PathVariable String pkApplicant) {
        return backgroundService.query(pkApplicant);
    }

    /**
     * 修改教育背景
     */
    @PutMapping
    public Result<EducationalVO> update(@RequestBody EducationalVO backgroundVO) {
        return backgroundService.update(backgroundVO);
    }
}
