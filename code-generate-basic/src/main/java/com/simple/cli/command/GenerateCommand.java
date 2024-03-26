package com.simple.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.simple.generate.MainGenerator;
import com.simple.model.NumberSumTemplateConfig;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

/**
 * 模板代码生成命令
 *
 * @author Simple
 */
@Data
@Command(name = "generate", mixinStandardHelpOptions = true)
public class GenerateCommand implements Callable<Integer> {

    @Option(names = {"-u", "--user"}, description = "作者", arity = "0..1", interactive = true, prompt = "请输入作者名称：", required = true, echo = true)
    private String author = "simple";
    @Option(names = {"-o", "--output"}, description = "结果输出描述", arity = "0..1", interactive = true, prompt = "例如，输出结果为：", required = true, echo = true)
    private String outputText = "输出结果为";
    @Option(names = {"-l", "--loop"}, description = "是否循环", arity = "0..1", interactive = true, prompt = "程序是否开启循环：", required = true, echo = true)
    private boolean loop;

    @Override
    public Integer call() throws Exception {
        NumberSumTemplateConfig numberSumTemplateConfig = new NumberSumTemplateConfig();
        BeanUtil.copyProperties(this, numberSumTemplateConfig);
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate(numberSumTemplateConfig);
        return 0;
    }
}
