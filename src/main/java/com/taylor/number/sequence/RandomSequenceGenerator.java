package com.taylor.number.sequence;


import com.taylor.common.utils.RandomUtil;

/**
 * For mock only
 * @author Michael.Wang
 * @date 2017/4/26
 */
public class RandomSequenceGenerator implements SequenceGenerator {
    @Override
    public long generateNext(String bizType) {
        return RandomUtil.randomNumber(999999);
    }
}
