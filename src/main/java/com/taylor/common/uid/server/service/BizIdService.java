package com.taylor.common.uid.server.service;

import com.taylor.common.uid.server.domain.BizIdEntity;

import java.util.List;

/**
 * @author Michael.Wang
 * @date 2017/4/28
 */
public interface BizIdService {

    /**
     * 获取下一号段最大id
     *
     * @param bizType
     * @return
     */
    public long fetchNextMaxId(String bizType);

    /**
     * 获取全部记录
     *
     * @return
     */
    public List<BizIdEntity> fetchAll(String module);

    /**
     * 获取全部记录
     *
     * @return
     */
    public List<BizIdEntity> queryAll();


}
