package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.po.Advantage;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.bean.vo.ResumeVO;

public interface ResumeService extends IService<Advantage> {
    /**
     * 根据 id 查询简历信息
     */
    Result<ResumeVO> queryVOById(Integer id);

    /**
     * 添加简历信息
     */
    Result<ResumeVO> saveVO(Advantage advantage);

    /**
     * 更新简历信息
     */
    Result<ResumeVO> updateVO(Advantage advantage);

    /**
     * 根据 id 删除简历信息
     */
    Result<Void> deleteVOById(Integer id);
}
