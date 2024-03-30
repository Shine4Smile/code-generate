package ${basePackage}.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine.Command;

import java.io.File;

/**
 * 读取模板目录文件列表命令
 *
 * @author ${author}
 */
@Command(name = "list", mixinStandardHelpOptions = true)
public class ListCommand implements Runnable {
    @Override
    public void run() {
        // 获取输入目录
        String inputPath = "${fileConfig.inputRootPath}";
        // 遍历模板中的文件
        for (File file : FileUtil.loopFiles(inputPath)) {
            System.out.println(file);
        }

    }
}
