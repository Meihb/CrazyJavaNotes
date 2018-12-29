package MultiThreading_16;


/*
Runnable实现类可以共享实例变量
 */
public class RunnableTest implements Runnable {

    private int i;

    @Override
    public void run() {
        for (; i < 100; i++) {
            //当线程类实现Runnable接口时,无法用this获取当前Thread,故使用Thread.currentThread()
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                RunnableTest rt = new RunnableTest();
                //通过new Thread(target,name)方法创建新的线程
                new Thread(rt, "新线程1").start();
                new Thread(rt, "新线程2").start();
                new Thread((Runnable)(()->{
                    for (int j=0; j < 100; j++) {
                        //当线程类实现Runnable接口时,无法用this获取当前Thread,故使用Thread.currentThread()
                        System.out.println(Thread.currentThread().getName() + " " + j);
                    }
                }),"新线程3").start();
            }

        }
    }
}
