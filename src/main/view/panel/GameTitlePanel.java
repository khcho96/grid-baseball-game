package main.view.panel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.view.util.ComponentSetter;

public class GameTitlePanel extends JPanel {

    private final JLabel titleLabel = new JLabel("⚾ 격자 야구 게임 🧢");

    public void setTitleComponents() {
        ComponentSetter.setComponent(titleLabel, 950, 40, 275, 10, Font.BOLD,40, Color.BLACK);
    }

    public void setTitlePanel() {
        ComponentSetter.setPanel(this, 950, 50, 550, 0, null);
        addComponents();
    }

    private void addComponents() {
        add(titleLabel);
    }
}
