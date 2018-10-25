package Collections_08.Set_03;

import java.util.HashSet;

//Override equals
class A {
    @Override
    public boolean equals(Object obj) {
        return true;
    }
}

//Override hashcode()
class B {
    @Override
    public int hashCode() {
        return 1;
    }
}

//Override hachcode() and equals()
class C {
    @Override
    public int hashCode() {
        return 2;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}


/**
 * HashSet 是Set接口典型实现,根据对象的hashcode()返回值确定存储位置,
 * 如果同一存储位置的两个对象equals不同,会以链表形式存储在同一位置
 * 只有同hashcode()同equals()的才被认作同一对象
 * Set的定义时无重复的,因此当equals时,应当满足hashcode()相同,这是重写hashcode和equals的注意点
 */
public class HashSetTest {
    public static void main(String[] args) {
        HashSet books = new HashSet();
        books.add(new A());
        books.add(new A());
        books.add(new B());
        books.add(new B());
        books.add(new C());
        books.add(new C());
        System.out.println(books);

        books.iterator().forEachRemaining(System.out::println);
    }
}
