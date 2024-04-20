package com.simple.maker.generate.main;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * 执行生成器主类
 *
 * @author Simple
 */
public class MainGenerator extends GenerateTemplate {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        MainGenerator mainGenerator = new MainGenerator();
        // 如若需要跳过某个流程，可以在字类重写父类方法
        mainGenerator.doGenerate();
    }
}
