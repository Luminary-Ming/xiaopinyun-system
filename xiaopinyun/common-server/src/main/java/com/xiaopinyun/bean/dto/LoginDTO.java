package com.xiaopinyun.bean.dto;

import lombok.Data;

@Data
public class LoginDTO {
    // 账号
    private String username;
    // 密码
    private String password;
    // 角色
    private String role;
    // token
    private String token;
}
