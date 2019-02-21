package base;

import java.util.concurrent.*;

/**
 * 自定义线程池
 * ThreadPoolExecutor(int corePoolSize,//常驻核心线程数
             int maximumPoolSize,//最大线程数
             long keepAliveTime,//空闲线程存活时间
             TimeUnit unit,//时间单位
             BlockingQueue<Runnable> workQueue,//线程等待，阻塞队列
             ThreadFactory threadFactory,//创建线程工厂
             RejectedExecutionHandler handler//拒绝策略
 */
public class Demo19 {
    public static void main(String[] args) {
        final ThreadPoolExecutor pool = new ThreadPoolExecutor(3,
                5,
                2,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );//这个最大线程数就是5+5=10,所以当线程有11的时候，如果采取AbortPolicy策略，就会报错RejectedExecutionException
        for (int i = 0; i <12; i++) {
            final int tempInt= i;
            pool.execute(()->{
                System.out.println(Thread.currentThread().getName()+"执行线程"+tempInt);
            });
        }
    }
}
