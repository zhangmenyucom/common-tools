package com.taylor.number.rule;

import com.taylor.client.state.EnumCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Michael.Wang
 * @date 2017/4/26
 */
@Getter
@RequiredArgsConstructor
public enum BillRuleTypeEnum implements EnumCode<String> {

    CONSTANT("FIXED", "固定串"), DATE("DATE", "时间"), SEQUENCE("SEQ", "流水号"), RANDOM("RANDOM", "随机数");

    private final String key;
    private final String description;

}
