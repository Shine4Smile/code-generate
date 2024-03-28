package com.simple.maker.cli.command;

import cn.hutool.core.util.ReflectUtil;
import com.simple.maker.model.NumberSumTemplateConfig;
import picocli.CommandLine;

import java.lang.reflect.Field;

/**
 * 获取数据模型参数信息
 *
 * @author Simple
 */
@CommandLine.Command(name = "conf", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {
    @Override
    public void run() {
        // 使用反射获取类的字段信息
//        Class<?> clazz = NumberSumTemplateConfig.class;
//        for (Field field : clazz.getDeclaredFields()) {
//            System.out.println("字段名：" + field.getName() + "，类型：" + field.getType());
//        }
        // 使用hutool工具类中的ReflectUtil工具类实现
        for (Field field : ReflectUtil.getFields(NumberSumTemplateConfig.class)) {
            System.out.println("字段名：" + field.getName() + "，类型：" + field.getType());
        }
    }
}
