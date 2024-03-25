package com.simple.cli.example;

import cn.hutool.core.util.ArrayUtil;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * 如果要实现交互式命令行，需要实现Callable接口
 */
class Login implements Callable<Integer> {
    @Option(names = {"-u", "--user"}, description = "User name")
    String user;

    /**
     * interactive为true时表示该参数通过交互式输入
     * echo属性可以控制jar包运行时该参数输入是否可见
     * prompt属性表示该参数输入时的提示语
     * arity表示接受参数个数（建议交互式参数都添加该属性），若未添加该参数，在打开交互式输入的情况下,无法直接从命令中读取参数，如：-u zs -p 123直接执行该命令-p获取不到参数123
     */
    @Option(names = {"-p", "--password"}, description = "Passphrase", arity = "0..1", interactive = true, prompt = "请输入密码：")
    String password;
    @Option(names = {"-cp", "--checkPassword"}, description = "Passphrase", arity = "0..1", interactive = true, prompt = "请确认密码：")
    String checkPassword;

    @Override
    public Integer call() {
        System.out.println("你好" + user + "，你的密码是：" + password + "确认密码是：" + checkPassword);
        return 0;
    }

    public static void main(String[] args) {
        // 模拟传参
        String[] myArgs = new String[]{"-u", "ZhangSan"};
        // 获取所有选项
        List<String> fields = OptionAnnotationProcessor.processFields(Login.class);
        // 移除用户已输入的选项
        fields.removeAll(Arrays.asList(myArgs));
        String[] fieldsArray = fields.toArray(new String[0]);
        // 将其他用户未输入的选项拼接到用户输入的命令后面
        String[] commandArray = ArrayUtil.append(myArgs, fieldsArray);
        new CommandLine(new Login()).execute(commandArray);
    }
}