package com.taylor.number.business;

/**
 * @author Michael.Wang
 * @date 2017/4/26
 */
public class BillNumRuleSetting {
    public static final String RULES = "{'bills':" +
            "[{'bill':'STORE_ORDER'," +
            "'rules':[{'type':'FIXED','length':'2','value':'10'},{'type':'DATE','length':'6'},{'type':'SEQ','length':'9'},{'type':'RANDOM','length':'2'}]}," +
            "{'bill':'PAY_ORDER_RECORD'," +
            "'rules':[{'type':'FIXED','length':'2','value':'11'},{'type':'DATE','length':'6'},{'type':'SEQ','length':'9'},{'type':'RANDOM','length':'2'}]}" +
            "]}";

}
