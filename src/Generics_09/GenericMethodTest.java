package Generics_09;

import java.util.ArrayList;
import java.util.Collection;

public class GenericMethodTest {
    static <T> void fromArrayToCollection(T[] ts, Collection<T> c) {
        for (T t : ts
        ) {
            c.add(t);
        }
    }

    public static void main(String[] args) {
        Object[] oa = new Object[100];
        Collection<Object> co = new ArrayList<>();
        //T代表Object类型
        fromArrayToCollection(oa, co);
        String[] sa = new String[100];
        Collection<String> cs = new ArrayList<>();



    }


}
