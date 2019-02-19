package base;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 使用线程安全的集合类
 * CopyOnWriteArrayList和ConcurrentHashMap类使用了写时复制技术
 * 在添加时，先复制一个长度加一的数组，然后把新增加的元素加到新数组中，然后集合指针指向新数组
 *
 */
public class Demo5_1 {
    public static void main(String[] args) {
        //list使用CopyOnWriteArrayList源码里加了add方法加了ReentrantLock的lock方法，是线程安全的
//        CopyOnWriteArrayList<Object> list = new CopyOnWriteArrayList<>();
//        for (int i = 0; i < 30 ; i++) {
//            new Thread(()->{list.add(UUID.randomUUID().toString().substring(0,3));
//                System.out.println(list);},String.valueOf(i)).start();
//        }
        //Hashset方法
//        CopyOnWriteArraySet<Object> list = new CopyOnWriteArraySet<>();
//        for (int i = 0; i < 30 ; i++) {
//            new Thread(()->{list.add(UUID.randomUUID().toString().substring(0,3));
//                System.out.println(list);},String.valueOf(i)).start();
//        }
        //在ConcurrentHashMap中添加元素用到了synchronized
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30 ; i++) {
            new Thread(()->{map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,3));
                System.out.println(map);},String.valueOf(i)).start();
        }
    }
}
