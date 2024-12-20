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
    public Result<HRVO> selectHRById(Integer id) {
        if (id == null || id < 1) {
            return Result.fail(BizCode.FAIL);
        }
        HR hr = hrInformationMapper.selectById(id);
        if (hr != null) {
            HRVO hrVO = new HRVO(hr);
            return Result.success(BizCode.SELECT_SUCCESS, hrVO);
        }
        return Result.fail(BizCode.NO_DATA);
    }

    /**
     * 条件查询、分页查询 HR 信息
     */
    @Override
    public Result<PageResult<List<HRVO>>> selectHR(Integer currentPage, Integer pageSize, String name, Integer sex, Integer status) {
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
            return Result.success(BizCode.SELECT_SUCCESS, pageResult);
        }
        return Result.fail(BizCode.NO_DATA);
    }

    /**
     * 添加 HR 信息
     */
    @Override
    public Result<HRVO> addHR(HR hr) {
        if (hr == null) {
            return Result.fail(BizCode.ADD_NULL);
        }
        // 校验对象的字段
        if (checkHR(hr).getCode() != 2000) {
            return checkHR(hr);
        }
        // 添加需要审核
        hr.setIsCheck(2);
        // 默认未删除
        hr.setIsDelete(0);
        if (save(hr)) {
            // 添加之后的数据
            HR hrData = hrInformationMapper.selectById(hr.getId());
            HRVO hrVO = new HRVO(hrData);
            return Result.success(BizCode.ADD_SUCCESS, hrVO);
        }
        return Result.fail(BizCode.ADD_FAIL);
    }

    /**
     * 修改 HR 信息
     */
    @Override
    public Result<HRVO> updateHR(HR hr) {
        // 如果没有要更新的数据直接返回更新成功
        if (hr == null) {
            return Result.success(BizCode.UPDATE_SUCCESS);
        }
        // 校验对象的字段
        if (checkHR(hr).getCode() != 2000) {
            return checkHR(hr);
        }
        // 更新需要审核
        hr.setIsCheck(2);
        if (updateById(hr)) {
            // 修改之后的数据
            HR hrData = hrInformationMapper.selectById(hr.getId());
            HRVO hrVO = new HRVO(hrData);
            return Result.success(BizCode.UPDATE_SUCCESS, hrVO);
        }
        return Result.fail(BizCode.UPDATE_FAIL);
    }

    /**
     * 根据 id 删除 HR 信息
     */
    @Override
    public Result<Void> deleteHRById(Integer id) {
        if (id == null || id < 1) {
            return Result.fail(BizCode.FAIL);
        }
        if (removeById(id)) {
            return Result.success(BizCode.DELETE_SUCCESS);
        }
        return Result.fail(BizCode.DELETE_FAIL);
    }

    /**
     * 校验所传入的字段
     */
    public Result<HRVO> checkHR(HR hr) {
        // 校检性别
        if (!Pattern.matches("[0-1]", hr.getSex().toString())) {
            return Result.fail(BizCode.SEX_FAIL);
        }
        // 校验手机号
        if (!Pattern.matches("^1[3-9]\\d{9}$", hr.getTelephone())) {
            return Result.fail(BizCode.TELEPHONE_FORMAT_FAIL);
        }
        // 校验电子邮箱
        if (!Pattern.matches("[a-zA-Z0-9]+@[A-Za-z0-9]+\\.[a-z0-9]{2,}", hr.getEmail())) {
            return Result.fail(BizCode.EMAIL_FORMAT_FAIL);
        }
        // 校检状态
        if (!Pattern.matches("[0-4]", hr.getStatus().toString())) {
            return Result.fail(BizCode.STATUS_FAIL);
        }
        return Result.success(BizCode.SUCCESS);
    }
}
