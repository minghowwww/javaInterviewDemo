package com.nio.review.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName CountDownLatchDemo
 * @Description: CountDownLatch
 * @Author Nio
 * @Date 2021/1/22
 * @Version V1.0
 **/
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

    }

    /**
     * 基本用法
     */
    public void basicCountDownLatch() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 1; i < 6; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("第" + finalI + "名就位!");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println("人齐了出发!");
    }
}
