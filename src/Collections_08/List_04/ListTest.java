package Collections_08.List_04;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListTest {
    public static void main(String[] args) {
        List books = new ArrayList();
        books.add(new String("轻量级Java EE企业应用实战"));
        books.add(new String("疯狂Java讲义"));
        books.add("疯狂Android讲义");
        System.out.println(books);

        //add(index ,object)在指定Index之后的数据将会被后移以为
        books.add(1, "疯狂Ajax讲义");
        for (int i = 0; i < books.size(); i++) {
            System.out.println(books.get(i));
        }
        //删除第三个元素
//        books.remove(2);
        System.out.println(books);
        //获取索引位置
        System.out.println(books.indexOf("疯狂Android讲义"));
//        books.set(1, "疯狂Java讲义");
        System.out.println(books);
        //[from,to)
        System.out.println(books.subList(1, 2));

        //sort() 以来Comparator控制元素顺序,replaceAll UnaryOperator() 重新算值,排序方法不变
        //两者皆是函数式接口
        books.sort((o1, o2) -> ((String) o1).length() - ((String) o2).length());
        System.out.println(books);
        books.sort((o1, o2) -> -1);
        System.out.println(books);

        books.replaceAll(ele -> ((String) ele).length());
        System.out.println(books);


        ListIterator li = books.listIterator();
        System.out.println("正向输出");
        while (li.hasNext()) {
            System.out.println(">>>" + li.next());
            li.add("-------");//在上一次迭代位置添加该对象,因此不是下一个迭代的对象
        }
        System.out.println("逆向输出");
        while (li.hasPrevious()) {
            System.out.println("<<<"+li.previous());
        }
    }
}
