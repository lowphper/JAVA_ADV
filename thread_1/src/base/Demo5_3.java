package base;

import java.util.HashMap;

/**
 * HashMap线程不安全的原因，待续
 */
public class Demo5_3 {
    public static void main(String[] args) {
        HashMap<String,String> map = new HashMap<>();
        map.put("key","val");
        /**
         * 看源码，在putVal中的最后有代码：
         *   ++modCount;
             if (++size > threshold)
             resize();
             afterNodeInsertion(evict);
         *
         *
         *
         *
         *
         */

    }
}
