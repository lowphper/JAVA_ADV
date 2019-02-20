package base;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier类的使用，
 *  它是在线程达到一定的数量再执行其中的方法
 *  与CountDownLatch对比，CountDownLatch是做减法，执行到0就执行后面的方法
 *  CyclicBarrier是做加法，他在线程达到一定的数量，就执行其中的方法
 */
public class Demo10 {
    public static void main(String[] args) {
        CyclicBarrier cb1 = new CyclicBarrier(7, () -> {
            System.out.println("线程数量达到，执行这个方法！");
        });
        for (int i = 0; i < 7; i++) {
            final int m = i + 1;
            new Thread(() -> {
                try {
                    System.out.println("执行第" + m + "个方法");
                    cb1.await();//计数
                } catch (Exception e) {}

                },"第" + m + "个线程").start();
            }
        }
    }
