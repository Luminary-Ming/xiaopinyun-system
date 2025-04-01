package com.xiaopinyun.controller;

import com.xiaopinyun.bean.dto.JobCollectDTO;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.vo.JobCollectVO;
import com.xiaopinyun.service.JobCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jobCollect")
public class JobCollectController {
    @Autowired
    private JobCollectService jobCollectService;

    @GetMapping("/{pkApplicant}")
    public Result<List<JobCollectDTO>> query(@PathVariable String pkApplicant) {
        return jobCollectService.query(pkApplicant);
    }

    @PostMapping
    public Result<JobCollectDTO> insert(@RequestBody JobCollectVO jobCollectVO) {
        return jobCollectService.insert(jobCollectVO);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable String id) {
        return jobCollectService.delete(id);
    }
}
