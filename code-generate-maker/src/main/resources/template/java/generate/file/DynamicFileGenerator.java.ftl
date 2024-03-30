package ${basePackage}.generate.file;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * 动态文件生成器
 *
 * @author ${author}
 */
public class DynamicFileGenerator {
    /**
     * 生成文件
     *
     * @param inputPath  模板文件存放路径
     * @param outputPath 输出路径
     * @param model      数据模型
     */
    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        // 创建Configuration对象，参数版本号与pom文件中保持一致
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        // 指定模板文件存放路径
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);
        // 指定模板文件字符集
        configuration.setDefaultEncoding("utf-8");
        configuration.setNumberFormat("0.######");
        // 创建模板对象，加载指定模板
        String fileName = new File(inputPath).getName();
        Template template = configuration.getTemplate(fileName);
        // 如果输出路径不存在，则先新建。touch创建文件，mkdir创建目录
        if (!FileUtil.exist(outputPath)) {
            FileUtil.touch(outputPath);
        }
        // 生成数据渲染后的文件
        Writer out = new OutputStreamWriter(new FileOutputStream(outputPath), "utf-8");
        template.process(model, out);
        // 关闭流
        out.close();
    }
}
