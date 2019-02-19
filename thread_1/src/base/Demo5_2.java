package base;

import java.util.ArrayList;

/**
 * ArrayList线程不安全解析(源码)
 */
public class Demo5_2 {
    /**
     * 线程不安全主要体现在add方法上
     *ArrayList在add方法时，主要有两部操作，1 判断是否需要扩容（需要就扩容）和 2 把传入的值添加到ArratList集合的数组中（添加到最后）
     * 出错主要就是在这两步
     *  public boolean add(E e) {
         ensureCapacityInternal(size + 1);  // Increments modCount!!
         elementData[size++] = e;
         return true;
        }
     * 首先第一步：ensureCapacityInternal(size + 1);判断是否需要扩容
     *      出错分析：不如两个线程A和B，A线程先进入，这是加入list集合的size为9，容量为10，判断不用扩容，这时突然运行线程B
     *          B也进入add方法，也是判断不容扩容，把B线程的值添加了进去，这时在运行A线程，就会出现数组越界异常！
     * 分析第二步：elementData[size++] = e；进行添加元素
     *      出错分析：第二步操作也不是原子性操作，它有两个步骤完成，elementData[size] = e 和 size = size+1
     *          这时就会出问题，比如size为1时，A线程进行添加，在elementData[1] = e处添加完成，还未size = size+1时，
     *          线程B抢过资源，线程B进行操作，这时候size还是1，这时比如B线程完成了添加elementData[1] = e 把A线程添加的值覆盖了
     *          然后A线程抢过资源，进行它接下来的操作，size = size+1=2,线程A完成全部操作，线程B完成它最后的操作size = size+1 = 3
     *          这时候就会出现elementData[2] = null,size = 3
     *          出现错误！
     *
     */

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList();
        list.add("zz");

    }

}
