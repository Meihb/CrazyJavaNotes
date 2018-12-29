package MultiThreading_16;


/*
    使用Threads继承类来创建线程时,最大的问题是无法共享实例变量,当然可以用类变量代替
    同时run()方法中的this也注意一下,本质上是同一个问题
 */


/*
如果直接调用run()方法则jvm直接将之认定为普通的对象方法,那么就不会接受jvm的线程调度,立即执行且无法并发
 */

public class ThreadExtendsTest extends Thread {
    private  int i;

    //重写run方法,run()为线程执行体
    @Override
    public void run() {
        for (; i < 100; i++) {
            //当线程类继承Thread类是,直接使用this获取当前线程
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //调用currentThread()方法获取当前线程
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                //创建并启动第一个线程
                new ThreadExtendsTest().start();
                //创建并启动第二个线程
                new ThreadExtendsTest().start();
            }
        }


    }
}
