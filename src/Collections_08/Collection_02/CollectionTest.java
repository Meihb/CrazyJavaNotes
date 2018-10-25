package Collections_08.Collection_02;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class CollectionTest {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        c.add("孙悟空");
        c.add(6);

        System.out.println("c集合的元素个数:" + c.size());
        c.remove(6);
        System.out.println("c集合的元素个数:" + c.size());

        System.out.println("c集合是否包含\"孙悟空\"字符串:" + c.contains("孙悟空"));

        Collection books= new HashSet();
        books.add("轻量级Java EE企业应用实战");
        books.add("疯狂Java讲义");

    }
}
