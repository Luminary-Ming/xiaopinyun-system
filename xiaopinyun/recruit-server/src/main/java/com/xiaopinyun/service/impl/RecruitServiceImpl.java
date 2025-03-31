package com.xiaopinyun.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.dto.RecommendJobDTO;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.Company;
import com.xiaopinyun.bean.po.HR;
import com.xiaopinyun.bean.po.Recruit;
import com.xiaopinyun.bean.vo.CompanyVO;
import com.xiaopinyun.bean.vo.HRVO;
import com.xiaopinyun.bean.vo.RecruitVO;
import com.xiaopinyun.mapper.CompanyInfoMapper;
import com.xiaopinyun.mapper.HRInfoMapper;
import com.xiaopinyun.mapper.RecruitMapper;
import com.xiaopinyun.service.RecruitService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecruitServiceImpl extends ServiceImpl<RecruitMapper, Recruit> implements RecruitService {
    @Autowired
    private RecruitMapper recruitMapper;
    @Autowired
    private HRInfoMapper hrInfoMapper;
    @Autowired
    private CompanyInfoMapper companyInfoMapper;


    @Override
    public Result<RecruitVO> query(String id) {
        Recruit recruit = recruitMapper.selectById(id);
        if (recruit != null) {
            return Result.ok(new RecruitVO(recruit));
        }
        return Result.ok(BizCode.NO_DATA);
    }

    @Override
    public Result<List<RecruitVO>> queryByPkHr(String pkHr) {
        QueryWrapper<Recruit> wrapper = new QueryWrapper<>();
        wrapper.eq("pk_hr", pkHr);
        List<Recruit> recruits = recruitMapper.selectList(wrapper);
        if (!recruits.isEmpty()) {
            List<RecruitVO> recruitVOs = new ArrayList<>();
            for (Recruit recruit : recruits) {
                RecruitVO recruitVO = new RecruitVO(recruit);
                recruitVOs.add(recruitVO);
            }
            return Result.ok(recruitVOs);
        }
        return Result.ok(BizCode.NO_DATA);
    }

    @Override
    public Result<List<RecommendJobDTO>> queryRecommend() {
        QueryWrapper<Recruit> wrapper = new QueryWrapper<>();
        wrapper.eq("recommend_status", 1).eq("publish_status", 1);
        List<Recruit> recruits = recruitMapper.selectList(wrapper);
        if (!recruits.isEmpty()) {
            List<RecommendJobDTO> recommendJobDTOList = new ArrayList<>();
            for (Recruit recruit : recruits) {
                RecommendJobDTO recommendJobDTO = new RecommendJobDTO();
                recommendJobDTO.setRecruitVO(new RecruitVO(recruit));
                HR hr = hrInfoMapper.selectById(recruit.getPkHr());
                recommendJobDTO.setHrvo(new HRVO(hr));
                Company company = companyInfoMapper.selectById(recruit.getPkCompany());
                recommendJobDTO.setCompanyVO(new CompanyVO(company));
                recommendJobDTOList.add(recommendJobDTO);
            }
            return Result.ok(recommendJobDTOList);
        }
        return Result.ok(BizCode.NO_DATA);
    }

    @Override
    public Result<List<RecommendJobDTO>> queryAll() {
        QueryWrapper<Recruit> wrapper = new QueryWrapper<>();
        wrapper.eq("publish_status", 1);
        List<Recruit> recruits = recruitMapper.selectList(wrapper);
        if (!recruits.isEmpty()) {
            List<RecommendJobDTO> recommendJobDTOList = new ArrayList<>();
            for (Recruit recruit : recruits) {
                RecommendJobDTO recommendJobDTO = new RecommendJobDTO();
                recommendJobDTO.setRecruitVO(new RecruitVO(recruit));
                HR hr = hrInfoMapper.selectById(recruit.getPkHr());
                recommendJobDTO.setHrvo(new HRVO(hr));
                Company company = companyInfoMapper.selectById(recruit.getPkCompany());
                recommendJobDTO.setCompanyVO(new CompanyVO(company));
                recommendJobDTOList.add(recommendJobDTO);
            }
            return Result.ok(recommendJobDTOList);
        }
        return Result.ok(BizCode.NO_DATA);
    }

    @Override
    public Result<RecruitVO> insert(RecruitVO recruitVO) {
        if (recruitVO == null) {
            return Result.paramError(BizCode.PLEASE_WRITE);
        }
        Recruit recruit = new Recruit();
        if (StrUtil.isNotBlank(recruitVO.getPkCompany())) {
            recruit.setPkCompany(Long.valueOf(recruitVO.getPkCompany()));
        }
        recruit.setPkHr(Long.valueOf(recruitVO.getPkHr()));
        recruit.setTitle(recruitVO.getTitle());
        recruit.setSalary(recruitVO.getSalary());
        recruit.setAddress(recruitVO.getAddress());
        recruit.setRequirement(recruitVO.getRequirement());
        recruit.setEducation(Integer.valueOf(recruitVO.getEducation()));
        recruit.setJobInformation(recruitVO.getJobInformation());
        recruit.setBenefit(recruitVO.getBenefit());
        recruit.setPublishStatus(Integer.valueOf(recruitVO.getPublishStatus()));
        if (save(recruit)) {
            return Result.ok(new RecruitVO(recruit));
        }
        return Result.error();
    }

    @Override
    public Result<RecruitVO> update(RecruitVO recruitVO) {
        if (recruitVO == null) {
            return Result.ok();
        }
        Recruit recruit = new Recruit();
        recruit.setId(Long.valueOf(recruitVO.getId()));
        recruit.setPkCompany(Long.valueOf(recruitVO.getPkCompany()));
        recruit.setPkHr(Long.valueOf(recruitVO.getPkHr()));
        recruit.setTitle(recruitVO.getTitle());
        recruit.setSalary(recruitVO.getSalary());
        recruit.setAddress(recruitVO.getAddress());
        recruit.setRequirement(recruitVO.getRequirement());
        recruit.setEducation(Integer.valueOf(recruitVO.getEducation()));
        recruit.setJobInformation(recruitVO.getJobInformation());
        recruit.setBenefit(recruitVO.getBenefit());
        recruit.setPublishStatus(Integer.valueOf(recruitVO.getPublishStatus()));
        recruit.setCheckStatus(Integer.valueOf(recruitVO.getCheckStatus()));
        recruit.setRecommendStatus(Integer.valueOf(recruitVO.getRecommendStatus()));
        if (updateById(recruit)) {
            return Result.ok(recruitVO);
        }
        return Result.error();
    }

    @Override
    public Result<Void> delete(String id) {
        if (removeById(id)) {
            return Result.ok();
        }
        return Result.error();
    }
}
