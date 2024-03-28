package com.simple.maker.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;


/**
 * some exports omitted for the sake of brevity
 * mixinStandardHelpOptions为true表示开启帮助手册
 * 实现命令行需要实现Callable或者Runnable接口
 *
 * @author Simple
 */
@Command(name = "ASCIIArt", version = "ASCIIArt 1.0", mixinStandardHelpOptions = true)
public class ASCIIArt implements Runnable {

    /**
     * Option注解表示该变量的值可在命令行中通过选项赋值，比如：-s 20、--font-size 20
     */
    @Option(names = {"-s", "--font-size"}, description = "Font size")
    int fontSize = 19;

    /**
     * Parameters注解表示该变量的值可在命令行中通过参数赋值
     */
    @Parameters(paramLabel = "<word>", defaultValue = "Hello, picocli",
            description = "Words to be translated into ASCII art.")
    private final String[] words = {"Hello,", "picocli"};

    @Override
    public void run() {
        // 自定义业务逻辑
        System.out.println("fontSize：" + fontSize);
        System.out.println("words：" + String.join(",", words));
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ASCIIArt()).execute(args);
        System.exit(exitCode);
    }
}