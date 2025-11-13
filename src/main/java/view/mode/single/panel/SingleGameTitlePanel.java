package view.mode.single.panel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.util.ComponentSetter;

public class SingleGameTitlePanel extends JPanel {

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
