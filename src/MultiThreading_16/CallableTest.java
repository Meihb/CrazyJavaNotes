package MultiThreading_16;

/*
Callable是Runaable的增强版
call()方法有返回值,可以抛出异常
不过不可以直接作为target
 */

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/*
Future接口 代表Callable接口call方法返回值
FutureTask实现了Future接口和Runnable接口
 */
public class CallableTest {
    public static void main(String[] args) {
        CallableTest ct = new CallableTest();
        //使用FutureTask包装Callable对象
        FutureTask<Integer> task = new FutureTask(
                 () -> {
                    int i = 0;
                    for (; i < 100; i++) {
                        System.out.println(Thread.currentThread().getName() + " " + i);

                    }
                    //call方法有返回值
                    return i;
                }
        );
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 20) {
                new Thread(task, "有返回值的线程").start();
            }
        }

        try{
            //获取线程返回值
            System.out.println(task.get());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
