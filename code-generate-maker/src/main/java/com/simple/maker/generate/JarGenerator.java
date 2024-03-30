package com.simple.maker.generate;

import java.io.*;

/**
 * 将生成的代码模板项目进行打包，以便运行jar包进行模板代码生成
 *
 * @author Simple
 */
public class JarGenerator {
    public static void doGenerate(String projectPath) throws IOException, InterruptedException {
        // 调用Process类执行maven命令
        String winMavenCommand = "mvn.cmd clean package -DskipTests=true";
        String otherMavenCommand = "mvn clean package -DskipTests=true";
        String mavenCommand = winMavenCommand;
        // mavenCommand用空格拆分，否则process会将所有命令整体当作一个字符串
        ProcessBuilder processBuilder = new ProcessBuilder(mavenCommand.split(" "));
        // 设置命令执行目录
        processBuilder.directory(new File(projectPath));
        // 构建一个process对象
        Process process = processBuilder.start();
        // 读取命令输出
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "GB2312"));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        // process对象执行命令
        int exitCode = process.waitFor();
        System.out.println("maven命令执行结束，退出码：" + exitCode);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerate("D:\\Project\\GitProject\\code-generate\\code-generate-basic");
    }
}
