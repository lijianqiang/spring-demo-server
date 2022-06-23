package com.bytehonor.server.demo.spring.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytehonor.sdk.define.spring.result.StringData;
import com.bytehonor.sdk.lang.spring.util.LocalDateTimeUtils;

@RestController
public class IndexController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private Integer serverPort;

    @RequestMapping("/")
    public StringData actionIndex() {
        String server = new StringBuilder(applicationName).append(":").append(serverPort).toString();
        LOG.info("hello, {}", server);
        return StringData.of("hello world");
    }

    @RequestMapping("/time")
    public StringData actionTime() {
        LocalDateTime ldt = LocalDateTime.now();
        String format = LocalDateTimeUtils.format(ldt);
        LOG.info("time:{}, timestamp:{}", format, LocalDateTimeUtils.toTimestamp(ldt));
        return StringData.of(format);
    }

}
