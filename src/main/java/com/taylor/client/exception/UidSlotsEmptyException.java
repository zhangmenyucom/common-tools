package com.taylor.client.exception;

/**
 * @author Michael.Wang
 * @date 2017/4/25
 */
public class UidSlotsEmptyException extends UidException {
    public UidSlotsEmptyException() {
        super();
    }

    public UidSlotsEmptyException(String msg) {
        super(msg);
    }

    public UidSlotsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
