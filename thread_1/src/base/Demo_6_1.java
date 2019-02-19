package base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用Lock代替Synchronized
 */

class Count1 {
    private int num = 0;
    private Lock lock = new ReentrantLock();//创建Lock
    private Condition condition = lock.newCondition();//创建Condition，来代替wait和notifyAll配合lock使用

    public void sunCount() throws Exception {
        lock.lock();
        try {
            //1判断
            while (num != 0) {
                condition.await();
            }
            //2干活
            System.out.println("进行加法操作，值变为：" + (++num));
            //3通知
            condition.signalAll();//唤醒
        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }
    public void devCount() throws Exception {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            System.out.println("进行减法操作，值变为：" + (--num));
            condition.signalAll();//唤醒

        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }

}



public class Demo_6_1 {
    public static void main(String[] args) {
        Count1 c1 = new Count1();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    c1.sunCount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"线程A_1").start();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                try {
                    c1.devCount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"线程B_1").start();
    }
}
