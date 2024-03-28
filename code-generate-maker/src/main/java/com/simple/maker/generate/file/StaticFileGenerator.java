package com.simple.maker.generate.file;

import cn.hutool.core.io.FileUtil;

/**
 * 静态文件生成器（这里的静态指的不是前端层面的）
 *
 * @author Simple
 */
public class StaticFileGenerator {
    /**
     * 拷贝文件（使用hutool工具实现，将输入目录完整拷贝到输出路径）
     *
     * @param inputPath  输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }
}
