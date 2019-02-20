package base;

import java.util.concurrent.TimeUnit;

/**
 * 线程8锁
 * 1正常线程，一般先发送短信，再发送邮箱？先发短信，因为在主线程中，在new B线程前，睡了2秒，这时候正常情况，A线程已经执行完了
 * 2发送短信时暂停4秒，先发短信还是先发邮箱？先发短信，因为在执行A线程是有synchronized修饰方法，
 *          它会锁住实例对象的所有synchronized修饰的方法，只有执行完了发送短信的方法，才能释放锁
 * 3新增普通开机方法，是先执行短信还是开机方法？先执行开机方法，因为synchronized方法只锁实例对象的synchronized修饰的方法，普通方法不锁
 * 4有两部手机，是先打印短信还是邮箱方法？先执行发送邮箱的方法，因为synchronized方法只锁实例对象，对于不同的示例对象，两者无关
 * 5静态同步方法，一部手机，先发短信还是邮箱？先发短信，因为对于synchronized修饰的静态方法，它锁的是类模板的方法，跟示例对象类似，只是它
 *          锁的是类静态方法
 * 6静态同步方法，两部手机，先发短信还是邮箱？先发短信，因为synchronized会锁住所有synchronized修饰的静态方法,
 *           两个示例对象用的静态方法时共用的，所以锁一个静态方法就把所有实例对象的静态方法锁了
 * 7一个普通同步方法，一个静态同步方法，一个手机，先发短信还是先发邮箱？发送邮箱，因为静态方法与普通的实例方法不共用一个方法，他们之间没有关系
 * 8一个普通同步方法，一个静态同步方法，两个手机，先发短信还是先发邮箱？先发邮箱，因为两个方法，静态与非静态没有关系，也与示例对象没有关系
 * 总结：对于一个实例对象来说，synchronized锁的是实例对象本身
 *       静态方法时所有实例对象共享的
 *       普通方法与synchronized修饰的方法无关，不受其影响
 *
 */
class Phone{
    //发送短信暂停4秒
    public static synchronized void sendSMS(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发送短信");
    }

    //发送邮箱方法
    public  synchronized void sendEmail(){
        System.out.println("发送邮箱");
    }

    //普通开机方法
    public void openPhone(){
        System.out.println("开机方法");
    }
}
public class Demo7 {
    public static void main(String[] args) {
        Phone p1 = new Phone();
        Phone p2 = new Phone();
        //A线程
        new Thread(()->{p1.sendSMS();},"A线程").start();
        //睡一下再new B线程
        try { Thread.sleep(2000);} catch (InterruptedException e) { e.printStackTrace();    }
        //B线程
        new Thread(()->{p2.sendEmail();},"B线程").start();

        //C线程
//        new Thread(()->{p1.openPhone();},"C线程").start();
    }

}
/* 他人笔记
 *  一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法了，
 *  其它的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些synchronized方法
 *  锁的是当前对象this，被锁定后，其它的线程都不能进入到当前对象的其它的synchronized方法
 *
 *   加个普通方法后发现和同步锁无关
 *  换成两个对象后，不是同一把锁了，情况立刻变化。
 *
 *  所有的非静态同步方法用的都是同一把锁——实例对象本身，
 *  23==23.0
 *   synchronized实现同步的基础：Java中的每一个对象都可以作为锁。
 *  具体表现为以下3种形式。
 *  对于普通同步方法，锁是当前实例对象。
 *  对于静态同步方法，锁是当前类的Class对象。
 *  对于同步方法块，锁是Synchonized括号里配置的对象。
 *
 当一个线程试图访问同步代码块时，它首先必须得到锁，退出或抛出异常时必须释放锁。

 也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必须等待获取锁的方法释放锁后才能获取锁，
 可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，
 所以毋须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。

 所有的静态同步方法用的也是同一把锁——类对象本身，
 这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
 但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，
 而不管是同一个实例对象的静态同步方法之间，
 还是不同的实例对象的静态同步方法之间，只要它们同一个类的实例对象！
 *
 */
