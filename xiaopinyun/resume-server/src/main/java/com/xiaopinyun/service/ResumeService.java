package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.po.Resume;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.bean.vo.ResumeVO;

public interface ResumeService extends IService<Resume> {
    /**
     * 根据 id 查询简历信息
     */
    Result<ResumeVO> queryVOById(Integer id);

    /**
     * 添加简历信息
     */
    Result<ResumeVO> saveVO(Resume resume);

    /**
     * 更新简历信息
     */
    Result<ResumeVO> updateVO(Resume resume);

    /**
     * 根据 id 删除简历信息
     */
    Result<Void> deleteVOById(Integer id);
}
