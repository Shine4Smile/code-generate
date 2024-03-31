package com.simple.maker.meta.enums;

/**
 * 模型类型枚举类
 *
 * @author Simple
 */
public enum ModelTypeEnum {

    STRING("字符串类型", "String"),

    BOOLEAN("布尔类型", "boolean");

    ModelTypeEnum(String text, String value) {
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
