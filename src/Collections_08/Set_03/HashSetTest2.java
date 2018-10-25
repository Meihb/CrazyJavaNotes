package Collections_08.Set_03;


import java.util.HashSet;
import java.util.Iterator;

class R {
    int count;

    public R(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "R[count:" + this.count + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == R.class) {
            return (((R) obj).count == count);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.count;
    }
}

public class HashSetTest2 {
    public static void main(String[] args) {
        HashSet hs = new HashSet();

        hs.add(new R(5));
        hs.add(new R(-3));
        hs.add(new R(9));
        hs.add(new R(-2));

        System.out.println(hs);

        Iterator iterator = hs.iterator();
        R first = (R) iterator.next();

        first.count = -3;
        System.out.println(hs);
        hs.remove(new R(-3));
        //为何只删除掉一个呢? 因为其中一个R(-3)对象存储的位置是之前-2的位置,即-3的hashcode一直不符合,故尽管equals也无法相等
        System.out.println(hs);
        System.out.println("hs是否包含count为-3的R对象"+hs.contains(new R(-3)));//false
        System.out.println("hs是否包含count为-2的R对象"+hs.contains(new R(-2)));//false


    }
}
