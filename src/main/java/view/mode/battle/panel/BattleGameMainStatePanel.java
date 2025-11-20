package view.mode.battle.panel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import view.util.ComponentSetter;

public class BattleGameMainStatePanel extends JPanel {

    private final BattleGameUserStatePanel battleGameUserStatePanel;
    private final BattleGameComputerStatePanel battleGameComputerStatePanel;

    private final JLabel outZoneSelectLabel = new JLabel("컴퓨터 측 격자칸에서 3개의 아웃 존을 정해주세요");
    private final JLabel gameStartLabel1 = new JLabel("vs");
    private final JLabel leftGameStartLabel1Icon = new JLabel();
    private final JLabel rightGameStartLabel1Icon = new JLabel();
    private final JLabel gameStartLabel2 = new JLabel("게임을 시작합니다!");
    private final JLabel userTurnIcon = new JLabel();
    private final JLabel userSelectLabel = new JLabel("내 차례입니다. 칸 하나를 선택하세요");
    private final JLabel computerTurnIcon = new JLabel();
    private final JLabel computerSelectLabel1 = new JLabel("컴퓨터가 생각하고 있습니다.");
    private final JLabel computerSelectLabel2 = new JLabel("컴퓨터가 생각하고 있습니다..");
    private final JLabel computerSelectLabel3 = new JLabel("컴퓨터가 생각하고 있습니다...");
    private final JLabel userWinResultLabel = new JLabel("승리했습니다!! 🎉");
    private final JLabel computerWinResultLabel = new JLabel("패배했습니다..😭");
    private final JLabel drawResultLabel = new JLabel("비겼습니다 😐");

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

    public JLabel getUserTurnIcon() {
        return userTurnIcon;
    }

    public JLabel getUserSelectLabel() {
        return userSelectLabel;
    }

    public JLabel getComputerTurnIcon() {
        return computerTurnIcon;
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

    public JLabel getLeftGameStartLabel1Icon() {
        return leftGameStartLabel1Icon;
    }

    public JLabel getRightGameStartLabel1Icon() {
        return rightGameStartLabel1Icon;
    }

    private void setIcons() {
        ImageIcon human = ComponentSetter.loadIcon(this, "/icon/human.png", 25, 25);
        ImageIcon robot = ComponentSetter.loadIcon(this, "/icon/robot.png", 25, 25);

        leftGameStartLabel1Icon.setIcon(human);
        leftGameStartLabel1Icon.setHorizontalAlignment(SwingConstants.CENTER);
        rightGameStartLabel1Icon.setIcon(robot);
        rightGameStartLabel1Icon.setHorizontalAlignment(SwingConstants.CENTER);

        userTurnIcon.setIcon(human);
        userTurnIcon.setHorizontalAlignment(SwingConstants.CENTER);
        computerTurnIcon.setIcon(robot);
        computerTurnIcon.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void setMainStateComponents() {
        ComponentSetter.setComponent(outZoneSelectLabel, 350, 30, 14, 50, Font.BOLD, 16, Color.BLACK);
        ComponentSetter.setComponent(gameStartLabel1, 350, 30, 165, 15, Font.BOLD, 25, Color.BLACK);
        ComponentSetter.setComponent(leftGameStartLabel1Icon, 350, 30, -30, 15, Font.BOLD, 25, Color.BLACK);
        ComponentSetter.setComponent(rightGameStartLabel1Icon, 350, 30, 40, 15, Font.BOLD, 25, Color.BLACK);
        ComponentSetter.setComponent(gameStartLabel2, 350, 30, 100, 50, Font.BOLD, 20, Color.BLACK);
        ComponentSetter.setComponent(userTurnIcon, 350, 30, 0, 15, Font.BOLD, 25, Color.BLACK);
        ComponentSetter.setComponent(userSelectLabel, 350, 30, 30, 50, Font.BOLD, 20, Color.BLACK);
        ComponentSetter.setComponent(computerTurnIcon, 350, 30, 0, 15, Font.BOLD, 25, Color.BLACK);
        ComponentSetter.setComponent(computerSelectLabel1, 350, 30, 60, 50, Font.BOLD, 20, Color.BLACK);
        ComponentSetter.setComponent(computerSelectLabel2, 350, 30, 60, 50, Font.BOLD, 20, Color.BLACK);
        ComponentSetter.setComponent(computerSelectLabel3, 350, 30, 60, 50, Font.BOLD, 20, Color.BLACK);
        ComponentSetter.setComponent(userWinResultLabel, 350, 30, 75, 35, Font.BOLD, 30, Color.RED);
        ComponentSetter.setComponent(computerWinResultLabel, 350, 30, 80, 35, Font.BOLD, 30, Color.RED);
        ComponentSetter.setComponent(drawResultLabel, 350, 30, 90, 35, Font.BOLD, 30, Color.RED);
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
        setIcons();
        addComponents();
    }

    private void addComponents() {
        add(outZoneSelectLabel);
        add(gameStartLabel1);
        add(leftGameStartLabel1Icon);
        add(rightGameStartLabel1Icon);
        add(gameStartLabel2);
        add(userTurnIcon);
        add(userSelectLabel);
        add(computerTurnIcon);
        add(computerSelectLabel1);
        add(computerSelectLabel2);
        add(computerSelectLabel3);
        add(userWinResultLabel);
        add(computerWinResultLabel);
        add(drawResultLabel);
        setVisibleFalse(gameStartLabel1, leftGameStartLabel1Icon, rightGameStartLabel1Icon, gameStartLabel2, userSelectLabel, computerTurnIcon,
                computerSelectLabel1, computerSelectLabel2, computerSelectLabel3,
                userWinResultLabel, computerWinResultLabel, drawResultLabel);
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

    public void showWinner() {
        setVisibleFalse(userTurnIcon, userSelectLabel, computerTurnIcon, computerSelectLabel3);

        if (battleGameUserStatePanel.isGameOver() && battleGameComputerStatePanel.isGameOver()) {
            setVisibleTrue(drawResultLabel);
            return;
        }

        if (battleGameUserStatePanel.isGameOver()) {
            setVisibleTrue(userWinResultLabel);
            return;
        }

        if (battleGameComputerStatePanel.isGameOver()) {
            setVisibleTrue(computerWinResultLabel);
        }
    }

    public void restartGame() {
        setVisibleTrue(outZoneSelectLabel, userTurnIcon);
        setVisibleFalse(gameStartLabel1, leftGameStartLabel1Icon, rightGameStartLabel1Icon, gameStartLabel2, userSelectLabel, computerTurnIcon,
                computerSelectLabel1, computerSelectLabel2, computerSelectLabel3,
                userWinResultLabel, computerWinResultLabel, drawResultLabel);
    }
}
