package Collections_08.Set_03;

import java.util.EnumSet;

enum Season {
    SPRING, SUMMER, AUTUMN, WINTER;

}

public class EnumSetTest {
    public static void main(String[] args) {
        EnumSet es1 = EnumSet.allOf(Season.class);
        System.out.println(es1);
        //创建一个EnumSet空集合,指定其集合元素是Season的枚举类
        EnumSet es2 = EnumSet.noneOf(Season.class);
        System.out.println(es2);
        //手动添加两个元素
        es2.add(Season.WINTER);
        es2.add(Season.SPRING);
        System.out.println(es2);
        //以指定枚举值创建EnumSet集合
        EnumSet es3 = EnumSet.of(Season.SUMMER, Season.WINTER);
        System.out.println(es3);
        EnumSet es4 = EnumSet.range(Season.SUMMER, Season.WINTER);
        System.out.println(es4);
        EnumSet es5 = EnumSet.complementOf(es4);
        System.out.println(es5);

    }
}
