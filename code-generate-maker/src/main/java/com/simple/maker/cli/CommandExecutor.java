package com.simple.maker.cli;

import com.simple.maker.cli.command.ConfigCommand;
import com.simple.maker.cli.command.GenerateCommand;
import com.simple.maker.cli.command.ListCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "", mixinStandardHelpOptions = true)
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
        return commandLine.execute(args);
    }
}
