package com.xiaopinyun.bean.vo;

import com.xiaopinyun.bean.po.JobCollect;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 岗位收藏
 */
@Data
@NoArgsConstructor
public class JobCollectVO {
    private String id;
    // 学生信息主键
    private String pkApplicant;
    // 招聘信息主键
    private String pkRecruit;

    public JobCollectVO(JobCollect jobCollect){
        this.id = jobCollect.getId().toString();
        this.pkApplicant = jobCollect.getPkApplicant().toString();
        this.pkRecruit = jobCollect.getPkRecruit().toString();
    }
}
