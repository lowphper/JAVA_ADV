package base;

/**
 * 多线程总结
 */
public class Demo12 {
    /**
     * lambda表达式的使用：拷贝小括号，写死右箭头，落地大括号
     * 企业级多线程使用模板：线程 操作 资源类(高内聚，低耦合)
     * ArrayList线程不安全分析
     * CopyOnWriteArrayList  ArrayList线程安全集合的使用
     * ConcurrentHashMap    HashMap线程安全集合的使用
     * 多线程总结：线程 操作 资源类(高内聚，低耦合)---判断 干活 通知----避免虚假唤醒（用while,不要用if）
     * Lock代替Synchronized,condition.await代替wait,condition.signalAll代替notifyAll(三角关系的代替)
     *线程8锁，8种线程锁情况
     * 第三种创建线程的方法:实现Callable接口，这个接口方法可以抛异常和有返回值，用FutureTask来代替Runnable接口，
     * 三个线程类的使用：CountDownLatch(减法阻止线程，计数变为0),CyclicBarrier(加法阻止线程，线程达到一定数量),Semaphore(抢车位线程)
     * 枚举类的使用
     */
}
