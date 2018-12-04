package Annotation_14;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ActionListenerInstaller {
    //处理注解方法,参数是包含注解的对象
    public static void processAnnotations(Object obj) {
        try {
            Class cl = obj.getClass();
            for (Field field : cl.getFields()) {
                //设置该field free to visit
                field.setAccessible(true);
                //获取注解
                ActionListenerFor a = field.getAnnotation(ActionListenerFor.class);
            }
        } catch (Exception e) {

        }
    }
}
