package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.po.Resume;
import com.xiaopinyun.bean.vo.Result;

public interface ResumeService extends IService<Resume> {

    /**
     * 根据 id 查询简历信息
     */
    Result<Resume> selectResume(Integer id);

    /**
     * 添加简历信息
     */
    Result<Resume> addResume(Resume resume);

    /**
     * 更新简历信息
     */
    Result<Resume> updateResume(Resume resume);

    /**
     * 根据 id 删除简历信息
     */
    Result<Void> deleteResumeById(Integer id);
}
