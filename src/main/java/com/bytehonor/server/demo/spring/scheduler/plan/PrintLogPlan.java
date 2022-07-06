package com.bytehonor.server.demo.spring.scheduler.plan;

import java.time.LocalDateTime;

import com.bytehonor.sdk.lang.spring.thread.SafeTask;
import com.bytehonor.sdk.server.spring.scheduler.plan.TimeGroupPlan;
import com.bytehonor.sdk.server.spring.scheduler.time.TimeGroup;
import com.bytehonor.sdk.server.spring.scheduler.time.TimeGroup.TimeGroupBuilder;
import com.bytehonor.server.demo.spring.scheduler.task.PrintLogTask;

public class PrintLogPlan extends TimeGroupPlan {

    private TimeGroup group;

    public PrintLogPlan() {
        TimeGroupBuilder builder = TimeGroup.builder();
        group = builder.every().build();
    }

    @Override
    public TimeGroup group() {
        return group;
    }

    @Override
    public SafeTask create(LocalDateTime ldt) {
        return new PrintLogTask(ldt);
    }

}
