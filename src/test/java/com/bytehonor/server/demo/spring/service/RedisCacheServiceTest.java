package com.bytehonor.server.demo.spring.service;

import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bytehonor.sdk.starter.redis.service.RedisCacheService;

@SpringBootTest
public class RedisCacheServiceTest {

    private static final Logger LOG = LoggerFactory.getLogger(RedisCacheServiceTest.class);

    @Autowired
    private RedisCacheService redisCacheService;

    @Test
    public void test() {
        String key = "name";
        String value = "Elon Musk";
        redisCacheService.kvSet(key, value);

        String val = redisCacheService.kvGet(key);

        LOG.info("test val:{}", val);

        Assertions.assertTrue(value.equals(val), "test");
    }

    @Test
    public void testIncrement() {
        String key = "testIncrement";
        redisCacheService.delete(key);
        int size = 10;
        for (int i = 0; i < size; i++) {
            redisCacheService.increment(key);
        }
        int val = redisCacheService.getCount(key);

        LOG.info("testIncrement val:{}", val);
        redisCacheService.delete(key);
        Assertions.assertTrue(val == size, "test");
    }

    @Test
    public void testSet() {
        String key = "testSet";
        redisCacheService.delete(key);
        int size = 10;
        for (int i = 0; i < size; i++) {
            redisCacheService.setLongAdd(key, 1L * i);
        }
        Set<Long> set = redisCacheService.setLongMemebers(key);
        for (Long lo : set) {
            LOG.info("long:{}", lo);
        }

        redisCacheService.delete(key);
        Assertions.assertTrue(set.size() == size, "test");
    }
}
