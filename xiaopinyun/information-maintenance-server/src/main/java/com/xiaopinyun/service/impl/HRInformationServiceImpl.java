package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.po.HR;
import com.xiaopinyun.bean.vo.HRVO;
import com.xiaopinyun.bean.vo.PageResult;
import com.xiaopinyun.bean.vo.Result;
import com.xiaopinyun.mapper.HRInformationMapper;
import com.xiaopinyun.service.HRInformationService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class HRInformationServiceImpl extends ServiceImpl<HRInformationMapper, HR> implements HRInformationService {
    @Autowired
    private HRInformationMapper hrInformationMapper;

    /**
     * 根据 id 查询 HR 信息
     */
    @Override
    public Result<HRVO> queryVOById(Integer id) {
        HR hr = hrInformationMapper.selectById(id);
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
        Page<HR> HRPage = hrInformationMapper.selectPage(page, wrapper);
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
    public Result<HRVO> saveVO(HR hr) {
        if (hr == null) {
            return Result.paramError(BizCode.PLEASE_WRITE);
        }
        // 校验对象的字段
        if (checkHR(hr).isSuccess()) {
            return checkHR(hr);
        }
        // 添加需要审核
        hr.setCheckStatus(2);
        // 默认未删除
        hr.setDr(0);
        if (save(hr)) {
            // 添加之后的数据
            HR hrData = hrInformationMapper.selectById(hr.getId());
            HRVO hrVO = new HRVO(hrData);
            return Result.ok(hrVO);
        }
        return Result.error();
    }

    /**
     * 修改 HR 信息
     */
    @Override
    public Result<HRVO> updateVO(HR hr) {
        // 如果没有要更新的数据直接返回更新成功
        if (hr == null) {
            return Result.ok();
        }
        // 校验对象的字段
        if (checkHR(hr).isSuccess()) {
            return checkHR(hr);
        }
        // 更新需要审核
        hr.setCheckStatus(2);
        if (updateById(hr)) {
            // 修改之后的数据
            HR hrData = hrInformationMapper.selectById(hr.getId());
            HRVO hrVO = new HRVO(hrData);
            return Result.ok(hrVO);
        }
        return Result.error();
    }

    /**
     * 根据 id 删除 HR 信息
     */
    @Override
    public Result<Void> deleteVOById(Integer id) {
        if (removeById(id)) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 校验所传入的字段
     */
    private Result<HRVO> checkHR(HR hr) {
        // 校检性别
        if (!Pattern.matches("[0-1]", hr.getSex().toString())) {
            return Result.paramError("性别参数错误");
        }
        // 校验手机号
        if (!Pattern.matches("^1[3-9]\\d{9}$", hr.getTelephone())) {
            return Result.paramError("手机号格式错误");
        }
        // 校验电子邮箱
        if (!Pattern.matches("[a-zA-Z0-9]+@[A-Za-z0-9]+\\.[a-z0-9]{2,}", hr.getEmail())) {
            return Result.paramError("邮箱格式错误");
        }
        // 校检状态
        if (!Pattern.matches("[0-4]", hr.getStatus().toString())) {
            return Result.paramError("校检状态错误");
        }
        return Result.ok();
    }
}
