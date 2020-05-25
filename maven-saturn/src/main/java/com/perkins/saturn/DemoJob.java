package com.perkins.saturn;

import com.vip.saturn.job.AbstractSaturnJavaJob;
import com.vip.saturn.job.SaturnJobExecutionContext;
import com.vip.saturn.job.SaturnJobReturn;

/**
 * @author ：Perkins Zhu
 * @date ：Created in 2020/4/23 10:54
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class DemoJob extends AbstractSaturnJavaJob {

    @Override
    public SaturnJobReturn handleJavaJob(final String jobName, final Integer shardItem, final String shardParam, final SaturnJobExecutionContext context) {
        // do what you want here ...

        // 返回一个SaturnJobReturn对象，默认返回码是200表示正常的返回
        return new SaturnJobReturn("我是分片"+shardItem+"的处理结果");
    }
}
