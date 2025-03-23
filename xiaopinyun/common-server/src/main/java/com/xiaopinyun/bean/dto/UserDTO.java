package com.xiaopinyun.bean.dto;

import com.xiaopinyun.bean.vo.UserVO;
import lombok.Data;

@Data
public class UserDTO {
    private UserVO userVO;
    // 姓名
    private String name;
    // 头像
    private String profileImg;
    // token
    private String token;
}
