package base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock的使用
 * 情景：
 *  在一个资源类中，存在读取与写操作，读取操作对资源类没有影响，应该允许所有线程同时读取，
 *  而写操作应该只允许一个线程操作，这时候就用到了我们的ReentranReadWriteLock类了
 *  此类允许多个线程同时读取操作，只允许一个线程写操作：
 *  总结：
 *      读-读能共存
 *      读-写不能共存
 *      写-写不能共存
 *      读写互斥，写写互斥，读读无事
 *   解释：就是在读的时候允许多个线程同时读，但不允许写操作，
 *   同理，在写操作的时候也不允许读操作，写操作时更不允许写操作了
 *
 *   多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 *   如果有一个线程想去写共享资源来，就不应该再有其它线程可以对该资源进行读或写
 */
class MyCache{
    private volatile Map<String,Object> map = new HashMap();
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();//创建读写锁
    //写操作，要上锁
    public void put(String key,Object value){
        lock.writeLock().lock();//上写锁
        try {
            System.out.println(Thread.currentThread().getName()+"写操作"+key+" 开始前");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写操作 完成"+key);
        }catch (Exception e){}
        finally {
            lock.writeLock().unlock();//释放锁
        }
    }

    //读操作
    public Object get(String key){
        Object result = null;
        lock.writeLock().lock();//上锁
        try {
            System.out.println(Thread.currentThread().getName()+"读操作 "+key+"开始前");
            result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读操作 完成"+key);
        }catch (Exception e){

        }finally {
            lock.writeLock().unlock();//释放锁
        }
        return result;
    }
}
public class Demo13 {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i <10 ; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <=5; i++)
        {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();
        }
    }
}
