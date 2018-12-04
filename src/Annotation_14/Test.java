package Annotation_14;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
@interface MyTag {
    String name() default "mhb";

    int age();
}

public class Test {
    @MyTag(age = 12, name = "sss")
    public void info() {

    }

    public static void main(String[] args) {
        try {

            Annotation[] aArray = Class.forName("Annotation_14.Test").getMethod("info").getAnnotations();
            for (Annotation an : aArray
            ) {
                System.out.println("annotation is " + an);

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Test tt = new Test();
        try {
            Annotation[] anArray = tt.getClass().getMethod("info").getAnnotations();
            for (Annotation an : anArray
            ) {
                System.out.println("annotation is " + an);
                if (an instanceof MyTag) {
                    System.out.println("Tag is :" + an);
                    MyTag anTag = (MyTag) an;
                    System.out.println(anTag.name() + "  "+ anTag.age());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
