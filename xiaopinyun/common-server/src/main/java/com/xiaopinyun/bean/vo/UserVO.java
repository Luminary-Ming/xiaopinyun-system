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
    private String pk_applicant;
    // HR信息主键
    private String pk_hr;
    // 管理员信息主键
    private String pk_admin;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 角色  0学生、1企业、2管理员
    private String role;

    public UserVO(User user) {
        this.id = user.getId().toString();
        if (user.getPk_applicant() != null) {
            this.pk_applicant = user.getPk_applicant().toString();
        }
        if (user.getPk_hr() != null) {
            this.pk_hr = user.getPk_hr().toString();
        }
        if (user.getPk_admin() != null) {
            this.pk_admin = user.getPk_admin().toString();
        }
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole().toString();
    }
}
