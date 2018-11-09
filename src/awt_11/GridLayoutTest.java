package awt_11;

import java.awt.*;

public class GridLayoutTest {
    public static void main(String[] args) {
        Frame f = new Frame("计算器");

        f.setLayout(new BorderLayout(30, 15));
        Panel p1 = new Panel();
        Panel p2 = new Panel();

        p1.add(new TextField(30));
        String[] names = new String[]{
                "+", "-", "*", "/", "="
        };
        p2.setLayout(new GridLayout(3, 5, 15, 8));
        for (int i = 0; i < 10; i++) {
            p2.add(new Button(i + ""));
        }
        for (int i = 0; i < names.length; i++) {
            p2.add(new Button(names[i]));
        }

        f.add(p1, BorderLayout.NORTH);
        f.add(p2, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
    }
}
