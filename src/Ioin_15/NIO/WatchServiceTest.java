package Ioin_15.NIO;

import java.nio.file.*;

public class WatchServiceTest {

    public static void main(String[] args) throws Exception {
        //获取WatchService对象
        WatchService watchService = FileSystems.getDefault().newWatchService();
        //注册文件变更
        Paths.get("D:/").register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
        );

        while (true) {
            //take方式阻塞等待变更
            WatchKey watchKey = watchService.take();
            for (WatchEvent<?> event : watchKey.pollEvents()) {
                System.out.println(event.context() + "发生变化" + event.kind() + "事件!");

            }
            //重置watchkey
            boolean valid = watchKey.reset();
            if (!valid) {
                break;
            }
        }
    }


}
