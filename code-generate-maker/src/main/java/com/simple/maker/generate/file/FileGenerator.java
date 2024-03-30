package com.simple.maker.generate.file;

import com.simple.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 动态文件和静态文件组合生成
 *
 * @author Simple
 */
public class FileGenerator {

    /**
     * 执行模板代码生成
     *
     * @param model 数据模型
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerate(Object model) throws TemplateException, IOException {
        // 定义输入、输出根路径
        String inputRootPath = "D:\\Project\\GitProject\\code-generate\\code-generate-samples\\acm-template";
        String outputRootPath = "D:\\Project\\GitProject\\code-generate";
        System.out.println(inputRootPath);

        String inputPath;
        String outputPath;
        inputPath = new File(inputRootPath, "src/com/simple/acm/NumberSumTemplate.java.ftl").getAbsolutePath();
        outputPath = new File(outputRootPath, "src/com/simple/acm/NumberSumTemplate.java").getAbsolutePath();
//        DynamicFileGenerator.doGenerate(inputPath, outputPath, model);

        inputPath = new File(inputRootPath, "_gitignore").getAbsolutePath();
        outputPath = new File(outputRootPath, "_gitignore").getAbsolutePath();
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
        inputPath = new File(inputRootPath, "README.md").getAbsolutePath();
        outputPath = new File(outputRootPath, "README.md").getAbsolutePath();
//        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
    }

    public static void main(String[] args) throws IOException, TemplateException {
        // 创建数据模型
        DataModel numberSumConf = new DataModel();
        numberSumConf.setAuthor("simple");
        numberSumConf.setOutputText("求和结果为");
        numberSumConf.setLoop(true);
        doGenerate(numberSumConf);
    }
}
