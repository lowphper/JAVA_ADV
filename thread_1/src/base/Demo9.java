package base;

import java.util.concurrent.CountDownLatch;

/**
 * 秦灭六国
 * 一统天下
 * 使用类：CountDownLatch
 *  使用方法：await()  countDown()
 *
 */
public class Demo9 {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 下面的问题是：不能保证主线程最后运行，其他线程可能在主线程之后运行
         */
//        for (int i = 0; i <6 ; i++) {
//            final int m = i;
//            new Thread(()->{
//                System.out.println(m+"国灭亡！");
//            },String.valueOf(i).toString()).start();
//        }
//        System.out.println("秦国一统天下！");

        /**
         * 解决办法,使用CountDownLatch
         * 这个类允许线程在运行指定个线程前，阻止其他线程运行CountDownLatch(int count)
         * 两个方法：c.countDown();使计数减一   c.await();让其他线程等待，直到计数为0
         * 注意：Demo9_1是一个枚举类
         */
        CountDownLatch c = new CountDownLatch(6);
        for (int i = 0; i <6 ; i++) {
            final int m = i+1;
            new Thread(()->{
                System.out.println(Demo9_1.getEnum1(m).getName()+"国灭亡！");
                //计数减一，
                c.countDown();
            },String.valueOf(i).toString()).start();
        }
        //让其他线程等待，直到计数器变为0
        c.await();
        System.out.println("秦国一统天下！");
    }
}
