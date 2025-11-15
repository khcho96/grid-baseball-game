package view.mode.battle.panel;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import view.util.ComponentSetter;

public class BattleGameMainStatePanel extends JPanel {

    private final BattleGameUserStatePanel battleGameUserStatePanel;
    private final BattleGameComputerStatePanel battleGameComputerStatePanel;

    private final JLabel outZoneSelectLabel = new JLabel("컴퓨터 측 격자칸에서 3개의 아웃 존을 정해주세요");
    private final JLabel gameStartLabel1 = new JLabel("👤 vs 🤖");
    private final JLabel gameStartLabel2 = new JLabel("게임을 시작합니다!");
    private final JLabel userTurnLabel = new JLabel("👤");
    private final JLabel userSelectLabel = new JLabel("내 차례입니다. 칸 하나를 선택하세요");
    private final JLabel computerTurnLabel = new JLabel("🤖");
    private final JLabel computerSelectLabel1 = new JLabel("컴퓨터가 생각하고 있습니다.");
    private final JLabel computerSelectLabel2 = new JLabel("컴퓨터가 생각하고 있습니다..");
    private final JLabel computerSelectLabel3 = new JLabel("컴퓨터가 생각하고 있습니다...");
    private final JLabel resultLabel = new JLabel();
    private final List<String> results = List.of("승리했습니다!! 🎉", "패배했습니다..😭", "비겼습니다 😐");
    private static final int USER_WIN = 0;
    private static final int COMPUTER_WIN = 1;
    private static final int DRAW = 2;

    public BattleGameMainStatePanel(BattleGameUserStatePanel battleGameUserStatePanel, BattleGameComputerStatePanel battleGameComputerStatePanel) {
        this.battleGameUserStatePanel = battleGameUserStatePanel;
        this.battleGameComputerStatePanel = battleGameComputerStatePanel;
    }

    public JLabel getOutZoneSelectLabel() {
        return outZoneSelectLabel;
    }

    public JLabel getGameStartLabel1() {
        return gameStartLabel1;
    }

    public JLabel getGameStartLabel2() {
        return gameStartLabel2;
    }

    public JLabel getUserTurnLabel() {
        return userTurnLabel;
    }

    public JLabel getUserSelectLabel() {
        return userSelectLabel;
    }

    public JLabel getComputerTurnLabel() {
        return computerTurnLabel;
    }

    public JLabel getComputerSelectLabel1() {
        return computerSelectLabel1;
    }

    public JLabel getComputerSelectLabel2() {
        return computerSelectLabel2;
    }

    public JLabel getComputerSelectLabel3() {
        return computerSelectLabel3;
    }

    public void setMainStateComponents() {
        ComponentSetter.setComponent(outZoneSelectLabel, 350, 30, 14, 50, Font.BOLD, 16, Color.BLACK);
        ComponentSetter.setComponent(gameStartLabel1, 350, 30, 125, 15, Font.BOLD, 25, Color.BLACK);
        ComponentSetter.setComponent(gameStartLabel2, 350, 30, 100, 50, Font.BOLD, 20, Color.BLACK);
        ComponentSetter.setComponent(userTurnLabel, 350, 30, 165, 15, Font.BOLD, 25, Color.BLACK);
        ComponentSetter.setComponent(userSelectLabel, 350, 30, 30, 50, Font.BOLD, 20, Color.BLACK);
        ComponentSetter.setComponent(computerTurnLabel, 350, 30, 165, 15, Font.BOLD, 25, Color.BLACK);
        ComponentSetter.setComponent(computerSelectLabel1, 350, 30, 60, 50, Font.BOLD, 20, Color.BLACK);
        ComponentSetter.setComponent(computerSelectLabel2, 350, 30, 60, 50, Font.BOLD, 20, Color.BLACK);
        ComponentSetter.setComponent(computerSelectLabel3, 350, 30, 60, 50, Font.BOLD, 20, Color.BLACK);
        ComponentSetter.setComponent(resultLabel, 350, 30, 80, 35, Font.BOLD, 30, Color.RED);
        setVisibleFalse(gameStartLabel1, gameStartLabel2, userSelectLabel, computerTurnLabel,
                computerSelectLabel1, computerSelectLabel2, computerSelectLabel3, resultLabel);
    }

    public void setMainStatePanel() {
        ComponentSetter.setPanel(this, 350, 100, 570, 325, null);
        Border padded = BorderFactory.createCompoundBorder(
                new LineBorder(new Color(0x0669bf), 2, true), // 색, 두께, 모서리 라운드
                new EmptyBorder(6, 10, 6, 10)                 // 안쪽 여백(위,좌,아,우)
        );
        setBorder(padded);
        setOpaque(true);                 // 배경 칠하려면
        setBackground(Color.WHITE);
        addComponents();
    }

    private void addComponents() {
        add(outZoneSelectLabel);
        add(gameStartLabel1);
        add(gameStartLabel2);
        add(userTurnLabel);
        add(userSelectLabel);
        add(computerTurnLabel);
        add(computerSelectLabel1);
        add(computerSelectLabel2);
        add(computerSelectLabel3);
        add(resultLabel);
    }

    public boolean isGameOver() {
        return battleGameUserStatePanel.isGameOver() || battleGameComputerStatePanel.isGameOver();
    }

    public void setVisibleTrue(JComponent... components) {
        for (JComponent component : components) {
            component.setVisible(true);
        }
    }

    public void setVisibleFalse(JComponent... components) {
        for (JComponent component : components) {
            component.setVisible(false);
        }
    }
}
