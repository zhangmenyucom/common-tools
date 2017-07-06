package com.taylor.number;

import com.taylor.number.engine.BillNumGenerator;
import com.taylor.number.engine.BillNumGeneratorImpl;
import com.taylor.number.sequence.UidSequenceGenerator;

/**
 * 单据编号生成接口
 *
 * @author Michael.Wang
 * @date 2017/4/26
 */
public class BillNumGeneratorFacade {
    private static BillNumGenerator billNumGenerator;

    static {
        billNumGenerator = new BillNumGeneratorImpl();
        billNumGenerator.setSequenceGenerator(new UidSequenceGenerator());
    }

    /**
     * 根据业务类型获取编号
     *
     * @param bizType 业务类型，即表名
     * @return 单据编号
     */
    public static String generateNum(String bizType) {
        return billNumGenerator.generate(bizType);
    }
}
