package com.simple.maker.meta.enums;

/**
 * 生成文件类型枚举类
 *
 * @author Simple
 */
public enum FileGenerateTypeEnum {

    DYNAMIC("动态", "dynamic"),

    STATIC("静态", "static");

    FileGenerateTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    private final String text;
    private final String value;

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
