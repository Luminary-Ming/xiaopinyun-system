package com.xiaopinyun.bean.vo;

import lombok.Data;

@Data
public class UserVO {
    // id
    private Integer id;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 角色  0学生、1企业、2管理员
    private Integer role;
}
