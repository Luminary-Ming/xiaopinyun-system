package com.xiaopinyun.controller;

import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.vo.AdvantageVO;
import com.xiaopinyun.service.AdvantageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 个人优势（专业技能、获奖荣誉、自我评价）
 */
@RestController
@RequestMapping("/advantage")
public class AdvantageController {
    @Autowired
    private AdvantageService advantageService;

    @GetMapping("/{pkApplicant}")
    public Result<AdvantageVO> query(@PathVariable String pkApplicant) {
        return advantageService.query(pkApplicant);
    }

    @PutMapping
    public Result<AdvantageVO> update(@RequestBody AdvantageVO advantageVO) {
        return advantageService.update(advantageVO);
    }
}
