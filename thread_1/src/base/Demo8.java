package base;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 第三种多线程方法实现Callable接口
 */
class Phone1 implements Callable {

    //继承Callable类要实现的方法，也是函数式类,与Runnable不同的是它有返回值和抛出异常
    @Override
    public Object call() throws Exception {

        System.out.println("运行方法");
        return 12;
    }
}

public class Demo8 {
    public static void main(String[] args) throws Exception{
        //1 普通写法
       // FutureTask<Integer> f1 = new FutureTask<Integer>(new Phone1());
        //2 lambda表达式写法
        FutureTask<Integer> f1 = new FutureTask<Integer>(()->{
            System.out.println("方法输出");
            return 12;
        });

        Thread t1 = new Thread(f1, "线程A");
        t1.start();
        //获得返回值
        System.out.println("返回值："+f1.get());
    }
}
/**
 * 总结:Callable也是创建线程的一种方法，这个接口可以抛出异常和有返回值，方法为call方法，
 * 使用时配合FutureTask使用，FutureTake继承了Runnable接口，且构造函数有一个可以传入实现Callable接口的类
 *      在new线程的时候直接传入FutureTask即可，它会执行call方法，返回值可以在执行线程后用实例.get()获取
 */
