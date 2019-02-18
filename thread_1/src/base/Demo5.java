package base;

import java.util.*;

/**
 * ArrayList，HashSet,HashMap集合的线程不安全示例
 */
public class Demo5 {
    public static void main(String[] args) {
        //1 ArrayList示例,可能出现null,null或只有两个数值的情况，线程不安全
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < 3 ; i++) {
//            //开个线程，线程中添加数值，线程名为i
//            new Thread(()->{list.add(UUID.randomUUID().toString().substring(0,3));
//                System.out.println(list);},String.valueOf(i)).start();
//        }
        //2 Hashset线程不安全示例,跟list差不多
//        Set<String> list = new HashSet<>();
//        for (int i = 0; i < 3 ; i++) {
//            //开个线程，线程中添加数值，线程名为i
//            new Thread(()->{list.add(UUID.randomUUID().toString().substring(0,3));
//                System.out.println(list);},String.valueOf(i)).start();
//        }
        //3 HashMap线程不安全示例,报异常ConcurrentModificationException，线程修改异常ConcurrentModificationException
//        Map<String,String> map = new HashMap();
//        for (int i = 0; i <10 ; i++) {
//            new Thread(()->{map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,3));
//                System.out.println(map);},String.valueOf(i)).start();
//        }

    }
}
