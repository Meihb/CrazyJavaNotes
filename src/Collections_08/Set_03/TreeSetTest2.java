package Collections_08.Set_03;

import java.util.TreeSet;

class Z implements Comparable {
    int age;

    public Z(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public int compareTo(Object o) {
        return 1;
    }
}

public class TreeSetTest2 {
    public static void main(String[] args) {
        TreeSet treeSet = new TreeSet();
        Z z1 = new Z(6);

        treeSet.add(z1);
        System.out.println(treeSet.add(z1));

        System.out.print(treeSet);
        ((Z) treeSet.first()).age = 6;

    }
}
