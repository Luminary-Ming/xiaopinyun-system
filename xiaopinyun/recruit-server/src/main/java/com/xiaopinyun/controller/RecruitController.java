package com.xiaopinyun.controller;

import com.xiaopinyun.bean.dto.RecommendJobDTO;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.vo.RecruitVO;
import com.xiaopinyun.service.RecruitService;
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
@RequestMapping("/recruit")
public class RecruitController {
    @Autowired
    private RecruitService recruitService;

    @GetMapping("/{id}")
    public Result<RecruitVO> query(@PathVariable String id) {
        return recruitService.query(id);
    }

    @GetMapping("/queryByPkHr/{pkHr}")
    public Result<List<RecruitVO>> queryByPkHr(@PathVariable String pkHr) {
        return recruitService.queryByPkHr(pkHr);
    }

    @GetMapping("/queryRecommend")
    public Result<List<RecommendJobDTO>> queryRecommend() {
        return recruitService.queryRecommend();
    }

    @GetMapping("/queryAll")
    public Result<List<RecommendJobDTO>> queryAll() {
        return recruitService.queryAll();
    }

    @PostMapping
    public Result<RecruitVO> insert(@RequestBody RecruitVO recruitVO) {
        return recruitService.insert(recruitVO);
    }

    @PutMapping
    public Result<RecruitVO> update(@RequestBody RecruitVO recruitVO) {
        return recruitService.update(recruitVO);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable String id) {
        return recruitService.delete(id);
    }
}
