package Swing_12;

import javax.swing.*;
import java.awt.font.FontRenderContext;

public class AllLookAndFeel {
    public static void main(String[] args) {
        System.out.println("当前系统可用的所有LAF:");
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            System.out.println(info.getName()+"--->"+info);
        }
    }
}
