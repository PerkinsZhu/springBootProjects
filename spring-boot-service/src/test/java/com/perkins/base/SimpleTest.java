package com.perkins.base;

import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

/**
 * @author ：Perkins Zhu
 * @date ：Created in 2020/8/1 13:45
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class SimpleTest {

    RedissonClient client;



    @Test
    public void test03() {

        Instant start = Instant.now();
        LongStream.rangeClosed(0, 110)
                //并行流
                .parallel()
                .reduce(0,(a,b)->{
                    System.out.println(Thread.currentThread().getName());
                    return 23423L;});


        LongStream.rangeClosed(0, 110)
                //顺序流
                .sequential()
                .reduce(0, Long::sum);


        Instant end = Instant.now();
        System.out.println("耗费时间" + Duration.between(start, end).toMillis());

    }


    @Test
    public void testparStream(){
        List list = new ArrayList<Integer>(1000);
        for (int i = 0; i <1000; i++) {
            list.add(i);
        }
        list.parallelStream().forEach(i ->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println("===end===");

    }

}
