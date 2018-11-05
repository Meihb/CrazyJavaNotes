package Generics_09;

interface Foo<T> {
    void test(T t);
}

interface Eoo {
    void test(String string);
}

public class AnonymousDiamond {

    public static void main(String[] args) {
        Foo<String> f = new Foo<>() {
            @Override
            public void test(String string) {
                System.out.println(string);
            }
        };
        f.test("sss");
        Eoo e = new Eoo() {
            @Override
            public void test(String string) {
                System.out.println(string);
            }
        };
        e.test("aaa");
        Foo foo = System.out::println;
        foo.test("aaa");

    }
}
