package com.taylor.client.exception;

/**
 * @author Michael.Wang
 * @date 2017/4/25
 */
public class UidException extends RuntimeException {
    public UidException() {
        super();
    }

    public UidException(String msg) {
        super(msg);
    }

    public UidException(String message, Throwable cause) {
        super(message, cause);
    }
}
