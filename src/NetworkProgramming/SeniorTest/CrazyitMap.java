package NetworkProgramming.SeniorTest;

import java.util.*;

/*
 保存 用户和对应聊天室Socket 的映射表
 */
public class CrazyitMap<K, V> {
    //创建线程安全的HashMap
    public Map<K, V> map = Collections.synchronizedMap(new HashMap<K, V>());

    //根据value删除指定项
    public synchronized void removeByValue(Object value) {
        for (Object key : map.keySet()) {
            if (map.get(key) == value) {
                map.remove(key);
                break;
            }
        }
    }

    //获取所有value组成的Set
    public synchronized Set<V> valueSet() {
        Set<V> resultSet = new HashSet<V>();
        //将map中所有value添加到Set
        map.forEach((key, value) -> {
            resultSet.add(value);
        });
        return resultSet;
    }

    //根据value查找Key
    public synchronized K getKeyByValue(Object value) {
        for (K key : map.keySet()
        ) {
            if (map.get(key) == value) {
                return key;
            }
        }
        return null;
    }

    //实现put方法,该方法不允许value重复
    public synchronized V put(K key, V value) {
        for (V val : valueSet()
        ) {
            if (val.equals(value) && val.hashCode() == value.hashCode()) {
                throw new RuntimeException("MyMap实例中不允许有重复value");
            }
        }
        return map.put(key, value);
    }

}
