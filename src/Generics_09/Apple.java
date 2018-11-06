package Generics_09;

import java.util.ArrayList;
import java.util.Collection;

//泛型可以使用在任何类、接口中,作为可定义的类型限制
public class Apple<T> {
    private T info;

    public Apple() {

    }

    //构造器
    public Apple(T info) {
        this.info = info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public T getInfo() {
        return this.info;
    }

    public static void main(String[] args) {
        Apple<String> apple = new Apple<>(new String("苹果xsmax"));
        System.out.println(apple.getInfo());

        Apple<Integer> apple1 = new Apple<>(23);
        System.out.println(apple1.getInfo());

        Collection<String> cs = new ArrayList<>();
        System.out.println(cs instanceof  Collection);
        System.out.println(cs.getClass()==(new ArrayList()).getClass());


    }
}

class AppleChild extends Apple<String> {

}
