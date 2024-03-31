package com.simple.maker.meta;

/**
 * 元信息异常
 *
 * @author Simple
 */
public class MetaException extends RuntimeException {

    public MetaException(String message) {
        super(message);
    }

    public MetaException(String message, Throwable cause) {
        super(message, cause);
    }
}
