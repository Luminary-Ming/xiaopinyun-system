package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVO {
    // 主键
    private String id;
    // 学生信息主键
    private String pkApplicant;
    // HR信息主键
    private String pkHr;
    // 管理员信息主键
    private String pkAdmin;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 角色  0学生、1企业、2管理员
    private String role;

    public UserVO(User user) {
        this.id = user.getId().toString();
        if (user.getPkApplicant() != null) {
            this.pkApplicant = user.getPkApplicant().toString();
        }
        if (user.getPkHr() != null) {
            this.pkHr = user.getPkHr().toString();
        }
        if (user.getPkAdmin() != null) {
            this.pkAdmin = user.getPkAdmin().toString();
        }
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole().toString();
    }
}
