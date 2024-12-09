package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.mapper.ResumeMapper;
import com.xiaopinyun.pojo.Resume;
import com.xiaopinyun.pojo.vo.Result;
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
        if(id == null || id < 1){
            return Result.fail(BizCode.FAIL);
        }
        Resume resume = resumeMapper.selectById(id);
        if(resume != null){
            return Result.success(BizCode.SELECT_SUCCESS,resume);
        }
        return Result.fail(BizCode.NO_DATA);
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
