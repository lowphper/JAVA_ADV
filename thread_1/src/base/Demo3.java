package base;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程的工业模板与套路
 * 1线程  2操作 3资源类
 * 高内聚 低耦合
 * 以买票为例，三个窗口买30张票
 */
//资源类
class Ticket {
    private int count = 30;
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();//锁上
        try {
            if (count > 0) {
                count--;
                System.out.println(Thread.currentThread().getName() + "卖出票一张,还剩" + count);
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();//释放锁
        }
    }
}


public class Demo3 {
    public static void main(String[] args) {
        Ticket t1 = new Ticket();
        //完整写法，调用线程1
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++) {
                    t1.sale();
                }
            }
        }, "窗口1");
        //lambda表达式写 拷贝小括号，写死右箭头，落地大括号
        new Thread(() -> {
            for (int i = 0; i < 30; i++) t1.sale();
        }, "窗口2").start();

        new Thread(() -> {
            for (int i = 0; i < 30; i++) t1.sale();
        }, "窗口2").start();

    }
}
