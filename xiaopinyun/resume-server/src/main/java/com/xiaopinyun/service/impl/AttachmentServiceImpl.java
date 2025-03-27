package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.Attachment;
import com.xiaopinyun.bean.vo.AttachmentVO;
import com.xiaopinyun.mapper.AttachmentMapper;
import com.xiaopinyun.service.AttachmentService;
import com.xiaopinyun.util.BizCode;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements AttachmentService {
    /**
     * 查询附件
     */
    @Override
    public Result<List<AttachmentVO>> query(String pkApplicant) {
        QueryWrapper<Attachment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pk_applicant", pkApplicant);
        List<Attachment> attachments = list(queryWrapper);
        List<AttachmentVO> attachmentVOS = new ArrayList<>();
        if (!attachments.isEmpty()) {
            attachments.forEach(attachment -> {
                AttachmentVO attachmentVO = new AttachmentVO(attachment);
                attachmentVOS.add(attachmentVO);
            });
            return Result.ok(attachmentVOS);
        }
        return Result.ok(BizCode.NO_DATA);
    }

    /**
     * 查询 hr 附件
     */
    @Override
    public Result<List<AttachmentVO>> queryHR(String pkHr) {
        QueryWrapper<Attachment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pk_hr", pkHr);
        List<Attachment> attachments = list(queryWrapper);
        List<AttachmentVO> attachmentVOS = new ArrayList<>();
        if (!attachments.isEmpty()) {
            attachments.forEach(attachment -> {
                AttachmentVO attachmentVO = new AttachmentVO(attachment);
                attachmentVOS.add(attachmentVO);
            });
            return Result.ok(attachmentVOS);
        }
        return Result.ok(BizCode.NO_DATA);
    }

    /**
     * 新增附件
     */
    @Override
    public Result<AttachmentVO> insert(AttachmentVO attachmentVO) {
        if (attachmentVO == null) {
            return Result.paramError("请选择上传文件！");
        }
        QueryWrapper<Attachment> queryWrapper = new QueryWrapper<>();
        if (attachmentVO.getPkApplicant() != null) {
            queryWrapper.eq("pk_applicant", attachmentVO.getPkApplicant());
        }
        if (attachmentVO.getPkHr() != null) {
            queryWrapper.eq("pk_hr", attachmentVO.getPkHr());
        }
        List<Attachment> attachments = list(queryWrapper);
        if (attachments.size() == 3) {
            return Result.error("最多允许上传三个附件！");
        }
        Attachment attachment = new Attachment();
        if (attachmentVO.getPkApplicant() != null) {
            attachment.setPkApplicant(Long.valueOf(attachmentVO.getPkApplicant()));
        }
        if (attachmentVO.getPkHr() != null) {
            attachment.setPkHr(Long.valueOf(attachmentVO.getPkHr()));
        }
        attachment.setName(attachmentVO.getName());
        attachment.setSize(Double.valueOf(attachmentVO.getSize()));
        attachment.setResumePDF(attachmentVO.getResumePDF());
        if (save(attachment)) {
            AttachmentVO attachmentVOData = new AttachmentVO(attachment);
            return Result.ok(attachmentVOData);
        }
        return Result.error();
    }

    /**
     * 删除附件
     */
    @Override
    public Result<Void> delete(String id) {
        if (removeById(id)) {
            return Result.ok();
        }
        return Result.error();
    }
}
