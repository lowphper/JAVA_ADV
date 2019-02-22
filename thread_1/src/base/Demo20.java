package base;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin使用
 * ForkJoinPool：充当fork/join框架里面的管理者，最原始的任务都要交给它才能处理
     * 它负责控制整个fork/join有多少个workerThread，workerThread的创建，激活都是由它来掌控。它还负责workQueue队列的创建和分配，
     * 每当创建一个workerThread，它负责分配相应的workQueue。
     * 然后它把接到的活都交给workerThread去处理，它可以说是整个frok/join的容器。
 * Fork/Join框架主要完成两个任务
 *  1.任务分割：首先Fork/Join框架需要把大的任务分割成足够小的子任务，如果子任务比较大的话还要对子任务进行继续分割
 　 2.执行任务并合并结果：分割的子任务分别放到双端队列里， 然后几个启动线程分别从双端队列里获取任务执行。
                        子任务执行完的结果都放在另外一个队列里，启动一个线程从队列里取数据，然后合并这些数据。

 *在Java的Fork/Join框架中，使用两个类完成上述操作

 　　1.ForkJoinTask:我们要使用Fork/Join框架，首先需要创建一个ForkJoin任务。该类提供了在任务中执行fork和join的机制。
        通常情况下我们不需要直接集成ForkJoinTask类，只需要继承它的子类，Fork/Join框架提供了两个子类：
 　　　　a.RecursiveAction：用于没有返回结果的任务
 　　　　b.RecursiveTask:用于有返回结果的任务
 　　2.ForkJoinPool:ForkJoinTask需要通过ForkJoinPool来执行
 　　任务分割出的子任务会添加到当前工作线程所维护的双端队列中，进入队列的头部。
    当一个工作线程的队列里暂时没有任务时，它会随机从其他工作线程的队列的尾部获取一个任务(工作窃取算法)。

 */
class MyForkThread extends RecursiveTask<Integer>{

    //计算1000以内的数值，超过50就分给不同的线程
    private final int NUM_ADJUST = 10;
    private int start ;
    private int end;
    private int num;
    private int result;

    public MyForkThread(int start, int end) {
        this.start = start;
        this.end = end;
        this.num = end - start;
    }

    @Override
    protected Integer compute() {
        //50以内直接计算
        if(num<=NUM_ADJUST){
            for (int i = start; i <=end ; i++) {
                result+=i;
            }
        }else{
            int middle = (start+end)/2;
            final MyForkThread task1 = new MyForkThread(start, middle);
            final MyForkThread task2 = new MyForkThread(middle+1, end);
            task1.fork();task2.fork();//调用compute方法
            result = task1.join()+task2.join();
        }

        return result;
    }
}
public class Demo20 {
    public static void main(String[] args)throws  Exception {
        MyForkThread forkThread = new MyForkThread(0,10000);//计算1000以内的数字
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Integer> result = pool.submit(forkThread);
        System.out.println(result.get());
        pool.shutdown();
    }
}
