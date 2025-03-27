package com.xiaopinyun.controller;

import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.vo.AttachmentVO;
import com.xiaopinyun.service.AttachmentService;
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
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    @GetMapping("/{pkApplicant}")
    public Result<List<AttachmentVO>> query(@PathVariable String pkApplicant) {
        return attachmentService.query(pkApplicant);
    }

    @GetMapping("/queryHR/{pkHr}")
    public Result<List<AttachmentVO>> queryHR(@PathVariable String pkHr) {
        return attachmentService.queryHR(pkHr);
    }

    @PostMapping
    public Result<AttachmentVO> insert(@RequestBody AttachmentVO attachmentVO) {
        return attachmentService.insert(attachmentVO);
    }

    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable String id) {
        return attachmentService.delete(id);
    }
}
