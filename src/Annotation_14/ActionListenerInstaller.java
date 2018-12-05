package Annotation_14;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ActionListenerInstaller {
    //处理注解方法,参数是包含注解的对象
    public static void processAnnotations(Object obj) {
        try {
            Class cl = obj.getClass();
            //getFields()：获得某个类的所有的公共（public）的字段，包括父类中的字段。
            //getDeclaredFields()：获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
            for (Field field : cl.getDeclaredFields()) {
                System.out.println("field is " + field);
                //设置该field free to visit
                field.setAccessible(true);
                //获取注解
                ActionListenerFor alf = field.getAnnotation(ActionListenerFor.class);
                Object fobj = field.get(obj);//类似php中object.field,为了防止无法访问才设置的accessible
                if (fobj != null && alf != null && fobj instanceof JButton) { //确为次注解、确为非空field、确为JButton
                    //获取listener
                    Class<? extends ActionListener> listenerClazz = alf.listener();
                    //使用反射来创建此类的对象
                    ActionListener al = listenerClazz.getDeclaredConstructor().newInstance();
                    AbstractButton ab = (AbstractButton) fobj;
                    //为JButton添加事件监听器
                    ab.addActionListener(al);
                }
            }
        } catch (Exception e) {
            System.out.println("error!");
            System.out.println(e.getMessage());
        }
    }
}
