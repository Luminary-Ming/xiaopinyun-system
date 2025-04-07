package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.po.HR;
import com.xiaopinyun.bean.vo.HRVO;
import com.xiaopinyun.bean.dto.PageResult;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.mapper.HRInfoMapper;
import com.xiaopinyun.service.HRInfoService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class HRInfoServiceImpl extends ServiceImpl<HRInfoMapper, HR> implements HRInfoService {
    @Autowired
    private HRInfoMapper hrInfoMapper;

    /**
     * 查询 HR 信息
     */
    @Override
    public Result<HRVO> query(String id) {
        HR hr = hrInfoMapper.selectById(id);
        if (hr != null) {
            HRVO hrVO = new HRVO(hr);
            return Result.ok(hrVO);
        }
        return Result.ok(BizCode.NO_DATA);
    }

    /**
     * 条件查询、分页查询 HR 信息
     */
    @Override
    public Result<PageResult<List<HRVO>>> queryVO(Integer currentPage, Integer pageSize, String name, Integer sex, Integer status) {
        Page<HR> page = new Page<>();
        QueryWrapper<HR> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name)
                .like(sex == 0 || sex == 1, "sex", sex)
                .like(status >= 0 && status <= 3, "status", status);
        Page<HR> HRPage = hrInfoMapper.selectPage(page, wrapper);
        if (HRPage.getRecords() != null) {
            PageResult<List<HRVO>> pageResult = new PageResult<>();
            pageResult.setTotal(HRPage.getTotal());
            List<HR> hrList = HRPage.getRecords();
            List<HRVO> hrVOList = new ArrayList<>();
            for (HR hr : hrList) {
                HRVO hrVO = new HRVO(hr);
                hrVOList.add(hrVO);
            }
            pageResult.setData(hrVOList);
            return Result.ok(pageResult);
        }
        return Result.ok(BizCode.NO_DATA);
    }

    /**
     * 添加 HR 信息
     */
    @Override
    public Result<HRVO> insert(HRVO hrVO) {
        if (hrVO == null) {
            return Result.paramError(BizCode.PLEASE_WRITE);
        }
        // 校验对象的字段
        if (!checkHR(hrVO).isSuccess()) {
            return checkHR(hrVO);
        }
        HR hr = new HR();
        hr.setProfileImg(hrVO.getProfileImgUrl());
        hr.setName(hrVO.getName());
        hr.setSex(Integer.valueOf(hrVO.getSex()));
        if (hrVO.getPkCompany() != null) {
            hr.setPkCompany(Long.valueOf(hrVO.getPkCompany()));
        }
        hr.setIdentity(hrVO.getIdentity());
        hr.setTelephone(hrVO.getTelephone());
        hr.setEmail(hrVO.getEmail());

        if (save(hr)) {
            HRVO hrVOData = new HRVO(hr);
            return Result.ok(hrVOData);
        }
        return Result.error();
    }

    /**
     * 修改 HR 信息
     */
    @Override
    public Result<HRVO> update(HRVO hrVO) {
        // 如果没有要更新的数据直接返回更新成功
        if (hrVO == null) {
            return Result.ok();
        }
        // 校验对象的字段
        if (!checkHR(hrVO).isSuccess()) {
            return checkHR(hrVO);
        }

        HR hr = new HR();
        hr.setId(Long.valueOf(hrVO.getId()));
        hr.setProfileImg(hrVO.getProfileImgUrl());
        hr.setName(hrVO.getName());
        hr.setSex(Integer.valueOf(hrVO.getSex()));
        if (hrVO.getPkCompany() != null) {
            hr.setPkCompany(Long.valueOf(hrVO.getPkCompany()));
        }
        hr.setIdentity(hrVO.getIdentity());
        hr.setTelephone(hrVO.getTelephone());
        hr.setEmail(hrVO.getEmail());
        hr.setStatus(Integer.valueOf(hrVO.getStatus()));
        hr.setCheckStatus(Integer.valueOf(hrVO.getCheckStatus()));

        if (updateById(hr)) {
            return Result.ok(hrVO);
        }
        return Result.error();
    }

    /**
     * 根据 id 删除 HR 信息
     */
    @Override
    public Result<Void> delete(String id) {
        if (removeById(id)) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 校验所传入的字段
     */
    private Result<HRVO> checkHR(HRVO hrVO) {
        // 校检性别
        if (!Pattern.matches("[0-1]", hrVO.getSex())) {
            return Result.paramError("性别参数错误");
        }
        // 校验手机号
        if (!Pattern.matches("^1[3-9]\\d{9}$", hrVO.getTelephone())) {
            return Result.paramError("手机号格式错误");
        }
        // 校验电子邮箱
        if (!Pattern.matches("[a-zA-Z0-9]+@[A-Za-z0-9]+\\.[a-z0-9]{2,}", hrVO.getEmail())) {
            return Result.paramError("邮箱格式错误");
        }
        // 校检状态
        if (!Pattern.matches("[0-4]", hrVO.getStatus())) {
            return Result.paramError("校检状态错误");
        }
        return Result.ok();
    }
}
