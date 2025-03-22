package com.xiaopinyun.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.dto.LoginDTO;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.Applicant;
import com.xiaopinyun.bean.po.User;
import com.xiaopinyun.bean.vo.UserVO;
import com.xiaopinyun.client.ApplicantFeignClient;
import com.xiaopinyun.mapper.AuthDialogMapper;
import com.xiaopinyun.service.AuthDialogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthDialogServiceImpl extends ServiceImpl<AuthDialogMapper, User> implements AuthDialogService {
    @Autowired
    private AuthDialogMapper authDialogMapper;
    @Autowired
    private ApplicantFeignClient applicantFeignClient;

    @Override
    public Result<UserVO> login(LoginDTO login) {
        if (StrUtil.isBlank(login.getUsername()) && StrUtil.isBlank(login.getPassword())) {
            return Result.paramError("请填写账号或密码");
        }
        if (StrUtil.isBlank(login.getRole())) {
            return Result.paramError("请选择身份");
        }


        return null;
    }

    @Override
    public Result<UserVO> register(LoginDTO login) {
        if (StrUtil.isBlank(login.getUsername()) && StrUtil.isBlank(login.getPassword())) {
            return Result.paramError("请填写账号或密码");
        }
        if (StrUtil.isBlank(login.getRole())) {
            return Result.paramError("请选择身份");
        }

        User user = new User();
        user.setUsername(login.getUsername());
        user.setPassword(login.getPassword());
        user.setRole(Integer.valueOf(login.getRole()));
        authDialogMapper.insert(user);
        if (login.getRole().equals("0")) {
            Applicant applicant = applicantFeignClient.save();
            user.setPk_applicant(applicant.getId());
            authDialogMapper.updateById(user);
        } else if (login.getRole().equals("1")) {

        } else {
        }
        UserVO userVO = new UserVO(user);
        return Result.ok(userVO);
    }


}
