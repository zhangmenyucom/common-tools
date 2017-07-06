package com.taylor.client.state;

import com.taylor.client.entity.NumStatus;
import com.taylor.client.exception.UidException;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

public class StateTransfer {
    private static final Map states = new HashedMap();

    static {
        //初始化状态迁移路径
        states.put(UidStateEnum.EMPTY, UidStateEnum.FILLING);
        states.put(UidStateEnum.FILLING, UidStateEnum.FILLED);
        states.put(UidStateEnum.FILLED, UidStateEnum.TAKING);
        states.put(UidStateEnum.TAKING, UidStateEnum.EMPTY);
    }

    public boolean canTransfer(NumStatus numStatus, UidStateEnum from, UidStateEnum to) {
        throw new UidException("Not Implemented");
    }
}
