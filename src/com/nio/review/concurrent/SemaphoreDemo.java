package com.nio.review.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SemaphoreDemo
 * @Description: Semaphore
 * @Author Nio
 * @Date 2021/1/22
 * @Version V1.0
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i < 7; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(finalI + "号抢到位置");
                try {
                    TimeUnit.SECONDS.sleep(finalI);
                    System.out.println(finalI + "号离开位置");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
