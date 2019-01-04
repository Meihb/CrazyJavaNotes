package MultiThreading_16.ExecutorTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolTest {

    public static void main(String[] args) {
        //创建具有固定线程数的线程池
        ExecutorService es = Executors.newFixedThreadPool(6);
        //创建Runnable对象
        Runnable target = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "的i值为:" + i);

            }
        };
        es.submit(target);
        es.submit(target);
        //关闭线程池的submit
        es.shutdown();
    }
}
