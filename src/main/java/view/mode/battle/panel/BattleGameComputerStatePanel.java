package view.mode.battle.panel;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.util.ComponentSetter;

public class BattleGameComputerStatePanel extends JPanel {

    private int pitchesCount;
    private int outCount;
    private boolean isGameOver;
    private int selectedOutCount;
    private final List<List<Integer>> selectedCoordinates = new ArrayList<>();
    private final JLabel titleLabel = new JLabel("컴퓨터 🤖");
    private final JLabel stateLabel = new JLabel("⚾ 현재 투구 수: " + pitchesCount + "    ⭕ 아웃: " + outCount);

    public int getPitchesCount() {
        return pitchesCount;
    }

    public void setComputerStateComponents() {
        ComponentSetter.setComponent(titleLabel, 550, 30, 215, 15, Font.BOLD, 25, Color.BLACK);
        ComponentSetter.setComponent(stateLabel, 550, 25, 130, 45, Font.BOLD, 20, Color.BLACK);
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

    public boolean isSelectionComplete() {
        return selectedOutCount == 3;
    }

    public void increaseSelectedOutCount() {
        selectedOutCount++;
    }

    public void setComputerStatePanel() {
        ComponentSetter.setPanel(this, 500, 100, 950, 200, null);
        addComponents();
    }

    private void addComponents() {
        add(titleLabel);
        add(stateLabel);
    }

    public void updateState() {
        stateLabel.setText("현재 투구 수: " + pitchesCount + "    아웃: " + outCount);
    }

    public void resetState() {
        pitchesCount = 0;
        outCount = 0;
        updateState();
    }

    public void addSelectedButton(List<Integer> coordinate) {
        selectedCoordinates.add(coordinate);
    }

    public boolean isSelected(List<Integer> coordinate) {
        return selectedCoordinates.contains(coordinate);
    }
}
