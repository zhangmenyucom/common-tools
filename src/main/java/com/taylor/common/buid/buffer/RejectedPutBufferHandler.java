
package com.taylor.common.buid.buffer;

public interface RejectedPutBufferHandler {

    /**
     * Reject put buffer request
     * 
     * @param ringBuffer
     * @param uid
     */
    void rejectPutBuffer(RingBuffer ringBuffer, long uid);
}
