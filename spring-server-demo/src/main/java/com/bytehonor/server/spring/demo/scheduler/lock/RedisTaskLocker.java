package com.bytehonor.server.spring.demo.scheduler.lock;

import com.bytehonor.sdk.lang.spring.constant.TimeConstants;
import com.bytehonor.sdk.server.spring.SpringServer;
import com.bytehonor.sdk.server.spring.scheduler.lock.PlanLocker;
import com.bytehonor.sdk.starter.redis.service.RedisCacheService;

public class RedisTaskLocker extends PlanLocker {

    private static final long LOCK_MILLIS = TimeConstants.MINUTE * 2;

    private final RedisCacheService redisCacheService;

    public RedisTaskLocker() {
        this.redisCacheService = SpringServer.bean(RedisCacheService.class);
    }

    @Override
    public boolean lock(String key) {
        return redisCacheService.lock(key, LOCK_MILLIS);
    }
}
