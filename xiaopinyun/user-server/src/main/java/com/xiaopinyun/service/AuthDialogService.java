package com.xiaopinyun.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.dto.LoginDTO;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.po.User;
import com.xiaopinyun.bean.vo.UserVO;

public interface AuthDialogService extends IService<User> {
    // 登录
    Result<UserVO> login(LoginDTO login);

    // 注册
    Result<UserVO> register(LoginDTO login);
}
