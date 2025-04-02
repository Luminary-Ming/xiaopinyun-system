package com.xiaopinyun.controller;

import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.dto.ResumeSubmitDTO;
import com.xiaopinyun.bean.dto.SubmitInfoDTO;
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
     * 学生查看hr回复
     */
    @GetMapping("/queryReply/{pkApplicant}")
    public Result<List<ResumeSubmitDTO>> queryReply(@PathVariable String pkApplicant) {
        return resumeSubmitService.queryReply(pkApplicant);
    }

    /**
     * hr查看投递情况
     */
    @GetMapping("/querySubmitInfo/{pkHr}")
    public Result<List<SubmitInfoDTO>> querySubmitInfo(@PathVariable String pkHr) {
        return resumeSubmitService.querySubmitInfo(pkHr);
    }

    /**
     * hr查看感兴趣的简历
     */
    @GetMapping("/queryInterest/{pkHr}")
    public Result<List<SubmitInfoDTO>> queryInterest(@PathVariable String pkHr) {
        return resumeSubmitService.queryInterest(pkHr);
    }

    /**
     * 投递简历
     */
    @PostMapping
    public Result<ResumeSubmitDTO> submit(@RequestBody ResumeSubmitVO resumeSubmitVO) {
        return resumeSubmitService.submit(resumeSubmitVO);
    }

    /**
     * hr回复
     */
    @PostMapping("/reply")
    public Result<Void> reply(@RequestBody ResumeSubmitVO resumeSubmitVO) {
        return resumeSubmitService.reply(resumeSubmitVO);
    }

    /**
     * 感兴趣
     */
    @PostMapping("/interest")
    public Result<SubmitInfoDTO> interest(@RequestBody SubmitInfoDTO submitInfoDTO) {
        return resumeSubmitService.interest(submitInfoDTO);
    }

    /**
     * 取消感兴趣
     */
    @PostMapping("/removeInterest")
    public Result<SubmitInfoDTO> removeInterest(@RequestBody SubmitInfoDTO submitInfoDTO) {
        return resumeSubmitService.removeInterest(submitInfoDTO);
    }
}
