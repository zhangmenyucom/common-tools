package com.taylor.client;

import com.taylor.client.state.EnumCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Michael.Wang
 * @date 2017/4/26
 */
@Getter
@RequiredArgsConstructor
public enum BillTypeEnum implements EnumCode<String> {

    ORDER("store_order", "订单"), PAY("PAY_ORDER_RECORD", "支付单");

    private final String key;
    private final String description;

}
