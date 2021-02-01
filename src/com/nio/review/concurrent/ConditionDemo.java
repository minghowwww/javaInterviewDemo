package com.nio.review.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConditionDemo
 * @Description: Condition
 * @Author Nio
 * @Date 2021/1/25
 * @Version V1.0
 **/
public class ConditionDemo {

    private volatile Integer number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public static void main(String[] args) {
        ConditionDemo conditionDemo = new ConditionDemo();
        for (int i = 1; i <= 3; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    conditionDemo.threadPrint(finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "myThread" + i).start();
        }
    }

    public void threadPrint(Integer j) throws InterruptedException {
        lock.lock();
        number = j;
        try {
            while (number < 1 || number > 3) {

            }
            c1.signal();
            if (number == 1) {
                c1.await();
                for (int i = 0; i < 3; i++) {
                    TimeUnit.MILLISECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName());
                }
                c2.signal();
            } else if (number == 2) {
                c2.await();
                for (int i = 0; i < 5; i++) {
                    TimeUnit.MILLISECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName());
                }
                c3.signal();
            } else {
                c3.await();
                for (int i = 0; i < 10; i++) {
                    TimeUnit.MILLISECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName());
                }
                c3.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }
}
