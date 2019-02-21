package com.fei;



import java.util.HashMap;
import java.util.Map;

class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    public void put(String key ,Object value){
        System.out.println("写操作前**************");
        map.put(key,value);
        System.out.println("写操作完成************");
    }
    public Object get(String key){
        Object result = null;
        System.out.println("读操作前**************");
        result =  map.get(key);
        System.out.println("值："+result+"+读操作完成************:");
        return result;
    }

}
public class Test02 {
    public static void main(String[] args) {
        MyCache m = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int n = i;
            new Thread(()->{
                System.out.print(Thread.currentThread().getName()+"\t");
                m.put(n+"",n+"");
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int n = i;
            new Thread(()->{
                System.out.print(Thread.currentThread().getName()+"\t");
                m.get(n+"");
            },String.valueOf(i)).start();
        }
    }
}
