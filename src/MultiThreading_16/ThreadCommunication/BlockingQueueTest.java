package MultiThreading_16.ThreadCommunication;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Producer extends Thread {
    private BlockingQueue<String> bq;

    public Producer(BlockingQueue<String> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        String strArr[] = new String[]{"JAVA", "STRUTS", "SPRING"};
        for (int i = 0; i < 999999999; i++) {
            System.out.println(getName() + "生产者准备生产集合元素!");
            try {
                Thread.sleep(200);
                //尝试放入元素
                bq.put(strArr[i % 3]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "生产完成");
        }
    }
}

class Consumer extends Thread {
    private BlockingQueue<String> bq;

    public Consumer(BlockingQueue<String> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(getName() + "消费者准备消费集合元素!");
            try {
                Thread.sleep(200);
                bq.take();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "消费完成");
        }
    }
}

/*
BlockingQueue 没有显示的wait或者notify,基本上直接靠CPU处理
隐式的 把 空、满 await
          余、空 notify
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        BlockingQueue<String> bq = new ArrayBlockingQueue(1);
        new Producer(bq).start();
        new Producer(bq).start();
        new Producer(bq).start();

        new Consumer(bq).start();
    }
}

