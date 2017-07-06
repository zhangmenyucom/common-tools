package com.taylor.common.message.constant;

/**
 * Created by xpl on 2017-05-02 11:55.
 */
public enum StoreOperationEnum {
    CREATE(1, "创建门店"),
    DELETE(2, "删除门店"),
    ;
    private Integer code;
    private String message;

    StoreOperationEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
