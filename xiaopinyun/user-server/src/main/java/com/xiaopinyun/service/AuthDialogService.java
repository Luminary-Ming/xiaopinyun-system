package com.xiaopinyun.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaopinyun.bean.dto.LoginDTO;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.dto.UserDTO;
import com.xiaopinyun.bean.po.User;

public interface AuthDialogService extends IService<User> {
    // 登录
    Result<UserDTO> login(LoginDTO login);

    // 注册
    Result<UserDTO> register(LoginDTO login);
}
