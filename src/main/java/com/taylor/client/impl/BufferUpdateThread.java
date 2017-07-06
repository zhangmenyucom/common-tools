package com.taylor.client.impl;

import com.taylor.client.entity.NumStatus;
import com.taylor.client.entity.UidHolderStatus;
import com.taylor.client.loader.UidConfig;
import com.taylor.client.loader.UidLoader;
import com.taylor.client.state.UidStateEnum;
import com.taylor.common.utils.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Michael.Wang
 * @date 2017/5/2
 */
@Slf4j
public class BufferUpdateThread implements Runnable {
    private static Thread thread = new Thread(new BufferUpdateThread());

    private static volatile boolean stopFlag = Boolean.FALSE;

    static {
        thread.setName("COMMON-UID-BufferUpdateThread");
        thread.setPriority(Thread.MAX_PRIORITY); //设置最高优先级
    }

    public static Thread getThread() {
        return thread;
    }

    @Override
    public void run() {
        log.info("buffer update thread [start]");
        while (!stopFlag) {
            ThreadUtil.sleepSeconds(UidConfig.getInstance().getScanIntervalSeconds());
            updateMaxId();
        }
    }

    public static void updateMaxId() {
        Map<String, UidHolderStatus> currentIds = NumHolder.getCurrentIds();
        Set<Map.Entry<String, UidHolderStatus>> entries = currentIds.entrySet();
        for (Map.Entry<String, UidHolderStatus> entry : entries) {
            UidHolderStatus uidHolderStatus = entry.getValue();
            fill(entry, uidHolderStatus.getWaitingSlot());
            fill(entry, uidHolderStatus.getUsingSlot());
        }
    }

    private static void fill(Map.Entry<String, UidHolderStatus> entry, NumStatus slot) {
        if (slot.isEmpty()) {
            slot.setState(UidStateEnum.FILLING.getKey()); //修改状态
            long nextMaxId = UidLoader.fetchNextMaxId(entry.getKey());
            log.info("Slot is empty. Fetch next max id of bizType:" + entry.getKey() + ":" + nextMaxId);
            //设置slots
            slot.setMax(nextMaxId);
            slot.setCurrentNum(new AtomicLong(nextMaxId - slot.getStep()));
            slot.setState(UidStateEnum.FILLED.getKey());//再次修改状态
        }
    }

    public static void stop() {
        stopFlag = true;
        log.info(" update thread  [stopped]");
    }

}
