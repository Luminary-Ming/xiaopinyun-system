package com.xiaopinyun.controller;

import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.dto.ResumeSubmitDTO;
import com.xiaopinyun.bean.vo.ResumeSubmitVO;
import com.xiaopinyun.service.ResumeSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/resumeSubmit")
public class ResumeSubmitController {
    @Autowired
    private ResumeSubmitService resumeSubmitService;

    /**
     * 学生查询投递情况
     */
    @GetMapping("/queryByPkApplicant/{pkApplicant}")
    public Result<List<ResumeSubmitDTO>> queryByPkApplicant(@PathVariable String pkApplicant) {
        return resumeSubmitService.queryByPkApplicant(pkApplicant);
    }

    /**
     * hr查看谁已投递
     */
    @GetMapping("/queryByPkRecruit/{pkRecruit}")
    public Result<List<ResumeSubmitDTO>> queryByPkRecruit(@PathVariable String pkRecruit) {
        return resumeSubmitService.queryByPkRecruit(pkRecruit);
    }

    /**
     * 学生查看hr回复
     */
    @GetMapping("/queryReply/{pkApplicant}")
    public Result<List<ResumeSubmitDTO>> queryReply(@PathVariable String pkApplicant) {
        return resumeSubmitService.queryReply(pkApplicant);
    }

    /**
     * 投递简历
     */
    @PostMapping
    public Result<ResumeSubmitDTO> submit(@RequestBody ResumeSubmitVO resumeSubmitVO) {
        return resumeSubmitService.submit(resumeSubmitVO);
    }
}
