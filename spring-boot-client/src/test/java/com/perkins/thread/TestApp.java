package com.perkins.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestApp {

     static ExecutorService service = Executors.newFixedThreadPool(5);
    private  static ThreadLocal<Integer> local = new ThreadLocal<>();

    public static void main(String[] args) {
        local.set(12);
        System.out.println(local.get());
        testThreadLocal();
        try {
            service.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"----"+local.get());
    }

    public static void testThreadLocal(){
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName()+"====="+i);
            int finalI = i;
            service.execute(() ->{
                System.out.println(Thread.currentThread().getName()+"= A =="+local.get());
                local.set(finalI);
                System.out.println(Thread.currentThread().getName()+"= B  =="+local.get());
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
