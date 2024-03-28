package com.simple.maker.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * 编程式子命令绑定样例
 * 声明式绑定样例：@Command(@subCommand={Login.class})
 *
 * @author Simple
 */
@Command(name = "main", mixinStandardHelpOptions = true)
public class SubCommandExample implements Runnable {
    @Override
    public void run() {
        System.out.println("执行主命令");
    }

    @Command(name = "add", description = "增加", mixinStandardHelpOptions = true)
    static class AddCommand implements Runnable {
        @Override
        public void run() {
            System.out.println("执行增加命令");
        }
    }

    @Command(name = "del", description = "删除", mixinStandardHelpOptions = true)
    static class DelCommand implements Runnable {
        @Override
        public void run() {
            System.out.println("执行删除命令");
        }
    }

    @Command(name = "query", description = "查询", mixinStandardHelpOptions = true)
    static class QueryCommand implements Runnable {
        @Override
        public void run() {
            System.out.println("执行查询命令");
        }
    }

    public static void main(String[] args) {
        // 执行主命令
        String[] myArgs = new String[]{};
        // 查看主命令帮助手册
//        String[] myArgs = new String[]{"--help"};
        // 执行增加命令
//        String[] myArgs = new String[]{"add"};
        // 查看查询命令的帮助手册
//        String[] myArgs = new String[]{"query", "--help"};
        // 执行不存在命令，报错
//        String[] myArgs = new String[]{"de"};
        int exitCode = new CommandLine(new SubCommandExample())
                .addSubcommand(new AddCommand())
                .addSubcommand(new DelCommand())
                .addSubcommand(new QueryCommand())
                .execute(myArgs);
        System.exit(exitCode);
    }
}
