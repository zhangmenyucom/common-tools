
package com.taylor.common.buid.buffer;

import java.util.List;

public interface BufferedUidProvider {

    /**
     * Provides UID in one second
     * 
     * @param momentInSecond
     * @return
     */
    List<Long> provide(long momentInSecond);
}
