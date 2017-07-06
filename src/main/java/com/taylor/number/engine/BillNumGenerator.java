package com.taylor.number.engine;


import com.taylor.number.sequence.SequenceGenerator;

/**
 * @author Michael.Wang
 * @date 2017/4/26
 */
public interface BillNumGenerator {
    String generate(String bizType);

    void setSequenceGenerator(SequenceGenerator sequenceGenerator);
}
