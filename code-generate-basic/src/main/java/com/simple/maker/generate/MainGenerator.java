package com.simple.maker.generate;

import com.simple.maker.model.NumberSumTemplateConfig;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 动态文件和静态文件组合生成
 *
 * @author Simple
 */
public class MainGenerator {

    /**
     * 执行模板代码生成
     *
     * @param model 数据模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate(Object model) throws TemplateException, IOException {
        // 1.静态文件生成，获取项目根路径(这种方式获取到的根目录是当前idea打开工程的目录)
        String projectPath = System.getProperty("user.dir");
        // 输入路径
        String inputPath = projectPath + File.separator + "code-generate-samples" + File.separator + "acm-template";
        // 输出路径
        String outputPath = projectPath;
        // 执行复制，生成静态文件
        StaticGenerator.copyFilesByRecursive(inputPath, outputPath);

        // 2.动态文件生成,指定模板文件存放路径
        String dynamicInputPath = projectPath + File.separator + "code-generate-basic" + File.separator + "src\\main\\resources\\templates\\NumberSumTemplate.java.ftl";
        String dynamicOutputPath = projectPath + File.separator + "acm-template\\src\\com\\simple\\acm\\NumberSumTemplate.java";

        DynamicGenerator.doGenerate(dynamicInputPath, dynamicOutputPath, model);
    }

    public static void main(String[] args) throws IOException, TemplateException {
        // 创建数据模型
        NumberSumTemplateConfig numberSumConf = new NumberSumTemplateConfig();
        numberSumConf.setAuthor("simple");
        numberSumConf.setOutputText("求和结果为");
        numberSumConf.setLoop(true);
        doGenerate(numberSumConf);
    }
}
