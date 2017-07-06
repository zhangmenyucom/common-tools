package com.taylor.common.lock;

public interface Callback {

    Object onGetLock() throws InterruptedException;

    Object onTimeout() throws InterruptedException;
}
