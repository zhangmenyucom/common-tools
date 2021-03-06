package com.taylor.common.lock.redis;

import com.taylor.common.lock.DistributedLockTemplate;
import com.taylor.common.lock.Callback;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.TimeUnit;


@Slf4j
public class RedisDistributedLockTemplate implements DistributedLockTemplate {

    private JedisPool jedisPool;

    public RedisDistributedLockTemplate(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public Object execute(String lockId, int timeout, Callback callback) {
        RedisReentrantLock distributedReentrantLock = null;
        boolean getLock = false;
        try {
            distributedReentrantLock = new RedisReentrantLock(jedisPool, lockId);
            if (distributedReentrantLock.tryLock(new Long(timeout), TimeUnit.MILLISECONDS)) {
                getLock = true;
                return callback.onGetLock();
            } else {
                return callback.onTimeout();
            }
        } catch (InterruptedException ex) {
            log.error(ex.getMessage(), ex);
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (getLock) {
                distributedReentrantLock.unlock();
            }
        }
        return null;
    }
}
