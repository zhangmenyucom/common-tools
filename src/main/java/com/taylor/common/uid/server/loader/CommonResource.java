package com.taylor.common.uid.server.loader;

import lombok.Data;

/**
 * Created by lenovo on 2017/5/8.
 */
@Data
public class CommonResource {
    private String host;

    private int port;

    private String instance;

    private String password;
}
