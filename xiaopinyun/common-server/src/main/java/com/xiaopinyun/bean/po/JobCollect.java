package com.xiaopinyun.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 职位收藏
 */
@Data
@TableName("job_collect")
public class JobCollect {
    // 自增id
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    // 学生id
    private Integer aid;
    // 招聘信息id
    private Integer rid;
    // 是否已取消收藏  0已收藏、1取消收藏
    @TableLogic
    private Integer dr;
    // 时间戳
    private String ts;
}
