package com.simple.model;

import lombok.Data;

/**
 * 多数求和模板配置
 * 目的修改原来的多数求和代码模板实现下列三点：
 * 1.插入作者信息
 * 2.修改程序输出提示
 * 3.控制程序是否循环读取
 *
 * @author Simple
 */
@Data
public class NumberSumTemplateConfig {
    /**
     * 作者
     */
    private String author = "simple";
    /**
     * 输出提示
     */
    private String outputText = "输出结果";
    /**
     * 是否循环读取
     */
    private Boolean loop;
}
