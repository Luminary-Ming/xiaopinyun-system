package com.xiaopinyun.config;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 数据新增、更新自动填充配置
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    private Snowflake snowflake;

    @Override
    public void insertFill(MetaObject metaObject) {
        strictInsertFill(metaObject, "id", Long.class, snowflake.nextId());
        strictInsertFill(metaObject, "dr", Integer.class, 0);
        strictInsertFill(metaObject, "ts", LocalDateTime.class, LocalDateTime.now());
        strictInsertFill(metaObject, "checkStatus", Integer.class, 2);  // 审核中
        strictInsertFill(metaObject, "recommendStatus", Integer.class, 0);  // 默认不推荐
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictInsertFill(metaObject, "ts", LocalDateTime.class, LocalDateTime.now());
        strictInsertFill(metaObject, "checkStatus", Integer.class, 2);  // 审核中
    }
}
