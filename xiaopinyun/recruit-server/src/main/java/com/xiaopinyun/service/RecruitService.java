package com.xiaopinyun.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.dto.RecommendJobDTO;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.Recruit;
import com.xiaopinyun.bean.vo.RecruitVO;

import java.util.List;

public interface RecruitService extends IService<Recruit> {
    Result<RecruitVO> query(String id);
    Result<List<RecruitVO>> queryByPkHr(String pkHr);
    // 首页推荐职位
    Result<List<RecommendJobDTO>> queryRecommend();
    // 所有职位
    Result<List<RecommendJobDTO>> queryAll();
    Result<RecruitVO> insert(RecruitVO recruitVO);
    Result<RecruitVO> update(RecruitVO recruitVO);
    Result<Void> delete(String id);
}