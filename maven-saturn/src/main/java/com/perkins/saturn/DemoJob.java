package com.perkins.saturn;

import com.vip.saturn.job.AbstractSaturnJavaJob;
import com.vip.saturn.job.SaturnJobExecutionContext;
import com.vip.saturn.job.SaturnJobReturn;

public class DemoJob extends AbstractSaturnJavaJob {

    @Override
    public SaturnJobReturn handleJavaJob(final String jobName, final Integer shardItem, final String shardParam, final SaturnJobExecutionContext context) {
        return new SaturnJobReturn("i am "+shardItem+"result");
    }
}
