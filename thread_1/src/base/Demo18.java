package base;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池的7个参数
 *  this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
            Executors.defaultThreadFactory(), defaultHandler);
    参数解释：
        1.corePoolSize:线程池的常驻线程数。
        2.maximumPoolSize：线程池的最大线程数
        3.keepAliveTime：空闲线程池的存活时间
        4.unit：存活时间的时间单位（如，秒，毫秒等）
        5.workQueue：阻塞队列，被提交但未执行的线程存在的地方，一般线程池的最大线程就是最大线程数+阻塞队列的大小
        6.Executors.defaultThreadFactory()：创建线程的工厂，一般默认
        7.defaultHandler：饱和拒绝策略，就是线程数大于最大线程数+阻塞队列的大小是采取的策略
 线程池的拒绝策略：
     AbortPolicy（默认）:如果线程池满了，直接抛异常
     DiscardPolicy:如果线程池满了，直接把线程丢掉
     DiscardOldestPolicy：如果线程满了，把等待时间最长的丢掉
     CallerRunsPolicy：如果线程满了，把线程丢给调用线程的线程（一般主线程）

 注意：线程池不要通过Executors工具类创建，因为其创建的线程池有问题
        1.Executors.newFixedThreadPool()和 Executors.newSingleThreadExecutor()
                创建的阻塞队列为Integer.MAX_VALUE(21亿),可能出现OOM
        2.Executors.newCachedThreadPool()创建的线程池最大线程数为Integer.MAX_VALUE(21亿)，可能OOM
 */
public class Demo18 {
    public static void main(String[] args) {

    }
}
