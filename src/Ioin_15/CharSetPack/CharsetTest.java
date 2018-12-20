package Ioin_15.CharSetPack;

import java.nio.charset.Charset;
import java.util.SortedMap;

public class CharsetTest {
    public static void main(String[] args) {
        //获取java支持的全部字符集
        SortedMap<String, Charset> map = Charset.availableCharsets();
        for (String alias : map.keySet()
        ) {
            System.out.println(alias+"--->"+map.get(alias));

        }
        //本地文件编码属性
        System.out.println(System.getProperty("file.encoding"));
    }
}
