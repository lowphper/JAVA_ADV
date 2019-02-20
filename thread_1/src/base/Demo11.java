package base;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore
 * 在多线程中控制线程活动的数量
 * 类似抢车位，车位数量一定，抢到车位的线程活动，没抢到的线程不活动
 * 方法:acquire(),release()
 */
public class Demo11 {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(4);//相当于最多4个线程活动
        for (int i = 0; i <9 ; i++) {
            new Thread(()->{
                try {
                    s.acquire();
                    System.out.println(Thread.currentThread().getName()+"线程抢到了车位！");//线程抢到了车位
                    TimeUnit.SECONDS.sleep(3);//睡三秒
                    System.out.println(Thread.currentThread().getName()+"线程离开了车位！");//离开车位

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    s.release();//释放资源-车位
                }
            },String.valueOf(i)).start();
        }

    }
}
