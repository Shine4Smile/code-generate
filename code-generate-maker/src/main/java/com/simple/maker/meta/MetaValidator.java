package com.simple.maker.meta;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.simple.maker.meta.enums.FileGenerateTypeEnum;
import com.simple.maker.meta.enums.FileTypeEnum;
import com.simple.maker.meta.enums.ModelTypeEnum;

import java.nio.file.Paths;
import java.util.List;

/**
 * 元信息校验
 *
 * @author Simple
 */
public class MetaValidator {
    public static void doValidAndFill(Meta meta) {
        // 基础信息校验及默认值
        validAndFillMetaRoot(meta);

        // fileConfig信息校验及默认值
        validAndFillFileConfig(meta);

        // modelConfig信息校验
        validAndFillModelConfig(meta);
    }

    private static void validAndFillModelConfig(Meta meta) {
        Meta.ModelConfigDTO modelConfig = meta.getModelConfig();
        if (modelConfig == null) {
            return;
        }
        List<Meta.ModelConfigDTO.ModelInfo> models = modelConfig.getModels();
        if (CollUtil.isEmpty(models)) {
            return;
        }
        for (Meta.ModelConfigDTO.ModelInfo modelInfo : models) {
            String fieldName = modelInfo.getFieldName();
            if (StrUtil.isBlank(fieldName)) {
                throw new MetaException("必填项fieldName未填写");
            }
            String modelInfoType = StrUtil.emptyToDefault(modelInfo.getType(), ModelTypeEnum.STRING.getValue());
            modelInfo.setType(modelInfoType);
        }
    }

    private static void validAndFillFileConfig(Meta meta) {
        Meta.FileConfigDTO fileConfig = meta.getFileConfig();
        if (fileConfig == null) {
            return;
        }
        // sourceRootPath必填
        String sourceRootPath = fileConfig.getSourceRootPath();
        if (StrUtil.isBlank(sourceRootPath)) {
            throw new MetaException("sourceRootPath必填项未填写");
        }
        String inputRootPath = StrUtil.emptyToDefault(fileConfig.getInputRootPath(), ".source" + System.getProperty("file.separator") + FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString());
        fileConfig.setInputRootPath(inputRootPath);
        String outputRootPath = StrUtil.emptyToDefault(fileConfig.getOutputRootPath(), "generated");
        fileConfig.setOutputRootPath(outputRootPath);
        String fileConfigType = StrUtil.emptyToDefault(fileConfig.getType(), FileTypeEnum.DIR.getValue());
        fileConfig.setType(fileConfigType);
        List<Meta.FileConfigDTO.FileInfo> fileInfoList = fileConfig.getFiles();
        if (CollUtil.isEmpty(fileInfoList)) {
            return;
        }
        for (Meta.FileConfigDTO.FileInfo fileInfo : fileInfoList) {
            // inputPath必填
            String inputPath = fileInfo.getInputPath();
            if (StrUtil.isBlank(inputPath)) {
                throw new MetaException("inputPath必填项未填写");
            }
            String outputPath = StrUtil.emptyToDefault(fileInfo.getOutputPath(), inputPath);
            fileInfo.setOutputPath(outputPath);
            String type = fileInfo.getType();
            if (StrUtil.isEmpty(type)) {
                // 无文件后缀
                if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))) {
                    fileInfo.setType(FileTypeEnum.DIR.getValue());
                } else {
                    fileInfo.setType(FileTypeEnum.FILE.getValue());
                }
            }
            String generateType = fileInfo.getGenerateType();
            if (StrUtil.isEmpty(generateType)) {
                // 后缀为.ftl的文件为动态生成模板
                if (inputPath.endsWith(".ftl")) {
                    fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
                } else {
                    fileInfo.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
                }
            }
        }
    }

    private static void validAndFillMetaRoot(Meta meta) {
        String name = StrUtil.emptyToDefault(meta.getName(), "my-generator");
        String description = StrUtil.emptyToDefault(meta.getDescription(), "我的模板代码生成器");
        String basePackage = StrUtil.blankToDefault(meta.getBasePackage(), "com.simple");
        String version = StrUtil.emptyToDefault(meta.getVersion(), "1.0");
        String author = StrUtil.emptyToDefault(meta.getAuthor(), "simple");
        String createTime = StrUtil.emptyToDefault(meta.getCreateTime(), DateUtil.now());

        meta.setName(name);
        meta.setDescription(description);
        meta.setBasePackage(basePackage);
        meta.setVersion(version);
        meta.setAuthor(author);
        meta.setCreateTime(createTime);
    }
}
