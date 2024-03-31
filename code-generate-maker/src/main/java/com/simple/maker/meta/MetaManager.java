package com.simple.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

/**
 * 使用双检锁单例模式获取meta对象
 *
 * @author Simple
 */
public class MetaManager {
    private static volatile Meta meta;

    public static Meta getMetaObject() {
        // 获取锁前进行判空，可以避免meta不为空时仍然去获取锁
        if (meta == null) {
            synchronized (MetaManager.class) {
                // 获取锁后判空，避免第一个进程已经创建meta后，后续进程重复创建
                if (meta == null) {
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    private static Meta initMeta() {
        String metaStr = ResourceUtil.readUtf8Str("meta.json");
        Meta bean = JSONUtil.toBean(metaStr, Meta.class);
        // 校验配置文件，处理默认值
        MetaValidator.doValidAndFill(bean);
        return bean;
    }
}
