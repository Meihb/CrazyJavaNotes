package NetworkProgramming;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownUtil {

    //顶一下载资源的路径
    private String path;
    //指定下载资源的文件保存位置
    private String targetFile;
    //定义下载资源的线程数量
    private int threadNum;
    //定义下载文件的总大小
    private int fileSize;
    //定义下载的Thread对象
    private DownThread[] threads;

    public DownUtil(String path, String targetFile, int threadNum) {
        this.path = path;
        this.targetFile = targetFile;
        this.threadNum = threadNum;
        //初始化Threads数组
        threads = new DownThread[threadNum];
    }

    public void download() throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty(
                "Accept",
                "image/gif,image/jepg,image/pjpeg,application/x-shockwave-flash," +
                        "application/xaml+xml,application/vnd.ms-xpsdocument,application/x-ms-xbap," +
                        "application/x-ms-application,application/vnd.ms-excel," +
                        "application/vnd.ms-powerpoint,application/msword,*/*"
        );
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Connection", "Keep-Alive");

        //得到文件大小
        fileSize = conn.getContentLength();
        conn.disconnect();
        int currentPartSize = fileSize / threadNum + 1;
        RandomAccessFile file = new RandomAccessFile(targetFile, "rw");
        //设置本地文件大小
        file.setLength(fileSize);
        file.close();

        for (int i = 0; i < threadNum; i++) {
            //计算每个线程的下载开始位置
            int startPos = i * currentPartSize;
            //每个线程使用一个RandomAccessFile进行下载
            RandomAccessFile currentPart = new RandomAccessFile(targetFile, "rw");
            //定位下载位置
            currentPart.seek(startPos);
            //创建线程
            threads[i] = new DownThread(startPos, currentPartSize, currentPart);
            threads[i].start();
        }
    }

    public double getCompleteRate() {
        //统计多个线程已经下载的总大小
        int sumSize = 0;
        for (int i = 0; i < threadNum; i++) {
            sumSize += threads[i].length;
        }
        //返回百分比
        return sumSize * 1.0 / fileSize;
    }

    private class DownThread extends Thread {
        //当前线程的下载位置
        private int startPos;
        //当前线程需要下载的文件大小
        private int currentPartSize;
        //当前线程需要下载的文件快
        private RandomAccessFile currentPart;
        //定义该线程已下载的字节数
        public int length;

        public DownThread(int startPos, int currentPartSize, RandomAccessFile currentPart) {
            this.startPos = startPos;
            this.currentPart = currentPart;
            this.currentPartSize = currentPartSize;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(path);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(5 * 1000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty(
                        "Accept",
                        "image/gif,image/jepg,image/pjpeg,application/x-shockwave-flash," +
                                "application/xaml+xml,application/vnd.ms-xpsdocument,application/x-ms-xbap," +
                                "application/x-ms-application,application/vnd.ms-excel," +
                                "application/vnd.ms-powerpoint,application/msword,*/*"
                );
                conn.setRequestProperty("Accept-Language", "zh-CN");
                conn.setRequestProperty("Charset", "UTF-8");
                InputStream inputStream = conn.getInputStream();

                //跳过startPos个字节,表明该线程只下载自己负责的部分文件
                inputStream.skip(startPos);
                byte[] buffer = new byte[1024];
                int hasRead = 0;
                //读取网络文件
                while (length < currentPartSize && (hasRead = inputStream.read(buffer)) >0) {
                    currentPart.write(buffer, 0, hasRead);
                    length += hasRead;
                }
                currentPart.close();
                inputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws Exception {
        //初始化对象
        final DownUtil downUtil = new DownUtil("https://i2.hdslb.com/bfs/archive/7aaabf9a278e7147c928bfad79c64b8d5628447c.jpg@160w_100h.jpg",
                "test.png", 4);
        downUtil.download();
        new Thread(
                () -> {
                    while (downUtil.getCompleteRate() < 1) {
                        //每隔0.1s查询一次任务完成进度
                        //GUI程序中可根据改进度绘制进度条
                        System.out.println("已完成:" + downUtil.getCompleteRate());
                        try {
                            Thread.sleep(5);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
        ).start();

    }
}
