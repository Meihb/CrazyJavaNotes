package Annotation_14;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface ActionListenerFor {
    Class<? extends ActionListener> listener();
}

class OkListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
    }
}

public class AnnotationTest {
    private JFrame mainWin = new JFrame("使用注解绑定监听器");
    @ActionListenerFor(listener = OkListener.class)
    private JButton ok = new JButton("确定");
    private JButton cancel = new JButton("取消");

    public void init() {
        JPanel jp = new JPanel();
        jp.add(ok);
        jp.add(cancel);
        mainWin.add(jp);
    }
}
