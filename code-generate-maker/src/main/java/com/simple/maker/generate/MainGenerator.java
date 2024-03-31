package com.simple.maker.generate;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import com.simple.maker.generate.file.DynamicFileGenerator;
import com.simple.maker.meta.Meta;
import com.simple.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMetaObject();
        System.out.println(meta);
        // 获取项目根路径
        String projectPath = System.getProperty("user.dir");
        // 这里定义输出路径在code-generate-maker模块的generated目录下
        String outputPath = projectPath + File.separator + "code-generate-maker" + File.separator + "generator" + File.separator + meta.getName();
        if (!FileUtil.exist(outputPath)) {
            // 若目录不存在，则先生成
            FileUtil.mkdir(outputPath);
        }
        // 从原始模板文件路径复制到生成的代码包中
        String sourceRootPath = meta.getFileConfig().getSourceRootPath();
        String sourceCopyPath = outputPath + File.separator + ".source";
        FileUtil.copy(sourceRootPath, sourceCopyPath, false);

        //读取resource目录
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();
        // 获取待生成文件的Java包位置
        String outputBasePackage = meta.getBasePackage();
        // 将包名中的点改为路径分隔符
        String outputBasePackagePath = String.join(File.separator, outputBasePackage.split("\\."));
        // 拼接完整Java包输出路径
        String outputBaseJavaPath = outputPath + File.separator + "src/main/java/" + outputBasePackagePath;

        String inputFilePath;
        String outputFilePath;
        // 数据模型文件路径
        inputFilePath = inputResourcePath + File.separator + "template/java/model/DataModel.java.ftl";
        outputFilePath = outputBaseJavaPath + "/model/DataModel.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // com/simple/maker/cli/command/ConfigCommand.java
        inputFilePath = inputResourcePath + File.separator + "template/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath = outputBaseJavaPath + "/cli/command/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // com/simple/maker/cli/command/GenerateCommand.java
        inputFilePath = inputResourcePath + File.separator + "template/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath = outputBaseJavaPath + "/cli/command/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // com/simple/maker/cli/command/ListCommand.java
        inputFilePath = inputResourcePath + File.separator + "template/java/cli/command/ListCommand.java.ftl";
        outputFilePath = outputBaseJavaPath + "/cli/command/ListCommand.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // com/simple/maker/cli/command/OptionAnnotationProcessor.java
        inputFilePath = inputResourcePath + File.separator + "template/java/cli/command/OptionAnnotationProcessor.java.ftl";
        outputFilePath = outputBaseJavaPath + "/cli/command/OptionAnnotationProcessor.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // com/simple/maker/cli/CommandExecutor.java
        inputFilePath = inputResourcePath + File.separator + "template/java/cli/CommandExecutor.java.ftl";
        outputFilePath = outputBaseJavaPath + "/cli/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // com/simple/maker/Main.java
        inputFilePath = inputResourcePath + File.separator + "template/java/Main.java.ftl";
        outputFilePath = outputBaseJavaPath + "/Main.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // com/simple/maker/generate/file/DynamicFileGenerator.java
        inputFilePath = inputResourcePath + File.separator + "template/java/generate/file/DynamicFileGenerator.java.ftl";
        outputFilePath = outputBaseJavaPath + "/generate/file/DynamicFileGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // com/simple/maker/generate/file/FileGenerator.java
        inputFilePath = inputResourcePath + File.separator + "template/java/generate/file/FileGenerator.java.ftl";
        outputFilePath = outputBaseJavaPath + "/generate/file/FileGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // com/simple/maker/generate/file/StaticFileGenerator.java
        inputFilePath = inputResourcePath + File.separator + "template/java/generate/file/StaticFileGenerator.java.ftl";
        outputFilePath = outputBaseJavaPath + "/generate/file/StaticFileGenerator.java";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // pom.xml
        inputFilePath = inputResourcePath + File.separator + "template/pom.xml.ftl";
        outputFilePath = outputPath + "/pom.xml";
        DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

        // 构建jar包
        JarGenerator.doGenerate(outputPath);

        // 生成jar包执行脚本
        String sellOutputPath = outputPath + File.separator + "script";
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        ScriptGenerator.doGenerate(sellOutputPath, jarPath);
    }
}
