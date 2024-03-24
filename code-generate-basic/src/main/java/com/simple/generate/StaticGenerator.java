package com.simple.generate;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * 静态文件生成器（这里的静态指的不是前端层面的）
 *
 * @author Simple
 */
public class StaticGenerator {
    public static void main(String[] args) throws IOException {
        // 获取项目根路径
        String projectPath = System.getProperty("user.dir");
        // 输入路径
        String inputPath = projectPath + File.separator + "code-generate-samples" + File.separator + "acm-template";
        // 输出路径
        String outputPath = projectPath;
        // 执行复制
//        copyFilesByHutool(inputPath, outputPath);
        copyFilesByRecursive(inputPath, outputPath);
    }

    /**
     * 拷贝文件（使用hutool工具实现，将输入目录完整拷贝到输出路径）
     *
     * @param inputPath  输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByHutool(String inputPath, String outputPath) {
        FileUtil.copy(inputPath, outputPath, false);
    }

    /**
     * 使用递归思想完成文件拷贝
     *
     * @param inputPath  输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByRecursive(String inputPath, String outputPath) {
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        if (FileUtil.equals(inputFile, outputFile)) {
            throw new IORuntimeException("源文件路径与目的路径相同，请重新确认");
        }
        // 输出路径如果不增加输入路径最后一级目录名称，那么输入路径下的所有文件会直接复制到输出路径，而忽略了该级目录
        outputFile = new File(outputPath, inputFile.getName());
        if (inputFile.isDirectory()) {
            if (outputFile.exists() && !outputFile.isDirectory()) {
                throw new IORuntimeException("源为目录，而目标为文件，请重新确认");
            }
            internalCopyDir(inputFile, outputFile);
        } else {
            internalCopyFile(inputFile, outputFile);
        }
    }


    /**
     * 输入路径是目录时处理方法
     *
     * @param inputFile  输入路径
     * @param outputFile 输出路径
     */
    private static void internalCopyDir(File inputFile, File outputFile) {
        if (!outputFile.exists()) {
            // 目标为不存在路径，创建为目录
            outputFile.mkdirs();
        }
        // 获取输入路径下所有文件
        String[] fileList = inputFile.list();
        if (ArrayUtil.isNotEmpty(fileList)) {
            File input;
            File output;
            for (String file : fileList) {
                input = new File(inputFile, file);
                output = new File(outputFile, file);
                if (input.isDirectory()) {
                    // 如果仍为目录则继续调用复制目录方法
                    internalCopyDir(input, output);
                } else {
                    // 若未文件则复制文件
                    internalCopyFile(input, output);
                }
            }
        }
    }

    /**
     * 输入为文件时处理方法
     *
     * @param inputFile  输入路径
     * @param outputFile 输出路径
     */
    private static void internalCopyFile(File inputFile, File outputFile) {
        if (outputFile.exists()) {
            if (outputFile.isDirectory()) {
                // 目标为目录，目录下创建同名文件
                outputFile = new File(outputFile, inputFile.getName());
            }
        } else {
            // 路径不存在则创建父目录
            FileUtil.mkParentDirs(outputFile);
        }
        try {
            Files.copy(inputFile.toPath(), outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.print("文件复制失败");
            throw new IORuntimeException(e);
        }
    }
}
