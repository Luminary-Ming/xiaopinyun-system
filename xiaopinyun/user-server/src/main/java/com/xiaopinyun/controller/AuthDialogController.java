package com.xiaopinyun.controller;

import com.xiaopinyun.bean.dto.LoginDTO;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.dto.UserDTO;
import com.xiaopinyun.service.AuthDialogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthDialogController {
    @Autowired
    private AuthDialogService authDialogService;

    @PostMapping("/login")
    public Result<UserDTO> login(@RequestBody LoginDTO login) {
        return authDialogService.login(login);
    }

    @PostMapping("/register")
    public Result<UserDTO> register(@RequestBody LoginDTO login) {
        return authDialogService.register(login);
    }
}
