package com.nio.review.concurrent;

import javax.sound.midi.Soundbank;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName CyclicBarrierDemo
 * @Description: CyclicBarrier
 * @Author Nio
 * @Date 2021/1/22
 * @Version V1.0
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(6, () -> {
            System.out.println("起飞!");
        });
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(finalI + "号到了");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
