package com.taylor.common.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xpl on 2017-04-29 11:53.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreOperationMessage implements Serializable {

    private Long merchantId;
    private List<Long> storeIds;
    private Integer type;//@see StoreOperationEnum
}
