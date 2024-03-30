package ${basePackage}.generate.file;

import ${basePackage}.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 动态文件和静态文件组合生成
 *
 * @author ${author}
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
        // 从元信息文件获取输入、输出根路径
        String inputRootPath = "${fileConfig.inputRootPath}";
        String outputRootPath = "${fileConfig.outputRootPath}";

        String inputPath ;
        String outputPath;
<#list fileConfig.files as fileInfo>
        inputPath = new File(inputRootPath,"${fileInfo.inputPath}").getAbsolutePath();
        outputPath = new File(outputRootPath,"${fileInfo.outputPath}").getAbsolutePath();
    <#if fileInfo.generateType == "dynamic">
        DynamicFileGenerator.doGenerate(inputPath, outputPath, model);
    <#else>
        StaticFileGenerator.copyFilesByHutool(inputPath, outputPath);
    </#if>
</#list>
    }
}
