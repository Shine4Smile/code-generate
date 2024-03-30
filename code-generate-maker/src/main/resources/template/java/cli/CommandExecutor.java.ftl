package ${basePackage}.cli;

import cn.hutool.core.util.ArrayUtil;
import ${basePackage}.cli.command.ConfigCommand;
import ${basePackage}.cli.command.GenerateCommand;
import ${basePackage}.cli.command.ListCommand;
import ${basePackage}.cli.command.OptionAnnotationProcessor;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.util.Arrays;
import java.util.List;


@Command(name = "${name}", mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable {
    private CommandLine commandLine;

    /**
     * 实例化初始模块，在构造函数前执行
     * 为本命令行命令绑定子命令
     */ {
        commandLine = new CommandLine(this)
                .addSubcommand(new GenerateCommand())
                .addSubcommand(new ListCommand())
                .addSubcommand(new ConfigCommand());
    }

    @Override
    public void run() {
        System.out.println("请输入具体命令，或输入 --help 查看命令提示");
    }

    public Integer doExecute(String[] args) {
        List<String> argsList = Arrays.asList(args);
        // 当参数中包含generate命令时，将该命令中的用户未输入的必填选项以交互形式提示用户输入
        if (argsList.contains("generate")) {
            List<String> fields = OptionAnnotationProcessor.processFields(GenerateCommand.class);
            fields.removeAll(argsList);
            String[] fieldsArray = fields.toArray(new String[0]);
            return commandLine.execute(ArrayUtil.append(args, fieldsArray));
        }
        return commandLine.execute(args);
    }
}