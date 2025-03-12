package com.xiaopinyun.bean.po;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户登录
 */
@Data
@TableName("user")
public class User {
    // id
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 学生id
    private Integer aid;
    // HR id
    private Integer hid;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 角色  0学生、1企业、2管理员
    private Integer role;
    // 是否已删除账户  0正常使用、1已删除账户
    @TableLogic
    private Integer dr;
    // 时间戳
    private String ts;
}
