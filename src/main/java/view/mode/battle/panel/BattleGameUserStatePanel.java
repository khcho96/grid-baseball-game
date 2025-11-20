package view.mode.battle.panel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import view.util.ComponentSetter;

public class BattleGameUserStatePanel extends JPanel {

    private int pitchesCount;
    private int outCount;
    private boolean isGameOver = false;
    private final JLabel titleLabel = new JLabel("나");
    private final JLabel stateLabel = new JLabel("⚾ 현재 투구 수: " + pitchesCount + "    ⭕ 아웃: " + outCount);

    public int getPitchesCount() {
        return pitchesCount;
    }

    private void setIcons() {
        ImageIcon human = ComponentSetter.loadIcon(this, "/icon/human.png", 25, 25);

        titleLabel.setIcon(human);
        titleLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        titleLabel.setIconTextGap(5);
    }

    public void setUserStateComponents() {
        setIcons();
        ComponentSetter.setComponent(titleLabel, 550, 30, 250, 15, Font.BOLD, 25, Color.BLACK);
        ComponentSetter.setComponent(stateLabel, 550, 25, 150, 45, Font.BOLD, 20, Color.BLACK);
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOverTrue() {
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

    public void setUserStatePanel() {
        ComponentSetter.setPanel(this, 550, 100, 0, 200, null);
        addComponents();
    }

    private void addComponents() {
        add(titleLabel);
        add(stateLabel);
    }

    public void updateState() {
        stateLabel.setText("⚾ 현재 투구 수: " + pitchesCount + "    ⭕ 아웃: " + outCount);
    }

    public void resetState() {
        pitchesCount = 0;
        outCount = 0;
        isGameOver = false;
        updateState();
    }
}
