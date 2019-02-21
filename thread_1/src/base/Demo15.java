package base;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 阻塞队列：在多线程生产者-消费者中很好用
 * 阻塞队列是在队列的基础上诞生的（先进先出），多增加了两个功能：
 * 1.支持阻塞的插入方法：在队列满时，队列会阻塞线程的插入，直到队列不满
 * 2.支持阻塞的移除方法，在队列为空时，获取元素的线程会等到队列变为非空
 * 3.方法调用：
 * 方法\处理方式	     抛出异常	    返回特殊值	一直阻塞	超时退出
 * 插入方法	          add(e)	    offer(e)	put(e)	    offer(e,time,unit)
 * 移除方法            remove()	    poll()	    take()	    poll(time,unit)
 * 检查方法	          element()	    peek()	    不可用	    不可用
 * <p>
 * 注意：
 * 队列的使用如集合List的使用很相似
 * ArrayBlockingQueue（ArrayList）   LinkedBlockingQueue(LinkedList)
 * SynchronousQueue(只存储单个元素)
 */
public class Demo15 {
    public static void main(String[] args) {
        BlockingQueue<Integer> block = new ArrayBlockingQueue<Integer>(3);//指定队列的大小，具体看源码
        //添加域移除方法看3.方法调用
        //使用add，remove，element会出现异常，如队列元素满了，或队列没有元素等
        //put,take要慎用，当队列没有值时，还要移除值时，线程会一直等待；当队列值满了，还要塞值，线程也会等待
        //一般使用offer,和poll,peek,它们在特殊情况会返回特殊值
        block.offer(1);
        block.offer(2);
        System.out.println(block.peek());//获取当前队列的头元素
        block.offer(3);
        System.out.println(  block.offer(1));//返回false，超过队列最大允许的值了
        block.poll();
        block.poll();
        System.out.println(block.peek());//获取当前队列的头元素
        block.poll();
        System.out.println( block.poll());//返回null,队列已经没有值了，还要移除，返回null

    }
}
