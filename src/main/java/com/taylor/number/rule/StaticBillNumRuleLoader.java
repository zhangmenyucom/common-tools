package com.taylor.number.rule;


import com.taylor.common.utils.JsonUtil;
import com.taylor.number.business.BillNumRuleSetting;

/**
 * @author Michael.Wang
 * @date 2017/4/26
 */
public class StaticBillNumRuleLoader implements BillNumRuleLoader {

    public BillNumRules load() {
        String str = BillNumRuleSetting.RULES.replaceAll("'", "\"");
        BillNumRules billNumRules = JsonUtil.fromJson(str, BillNumRules.class);
        return billNumRules;
    }
}
