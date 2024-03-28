package com.simple.maker.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine.Command;

import java.io.File;

/**
 * 读取模板目录文件列表命令
 *
 * @author Simple
 */
@Command(name = "list", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {
    @Override
    public void run() {
        // 获取项目根目录
        String projectPath = System.getProperty("user.dir");
        // 找到模板代码目录
        String inputPath = new File(projectPath, "code-generate-samples/acm-template").getAbsolutePath();
        // 遍历模板中的文件
        for (File file : FileUtil.loopFiles(inputPath)) {
            System.out.println(file.getName());
        }

    }
}
