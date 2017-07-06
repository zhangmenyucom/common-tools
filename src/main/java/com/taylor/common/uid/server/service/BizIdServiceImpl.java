package com.taylor.common.uid.server.service;

import com.taylor.common.buid.exception.UidGenerateException;
import com.taylor.common.lock.redis.RedisReentrantLock;
import com.taylor.common.uid.server.dao.BizIdDAO;
import com.taylor.common.uid.server.domain.BizIdEntity;
import com.taylor.common.uid.server.loader.CommonResource;
import com.taylor.common.uid.server.loader.PropertiesLoader;
import com.taylor.common.utils.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Michael.Wang
 * @date 2017/4/28
 */
@Slf4j
@Service
public class BizIdServiceImpl implements BizIdService {
    public static final int LOCK_TIMEOUT = 1000;
    @Resource
    private BizIdDAO bizIdDAO;

    @Transactional
    public synchronized long fetchNextMaxId(String bizType) {
        BizIdEntity entity = bizIdDAO.queryByBizType(bizType);
        if (entity == null) {
            throw new UidGenerateException("Please add uid init data in database.");
        }
        int affecedRows = bizIdDAO.updateByPrimaryKeySelective(entity);
        log.info("fetch next max id. affected rows :{}", affecedRows);
        if (affecedRows == 0) {
            log.warn("Fetching nextMaxId failed.Trying to retry ...................");
            ThreadUtil.sleep(20);
            return fetchNextMaxId(bizType);//RETRY
        }
        return entity.getCurrentMax() + entity.getStep();
    }

    @Override
    public synchronized List<BizIdEntity> fetchAll(String module) {
        if (module ==  null){
            throw new IllegalArgumentException("module cannot be null");
        }
        module = module.toLowerCase();
        List<BizIdEntity> list = null;
        CommonResource resource = PropertiesLoader.getResource();
        JedisPool jedisPool = new JedisPool(resource.getHost(), resource.getPort());
        String uidLockId = PropertiesLoader.getUidLockId();
        RedisReentrantLock lock = new RedisReentrantLock(jedisPool, uidLockId);
        try {
            log.info("try to get lock. lockId is : {}", uidLockId);
            if (lock.tryLock(LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
                log.info("get lock : {}", uidLockId);
                bizIdDAO.initAll(module);
                list = bizIdDAO.queryByModule(module);
            } else {
                return fetchAll(module);//RETRY
            }
        } catch (Throwable t) {
            log.warn("fail to fetch all ids.", t);
        } finally {
            lock.unlock();
        }
        return list;
    }

    @Override
    public List<BizIdEntity> queryAll() {
        return bizIdDAO.queryAll();
    }
}
