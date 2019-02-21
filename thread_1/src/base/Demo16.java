package base;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Callable的使用（二）
 */
public class Demo16 {
    public static void main(String[] args) throws  Exception{
        FutureTask<Integer> task = new FutureTask<>(()->{
            System.out.println("  开始执行Callable接口的call方法");
            TimeUnit.SECONDS.sleep(3);
            return 1024;
        });//创建执行线程的类
        new Thread(task,"A").start();
        System.out.println("---");
        //1.阻塞
//        System.out.println("获取值："+task.get());//这个get()方法时一个同步方法，A线程不执行完，会阻塞下面的程序运行
//        System.out.println(Thread.currentThread().getName()+"执行了");

        //2.轮询 FutureTask有一个isDone()方法，当FutureTask返回结果了，isDone返回true，否则返回fasle，我们可以利用实现轮询
        while (!task.isDone()){
            System.out.println("正在查询");
        }
        System.out.println("查询完毕：值为："+task.get());


    }
}
