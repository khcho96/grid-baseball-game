package main.view.panel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.view.util.ComponentSetter;

public class GameResultPanel extends JPanel {

    private final JLabel resultLabel = new JLabel();

    public void setResultComponents() {
        ComponentSetter.setComponent(resultLabel, 950, 100, 300, 10, Font.BOLD, 30, Color.RED);
    }

    public void setResultPanel() {
        ComponentSetter.setPanel(this, 950, 100, 550, 700, null);
        addComponents();
    }

    private void addComponents() {
        add(resultLabel);
    }

    public void showResult(int pitchesCount) {
        resultLabel.setText("우승입니다!!🏆 투구 수: " + pitchesCount);
        resultLabel.setVisible(true);
    }

    public void disableResultView() {
        resultLabel.setVisible(false);
    }
}
