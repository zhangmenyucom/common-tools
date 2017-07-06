package com.taylor.common.uid.server.dao;

/**
 * @author Michael.Wang
 * @date 2017/4/28
 */

import com.taylor.common.uid.server.domain.BizIdEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DAO for biz_id
 */
@Repository
public interface BizIdDAO {

    int save(@Param("entity") BizIdEntity entity);

    int updateByPrimaryKeySelective(@Param("entity") BizIdEntity entity);

    BizIdEntity queryByBizType(String bizType);

    List<BizIdEntity> queryByModule(String module);

    List<BizIdEntity> queryAll();

    void initAll(String module);
}

