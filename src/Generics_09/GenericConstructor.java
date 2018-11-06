package Generics_09;

//泛型构造器,可以显/隐式的指定方法类型
class Fa {
    public <T> Fa(T t) {
        System.out.println(t);
    }
}

public class GenericConstructor {
    public static void main(String[] args) {
        //隐式
        Fa f1 = new Fa("222");
        Fa f2 = new Fa(111);
        //显式
        Fa f3 = new <String>Fa("sass");
        //显式冲突,代码出错
        Fa f4 = new <String>Fa(111);

    }

}
