package Generics_09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 指定通配符上限 协变;协变只出不进,因为无法确保协变的子类能够使用相关"进"的方法
 * 指定通配符下限:逆变;
 */
public class MyUtils {
    //思考一下为何泛型申明需要安排在 返回值类型之前,因返回值类型可能需要用法哦泛型申明
    public static <T> T copy(Collection<? super T> dest, Collection<T> src) {
        T last = null;
        for (T ele : src) {
            last = ele;
            //逆变的泛型集合添加元素是安全的
            dest.add(ele);
        }
        return last;
    }

    public static void main(String[] args) {
        List<Number> ln = new ArrayList<>();
        List<Integer> li = new ArrayList<>();
        li.add(5);
        li.add(23);
        //copy方法可以知道最后一个复制的元素,与src集合元素类型相同
        Integer last = copy(ln, li);
        System.out.println(ln);
    }

}
