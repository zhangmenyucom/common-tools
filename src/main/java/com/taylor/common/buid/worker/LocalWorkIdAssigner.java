package com.taylor.common.buid.worker;


import com.taylor.common.buid.utils.NetUtils;

/**
 * @author Michael.Wang
 * @date 2017/4/27
 */
public class LocalWorkIdAssigner implements WorkerIdAssigner {
    @Override
    public long assignWorkerId() {
        return Long.parseLong(NetUtils.getLocalAddressParts());
    }
}
