package Awt_11;

import java.awt.*;

public class PanelTest {
    public static void main(String[] args) {
        Frame f = new Frame("测试窗口");
        Panel p = new Panel();
        p.add(new Button("按钮"));
        p.add(new TextArea("sss"));
        f.add(p);
//        f.setBounds(23, 39, 40, 70);
        f.pack();//自动调整到最佳大小

        f.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));//对齐方式、水平间距、竖直间距

        for (int i = 0; i < 10; i++) {
            f.add(new Button(i + ""));
        }
        f.setVisible(true);

        Frame f2 = new Frame("BorderLayout");
    }
}
