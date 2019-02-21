package base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 * 1.作用：线程复用，控制最大并发数，管理线程
 *          降低资源消耗：重复利用已创建线程
 *          提高线程的可管理性：线程池堆线程进行统一的分配管理
 *          提高响应速度：当任务到达时，无须再创建线程，直接拿过线程来进行任务
 *  调用new Thread()创建的线程缺乏管理，被称为野线程，而且可以无限制创建，之间相互竞争，会导致过多占用系统资源导致系统瘫痪
 *  不利于扩展，比如如定时执行、定期执行、线程中断
 *  Executor线程池框架：
 *              Executor(接口)
 *              ExecutorService(接口)
 *            ThreadPoolExecutor(实现类)
 *  Executors:线程池工具类，可以产生不同的线程池
 *          Executors.newFixedThreadPool(10);产生指定线程数的线程池      一池固定数线程池
 *          Executors.newSingleThreadExecutor();产生只有一个线程的线程池    一池一数线程池
 *          Executors.newSingleThreadExecutor();产生可变线程数的线程池     一池可扩容线程池
 *  实际上这三个线程池的内部都是 new了一个ThreadPoolExecutor类，只是参数不同罢了
 */
public class Demo17 {
    public static void main(String[] args) {
        //ExecutorService exe = Executors.newFixedThreadPool(5);
        //ExecutorService exe = Executors.newSingleThreadExecutor();
        final ExecutorService exe = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i <11 ; i++) {
                exe.execute(()->{//线程执行，要传个Runnable接口
                    System.out.println(Thread.currentThread().getName()+"线程执行");
                });
            }
        }catch (Exception e){}finally {
            exe.shutdown();
        }
    }
}
