package com.xiaopinyun.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.mapper.HRInformationMapper;
import com.xiaopinyun.pojo.HR;
import com.xiaopinyun.pojo.vo.PageResult;
import com.xiaopinyun.pojo.vo.Result;
import com.xiaopinyun.service.HRInformationService;
import com.xiaopinyun.util.BizCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HRInformationServiceImpl extends ServiceImpl<HRInformationMapper, HR> implements HRInformationService {
    @Autowired
    private HRInformationMapper hrInformationMapper;

    /**
     * 根据 id 查询 HR 信息
     */
    @Override
    public Result<HR> selectHRById(Integer id) {
        if (id == null || id < 1) {
            return Result.fail(BizCode.FAIL);
        }
        HR hr = hrInformationMapper.selectById(id);
        if (hr != null) {
            return Result.success(BizCode.SELECT_SUCCESS, hr);
        }
        return Result.fail(BizCode.NO_DATA);
    }

    /**
     * 条件查询、分页查询 HR 信息
     */
    @Override
    public Result<PageResult<List<HR>>> selectHR(Integer currentPage, Integer pageSize, String name, Integer sex, Integer status) {
        Page<HR> page = new Page<>();
        QueryWrapper<HR> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.isNotBlank(name), "name", name)
                .like(sex == 0 || sex == 1, "sex", sex)
                .like(status >= 0 && status <= 3, "status", status);
        Page<HR> HRPage = hrInformationMapper.selectPage(page, wrapper);
        if (HRPage.getRecords() != null) {
            PageResult<List<HR>> pageResult = new PageResult<>();
            pageResult.setTotal(HRPage.getTotal());
            pageResult.setData(HRPage.getRecords());
            return Result.success(BizCode.SELECT_SUCCESS, pageResult);
        }
        return Result.fail(BizCode.NO_DATA);
    }

    /**
     * 添加 HR 信息
     */
    @Override
    public Result<HR> addHR(HR hr) {
        if (hr == null) {
            return Result.fail(BizCode.ADD_NULL);
        }
        // 添加需要审核
        hr.setIsCheck(2);
        // 默认未删除
        hr.setIsDelete(0);
        if (save(hr)) {
            // 添加之后的数据
            HR hrData = hrInformationMapper.selectById(hr.getId());
            return Result.success(BizCode.ADD_SUCCESS, hrData);
        }
        return Result.fail(BizCode.ADD_FAIL);
    }

    /**
     * 修改 HR 信息
     */
    @Override
    public Result<HR> updateHR(HR hr) {
        // 如果没有要更新的数据直接返回更新成功
        if (hr == null) {
            return Result.success(BizCode.UPDATE_SUCCESS);
        }
        // 更新需要审核
        hr.setIsCheck(2);
        if (updateById(hr)) {
            // 修改之后的数据
            HR hrData = hrInformationMapper.selectById(hr.getId());
            return Result.success(BizCode.UPDATE_SUCCESS, hrData);
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
}
