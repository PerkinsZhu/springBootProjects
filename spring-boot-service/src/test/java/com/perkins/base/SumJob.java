package com.perkins.base;

/**
 * @author ：Perkins Zhu
 * @date ：Created in 2020/8/1 11:58
 * @description：
 * @modified By：
 * @version: 1.0
 */

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * 多线程并发求和
 *
 * @author cobee
 * @since 2020-02-28
 */
public class SumJob extends RecursiveTask<Integer> {
    // 存放元素的list集合
    private List<Integer> elems;
    private int start;
    private int end;

    public SumJob(List<Integer> elems, int start, int end) {
        this.elems = elems;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int interval = end - start;
        // 以10个为一组计算，执行任务，最小执行单元
        if(interval <= 10){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("当前线程名:"+Thread.currentThread().getName());
            int sum = 0;
            for(int i = start; i < end; i++){
                sum += elems.get(i);
            }
            return sum;
        }else{
            // 继续拆分，直到拆分到最小的执行单元
            int x = (end + start) / 2;
            SumJob job1 = new SumJob(elems, start, x);
            job1.fork(); // 任务拆分
            SumJob job2 = new SumJob(elems, x, end);
            job2.fork(); // 任务拆分
            // 合并结果
            Integer join1 = job1.join();
            Integer join2 = job2.join();
            return join1 + join2;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> elems = new ArrayList<>();
        int sum = 0;
        for(int i = 1; i <= 100; i++){
            elems.add(i);
            sum += i;
        }
        System.out.println("for sum:" + sum);
        SumJob job = new SumJob(elems, 0, elems.size());
        ForkJoinPool forkJoinPool = new ForkJoinPool(5, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null, true);
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(job);
        System.out.println("forkJoin sum:" + forkJoinTask.get());


    }



}
