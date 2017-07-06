package com.taylor.number.engine;

import com.taylor.client.exception.UidException;
import com.taylor.common.utils.DateUtil;
import com.taylor.common.utils.RandomUtil;
import com.taylor.common.utils.StringUtil;
import com.taylor.number.rule.BillNumRules;
import com.taylor.number.rule.BillRuleTypeEnum;
import com.taylor.number.rule.StaticBillNumRuleLoader;
import com.taylor.number.sequence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Michael.Wang
 * @date 2017/4/26
 */
public class BillNumGeneratorImpl implements BillNumGenerator {

    @Getter
    @Setter
    private SequenceGenerator sequenceGenerator;

    private static BillNumRules billNumRules;

    @Override
    public String generate(String bizType) {
        if (StringUtil.isEmpty(bizType)) {
            throw new IllegalArgumentException("bizType is null");
        }
        List<BillNumRules.BillNumRule> bills = getBillNumRules().getBills();
        for (BillNumRules.BillNumRule bizNumRule : bills) {
            if (!bizType.equalsIgnoreCase(bizNumRule.getBill())) {
                continue;
            }
            List<BillNumRules.BillNumRuleElement> rules = bizNumRule.getRules();
            StringBuffer buffer = new StringBuffer();
            for (BillNumRules.BillNumRuleElement element : rules) {
                String type = element.getType();
                if (StringUtil.isEmpty(type)) {
                    throw new IllegalArgumentException("type is null");
                }
                type = type.toUpperCase();
                if (BillRuleTypeEnum.CONSTANT.getKey().equals(type)) {
                    buffer.append(element.getValue());//固定值
                } else if (BillRuleTypeEnum.DATE.getKey().equals(type)) {
                    buffer.append(DateUtil.getCurrentDate(element.getLength()));//时间
                } else if (BillRuleTypeEnum.SEQUENCE.getKey().equals(type)) {
                    long next = sequenceGenerator.generateNext(bizType);
                    buffer.append(StringUtil.leftPad(next,element.getLength(),'0'));
                } else if (BillRuleTypeEnum.RANDOM.getKey().equals(type)) {
                    buffer.append(RandomUtil.number(element.getLength()));
                }
            }
            return buffer.toString();
        }
        throw new UidException("Please add rule into BillNumRuleSetting before generate number.");
    }

    public static BillNumRules getBillNumRules() {
        if (billNumRules == null) {
            synchronized (BillNumRules.class) {
                if (billNumRules == null) {
                    billNumRules = new StaticBillNumRuleLoader().load();
                }
            }
        }
        return billNumRules;
    }
}
