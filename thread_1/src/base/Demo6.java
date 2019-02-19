package base;

/**
 * 两个线程操作资源num=0，一个加1，一个减1，轮换进行10轮
 * 代码：当只有两个线程的时候，以下代码即可完成操作，
 * 但当线程多于2个时，会出现问题，错误出现在 if(num!=0){this.wait(); }中
 * 分析，线程操作中，用if会出现虚假唤醒，就是在线程A中，判断num!=0为true时，为真，这时候A线程陷入休眠，然后线程B进行加法操作
 * 线程B判断num!=0为true，也陷入休眠，然后C抢过资源，进行加法操作，num==0为false，进行减法操作，并唤醒其他线程，如果A线程抢过资源
 * 进行加法操作，这时候A线程不会再次判断num!=0,而是进入下面的操作，这时候num = num-1=0,然后唤醒其他资源，比如说线程B抢过资源，这时候
 * B线程不会进行if判断，而是进行下面的操作，这时候还是num = num-1 = -1!,数据出现问题了！
 * 就是因为if判断会出现虚假唤醒，要换成while进行判断
 * 多线程总结：
 * 1高内聚，低耦合  线程 操作 资源类
 * 2判断+干活+通知
 * 3避免虚假唤醒，线程判断用while
 */
class Count {
    private int num = 0;

    public synchronized void sunCount() throws Exception {
        //1判断
        if (num != 0) {
            this.wait();
        }
        //2干活
        System.out.println("加法进行操作值为" + (++num));
        //3通知
        this.notifyAll();
    }

    public synchronized void devCount() throws Exception {
        if (num == 0) {
            this.wait();
        }
        System.out.println("减法进行操作值为：" + (--num));
        this.notifyAll();
    }
}

public class Demo6 {
    public static void main(String[] args) {
        Count count = new Count();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    count.sunCount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "线程A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    count.devCount();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "线程B").start();
//        new Thread(()->{
//            for (int i = 0; i <10 ; i++) {
//                try {
//                    count.devCount();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        },"线程C").start();
//        new Thread(()->{
//            for (int i = 0; i <10 ; i++) {
//                try {
//                    count.devCount();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        },"线程D").start();
    }
}
