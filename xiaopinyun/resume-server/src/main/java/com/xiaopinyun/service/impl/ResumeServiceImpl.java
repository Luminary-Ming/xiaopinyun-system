package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.mapper.ResumeMapper;
import com.xiaopinyun.bean.po.Resume;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.service.ResumeService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeServiceImpl extends ServiceImpl<ResumeMapper, Resume> implements ResumeService {
    @Autowired
    private ResumeMapper resumeMapper;

    /**
     * 根据 id 查询简历信息
     */
    @Override
    public Result<Resume> selectResume(Integer id) {
        Resume resume = resumeMapper.selectById(id);
        if(resume != null){
            return Result.ok(resume);
        }
        return Result.ok(BizCode.NO_DATA);
    }

    /**
     * 添加简历信息
     */
    @Override
    public Result<Resume> addResume(Resume resume) {
        return null;
    }

    /**
     * 更新简历信息
     */
    @Override
    public Result<Resume> updateResume(Resume resume) {
        return null;
    }

    /**
     * 根据 id 删除简历信息
     */
    @Override
    public Result<Void> deleteResumeById(Integer id) {
        return null;
    }
}
