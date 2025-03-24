package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.Attachment;
import com.xiaopinyun.bean.vo.AttachmentVO;

import java.util.List;

public interface AttachmentService extends IService<Attachment> {
    /**
     * 查询附件
     */
    Result<List<AttachmentVO>> query(String pkApplicant);

    /**
     * 新增附件
     */
    Result<AttachmentVO> insert(AttachmentVO attachmentVO);

    /**
     * 删除附件
     */
    Result<Void> delete(String id);
}
