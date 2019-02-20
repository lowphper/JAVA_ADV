package com.fei;

import com.sun.istack.internal.FinalArrayList;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程买票问题
 */
class Ticket{
    private int count;
    public Ticket(int i) throws Exception{
        if(i<1){
            throw new Exception("票数不能小于1！");
        }
        count = i;
    }
    ReentrantLock lock = new ReentrantLock();
    public void sale(){
       lock.lock();
       try{
           if(count>0){
               System.out.println(Thread.currentThread().getName()+"卖出第"+count--+"还剩:"+count+"张");
           }
       }finally {
           lock.unlock();
       }
    }
}

public class Test01 {
    public static void main(String[] args) {
        try {
            Ticket t1 = new Ticket(300);
            for (int i = 0; i <3 ; i++) {
                new Thread(()->{
                    for (int j = 0; j <300 ; j++) {
                        t1.sale();
                    }
                },String.valueOf(i)).start();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {

        }

    }
}
