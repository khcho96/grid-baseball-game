package view.mode.single.panel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.util.ComponentSetter;

public class SingleGameStatePanel extends JPanel {

    private int pitchesCount;
    private int outCount;
    private boolean isGameOver = false;
    private final JLabel stateLabel = new JLabel("현재 투구 수: " + pitchesCount + "    아웃: " + outCount);
    private final JButton restartButton = new JButton("↩︎Restart");

    public JButton getRestartButton() {
        return restartButton;
    }

    public int getPitchesCount() {
        return pitchesCount;
    }

    public void setStateComponents() {
        ComponentSetter.setComponent(stateLabel, 700, 50, 350, 10, Font.PLAIN,20, Color.BLACK);
        ComponentSetter.setComponent(restartButton, 100, 40, 650, 10, Font.PLAIN,20, Color.BLACK);
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver() {
        isGameOver = true;
    }

    public void increasePitchesCount() {
        pitchesCount++;
    }

    public void increaseOutCount() {
        outCount++;
    }

    public boolean isMaxOutCount() {
        return outCount == 3;
    }

    public void setStatePanel() {
        ComponentSetter.setPanel(this, 950, 50, 550, 50, null);
        addComponents();
    }

    private void addComponents() {
        add(stateLabel);
        add(restartButton);
    }

    public void updateState() {
        stateLabel.setText("현재 투구 수: " + pitchesCount + "    아웃: " + outCount);
    }

    public void resetState() {
        pitchesCount = 0;
        outCount = 0;
        isGameOver = false;
        updateState();
    }
}
