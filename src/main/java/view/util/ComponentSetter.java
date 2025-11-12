package view.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import javax.swing.JComponent;
import javax.swing.JPanel;

public final class ComponentSetter {

    private ComponentSetter() {
    }

    public static void setComponent(JComponent component, int width, int height, int x, int y, int fontStyle, int fontSize, Color fontColor) {
        component.setSize(width, height);
        component.setLocation(x, y);
        component.setFont(new Font("돋움", fontStyle, fontSize));
        component.setForeground(fontColor);
    }

    public static void setComponent(JComponent component, int width, int height, int x, int y) {
        component.setSize(width, height);
        component.setLocation(x, y);
    }

    public static void setPanel(JPanel panel, int width, int height, int x, int y, LayoutManager layout) {
        panel.setSize(width, height);
        panel.setLocation(x, y);
        panel.setLayout(layout);
    }
}
