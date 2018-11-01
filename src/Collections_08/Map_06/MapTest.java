package Collections_08.Map_06;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("111", 20);
        map.put("222", 32);
        map.put("333", 14);
        System.out.println(map.put("444", 32));
        System.out.println(map.put("333", 18));
        System.out.println(map);

        System.out.println(map.containsKey("444"));
        System.out.println(map.containsKey("5655"));
        System.out.println(map.containsValue(32));
        for (Object key : map.keySet()) {
            System.out.println(key + ":" + map.get(key));
        }
        map.forEach((ele,ele2)->System.out.println(ele+":"+ele2));

        map.replaceAll((ele1,ele2)->22);System.out.println(map);
    }
}
