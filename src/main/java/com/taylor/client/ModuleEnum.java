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
public enum ModuleEnum implements EnumCode<String> {

    ORDER_CENTER("order_center", "订单中心"), PAY_CENTER("pay_center", "支付中心"), CARD_CENTER("card_center","卡券中心");

    private final String key;
    private final String description;

}
