package com.perkins.springbootweb.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ：Perkins Zhu
 * @date ：Created in 2021/2/26 17:24
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class ThreadApp {
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    String lock = "lock";

    public static void main(String[] args) {
        ThreadApp app = new ThreadApp();
        app.startThread1();
        app.startThread2();
    }

    public void startThread1() {
        executorService.execute(() -> {
            synchronized (lock) {
                int i = 0;
                while (i < 10) {
                    System.out.println("===>" + i);
                    if (i == 4) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    i++;
                }
            }
        });
    }

    public void startThread2() {
        executorService.execute(() -> {
            synchronized (lock) {
                System.out.println("获取到锁");
                try {
                    Thread.sleep(3000);
                    System.out.println("准备唤醒");
                    /**
                     *使用的时候是因为当前线程任务完成了，可以结束当前线程任务，释放该锁。
                     * 所以可以主动调用notify去唤醒等待的线程
                     */
                    lock.notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
