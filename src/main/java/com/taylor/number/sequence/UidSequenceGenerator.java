package com.taylor.number.sequence;


import com.taylor.client.UidGeneratorFacade;

/**
 * Created by lenovo on 2017/5/4.
 */
public class UidSequenceGenerator implements SequenceGenerator {

    @Override
    public long generateNext(String bizType) {
        return UidGeneratorFacade.getUNO(bizType);
    }
}
