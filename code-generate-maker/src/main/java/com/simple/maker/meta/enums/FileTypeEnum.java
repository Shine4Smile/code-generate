package com.simple.maker.meta.enums;

/**
 * 文件类型枚举类
 *
 * @author Simple
 */
public enum FileTypeEnum {

    DIR("目录", "dir"),

    FILE("文件", "file");

    FileTypeEnum(String text, String value) {
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
