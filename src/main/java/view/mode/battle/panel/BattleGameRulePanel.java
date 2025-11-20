package view.mode.battle.panel;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import view.util.ComponentSetter;

public class BattleGameRulePanel extends JPanel {

    private final List<String> rules = List.of(
            " 게임 규칙",
            " 1. 마무리 투수인 당신은 3아웃을 잡아 이 이닝을 끝내면, 팀을 우승으로 이끕니다.",
            " 2. 아웃은 N × N 보드의 N^2개 칸 중 서로 다른 세 칸에 무작위로 배치됩니다.",
            " 3. 칸을 선택하면 아웃 지점에 대한 힌트가 주어집니다.",
            "   1) 아웃 지점 선택 시: 아웃",
            "   2) 아웃 지점과 상하좌우로 인접한 칸 선택 시: 스트라이크",
            "   3) 아웃 지점과 대각선으로 인접한 칸 선택 시: 볼",
            "   예) 1S 2B: 상하좌우 인접한 칸에 아웃이 1개, 대각선으로 인접한 칸에 아웃이 2개 존재",
            " 4. 칸을 선택할 때마다 1구씩 증가하며, 컴퓨터보다 먼저 3아웃을 달성하는 것을 목표로 합니다."
    );
    private final List<String> howToPlay = List.of(
            " 게임 진행 방법",
            " 1. 컴퓨터가 잡아야 하는 3개의 아웃 위치를 선택하세요.",
            " 2. 사용자가 잡아야 하는 3개의 아웃 위치는 랜덤으로 정해집니다.",
            " 3. 사용자, 컴퓨터 순으로 번갈아가며 칸을 하나씩 선택합니다.",
            " 4. 먼저 3아웃을 잡는 쪽이 승리합니다."
    );
    private final List<JLabel> ruleLabels = new ArrayList<>();
    private final JLabel leftRuleLabelsIcon = new JLabel();
    private final JLabel rightRuleLabelsIcon = new JLabel();
    private final List<JLabel> howToPlayLabels = new ArrayList<>();
    private final JLabel leftHowToPlayLabelsIcon = new JLabel();
    private final JLabel rightHowToPlayLabelsIcon = new JLabel();
    private final JButton backButton = new JButton("Back ⬅");
    private final JButton restartButton = new JButton("︎Restart");
    private final JLabel sizeInputLabel = new JLabel("⚾ 격자 크기를 입력해주세요.(3~8) 입력 예) 5");
    private final JTextField sizeInputTextField = new JTextField();
    private final JButton sizeInputButton = new JButton("입력");
    private final JLabel sizeInputResultLabel = new JLabel();

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getRestartButton() {
        return restartButton;
    }

    public JTextField getSizeInputTextField() {
        return sizeInputTextField;
    }

    public JButton getSizeInputButton() {
        return sizeInputButton;
    }

    private void setIcons() {
        ImageIcon light = ComponentSetter.loadIcon(this, "/icon/light.png", 20, 20);
        ImageIcon restart = ComponentSetter.loadIcon(this, "/icon/restart.png", 20, 20);

        leftRuleLabelsIcon.setIcon(light);
        leftRuleLabelsIcon.setHorizontalAlignment(SwingConstants.CENTER);
        rightRuleLabelsIcon.setIcon(light);
        rightRuleLabelsIcon.setHorizontalAlignment(SwingConstants.CENTER);

        leftHowToPlayLabelsIcon.setIcon(light);
        leftHowToPlayLabelsIcon.setHorizontalAlignment(SwingConstants.CENTER);
        rightHowToPlayLabelsIcon.setIcon(light);
        rightHowToPlayLabelsIcon.setHorizontalAlignment(SwingConstants.CENTER);

        restartButton.setIcon(restart);
        restartButton.setHorizontalTextPosition(SwingConstants.LEFT);
        restartButton.setIconTextGap(5);
    }

    public void setRuleComponents() {
        setIcons();
        setRuleLabels();
        setHowToPlayLabels();
        ComponentSetter.setComponent(backButton, 120, 40, 1050, 10, Font.PLAIN,20, Color.BLACK);
        ComponentSetter.setComponent(restartButton, 150, 40, 1310, 10, Font.PLAIN,20, Color.BLACK);
        ComponentSetter.setComponent(sizeInputLabel, 500, 20, 1050, 100, Font.BOLD, 18, Color.BLACK);
        ComponentSetter.setComponent(sizeInputTextField, 200, 30, 1050, 130);
        ComponentSetter.setComponent(sizeInputButton, 70, 30, 1250, 130, Font.BOLD,15, Color.BLACK);
        ComponentSetter.setComponent(sizeInputResultLabel, 600, 20, 1050, 170, Font.BOLD,15, Color.BLACK);
    }

    private void setRuleLabels() {
        for (int i = 0; i < rules.size(); i++) {
            ruleLabels.add(new JLabel(rules.get(i)));

            if (i == 0) {
                ComponentSetter.setComponent(ruleLabels.get(i), 100, 20, 200, 10, Font.BOLD,20, Color.BLACK);
                continue;
            }

            ComponentSetter.setComponent(ruleLabels.get(i), 580, 15, 0, 20 + i * 20, Font.BOLD,15, Color.BLACK);
        }
        ComponentSetter.setComponent(leftRuleLabelsIcon, 20, 20, 180, 10, Font.BOLD,20, Color.BLACK);
        ComponentSetter.setComponent(rightRuleLabelsIcon, 20, 20, 290, 10, Font.BOLD,20, Color.BLACK);
    }

    private void setHowToPlayLabels() {
        for (int i = 0; i < howToPlay.size(); i++) {
            howToPlayLabels.add(new JLabel(howToPlay.get(i)));

            if (i == 0) {
                ComponentSetter.setComponent(howToPlayLabels.get(i), 150, 20, 700, 10, Font.BOLD,20, Color.BLACK);
                continue;
            }

            ComponentSetter.setComponent(howToPlayLabels.get(i), 400, 15, 600, 20 + i * 20, Font.BOLD,15, Color.BLACK);
        }
        ComponentSetter.setComponent(leftHowToPlayLabelsIcon, 20, 20, 680, 10, Font.BOLD,20, Color.BLACK);
        ComponentSetter.setComponent(rightHowToPlayLabelsIcon, 20, 20, 830, 10, Font.BOLD,20, Color.BLACK);
    }

    public void setRulePanel() {
        ComponentSetter.setPanel(this,1500, 200, 0, 0, null);
        addComponents();
    }

    private void addComponents() {
        for (JLabel ruleLabel : ruleLabels) {
            add(ruleLabel);
        }
        add(leftRuleLabelsIcon);
        add(rightRuleLabelsIcon);
        for (JLabel howToPlayLabel : howToPlayLabels) {
            add(howToPlayLabel);
        }
        add(leftHowToPlayLabelsIcon);
        add(rightHowToPlayLabelsIcon);
        add(backButton);
        add(restartButton);
        add(sizeInputLabel);
        add(sizeInputTextField);
        add(sizeInputButton);
        add(sizeInputResultLabel);
    }

    public String getInputText() {
        return sizeInputTextField.getText();
    }

    public void showErrorMessage(Exception error) {
        sizeInputResultLabel.setText(error.getMessage());
        sizeInputResultLabel.setForeground(Color.RED);
        sizeInputResultLabel.setVisible(true);
    }

    public void showSuccessMessage(int size) {
        sizeInputResultLabel.setText(size + "을 입력하셨습니다.");
        sizeInputResultLabel.setForeground(Color.BLUE);
        sizeInputResultLabel.setVisible(true);
    }
}
