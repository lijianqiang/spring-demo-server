package com.bytehonor.server.demo.spring.service;

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

        LOG.info("val:{}", val);

        Assertions.assertTrue(value.equals(val), "test");
    }

}