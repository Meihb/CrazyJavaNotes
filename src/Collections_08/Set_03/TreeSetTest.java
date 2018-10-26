package Collections_08.Set_03;

import javax.annotation.processing.SupportedSourceVersion;
import java.util.TreeSet;


class Err {
};

public class TreeSetTest {
    public static void main(String[] args) {
        TreeSet treeSet = new TreeSet();
        treeSet.add(5);
        treeSet.add(2);
        treeSet.add(10);
        treeSet.add(-9);

        //输出集合元素,已经处于排序状态
        System.out.println(treeSet);

        System.out.println(treeSet.first());//

        System.out.println(treeSet.last());

        System.out.println(treeSet.headSet(4));

        System.out.println(treeSet.tailSet(5));

        System.out.println(treeSet.subSet(-3, 4));

        //Err cannot be cast to java.base/java.lang.Comparable，
        // TreeSet存储的对象必须 实现Comparable Interface ,包含compareTo(Object obj)
        //以下是 集中常见的 实现了该 Comparable Interface Object :
        //BigDecimal、BigInteger以及所有的数值类型对应的包装类,按照数值大小进行比较
        //Character 按照UNICODE 比较
        //Boolean true>false
        //String 按照字符串的UNICODE比较
        //Date、Time
        //且 应当都是统一类,即使用 该对象的comparableto()可以正常比较,另 相等也不行,即compareTo()不可返回0
        treeSet.add(new Err());//编译错误
    }
}
