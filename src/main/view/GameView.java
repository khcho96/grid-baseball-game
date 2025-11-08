package main.view;

import main.communicator.EventCommunicator;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import main.application.Application;

public class GameView extends JFrame {

    private final JPanel gameRulePanel = new JPanel();
    private final JPanel gameTitlePanel = new JPanel();
    private final JPanel gameStatePanel = new JPanel();
    private final JPanel gameGridPanel = new JPanel();
    private final JPanel gameResultPanel = new JPanel();

    private final EventCommunicator eventCommunicator = new EventCommunicator();

    // gameRulePanel
    private final List<String> rules = List.of(
            "🚥 게임 규칙 🚥",
            "1. 총 3개의 아웃을 잡아 이닝을 마무리해야 한다.",
            "2. 아웃은 N × N 보드의 N^2개 칸 중 서로 다른 세 칸에 무작위로 배치된다.",
            "3. 칸을 선택하면 아웃 지점에 대한 힌트가 주어진다.",
            "  1) 아웃 지점 선택 시: 아웃",
            "  2) 아웃 지점과 상하좌우로 인접한 칸 선택 시: 스트라이크",
            "  3) 아웃 지점과 대각선으로 인접한 칸 선택 시: 볼",
            "4. 칸을 선택할 때마다 1구씩 증가하며, 최소 투구수로 3아웃을 달성하는 것이 목표다."
    );
    private final List<JLabel> ruleLabels = new ArrayList<>();

    // gameTitlePanel
    private final JLabel titleLabel = new JLabel("⚾ 격자 야구 게임 🧢");

    // gameStatePanel
    private int pitchesCount;
    private int outCount;
    private final JLabel stateLabel = new JLabel("현재 투구 수: " + pitchesCount + "    아웃: " + outCount);
    private final JButton restartButton = new JButton("↩︎Restart");

    // gameGridPanel
    private final List<List<JButton>> gridButtons = new ArrayList<>();

    // gameResultPanel
    private final JLabel resultLabel = new JLabel();

    public GameView() {
        setTitle("격자 야구 게임"); // 프레임 제목 설정.
        setSize(1500, 900); // 프레임의 크기 설정.
        setResizable(true); // 프레임의 크기 변경 못하게 설정.
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 프레임의 x버튼 누르면 종료;

        setLayout(null);

        setComponents();
        setPanel();
        setEvents(); // 이벤트 처리!

        add(gameRulePanel);
        add(gameTitlePanel);
        add(gameStatePanel);
        add(gameGridPanel);
        add(gameResultPanel);

        setVisible(true); // 프레임 보이기;
    }

    private void setComponents() {
        // rule
        for (int i = 0; i < rules.size(); i++) {
            ruleLabels.add(new JLabel(rules.get(i)));

            if (i == 0) {
                ruleLabels.get(i).setSize(550, 30);
                ruleLabels.get(i).setLocation(150, 10);
                ruleLabels.get(i).setFont(new Font("돋움", Font.BOLD, 20));
                continue;
            }

            ruleLabels.get(i).setSize(550, 30);
            ruleLabels.get(i).setLocation(0, 10 + i * 40);
            ruleLabels.get(i).setFont(new Font("돋움", Font.BOLD, 15));
        }

        // title
        titleLabel.setSize(950, 40);
        titleLabel.setLocation(275, 10);
        titleLabel.setFont(new Font("돋움", Font.BOLD, 40));

        // state
        stateLabel.setSize(700, 50);
        stateLabel.setLocation(350, 10);
        stateLabel.setFont(new Font("돋움", Font.PLAIN, 20));
        restartButton.setSize(100, 40);
        restartButton.setLocation(600, 10);
        restartButton.setFont(new Font("돋움", Font.PLAIN, 20));

        // grid
        for (int i = 0; i < 5; i++) {
            gridButtons.add(new ArrayList<>());
            for (int j = 0; j < 5; j++) {
                JButton jButton = new JButton();
                jButton.setFocusPainted(false); // 포커스 하이라이트 숨김
                gridButtons.get(i).add(jButton);
            }
        }

        // result
        resultLabel.setSize(950, 100);
        resultLabel.setLocation(300, 10);
        resultLabel.setFont(new Font("돋움", Font.BOLD, 30));
    }

    private void setPanel() {
        // rule
        gameRulePanel.setSize(550, 900);
        gameRulePanel.setLocation(0, 0);
        gameRulePanel.setLayout(null);
        for (JLabel ruleLabel : ruleLabels) {
            gameRulePanel.add(ruleLabel);
        }

        // title
        gameTitlePanel.setSize(950, 50);
        gameTitlePanel.setLocation(550, 0);
        gameTitlePanel.setLayout(null);
        gameTitlePanel.add(titleLabel);

        // state
        gameStatePanel.setSize(950, 50);
        gameStatePanel.setLocation(550, 50);
        gameStatePanel.setLayout(null);
        gameStatePanel.add(stateLabel);
        gameStatePanel.add(restartButton);

        // grid
        gameGridPanel.setSize(500, 500);
        gameGridPanel.setLocation(750, 100);
        gameGridPanel.setLayout(new GridLayout(5, 5));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                gameGridPanel.add(gridButtons.get(i).get(j));
            }
        }

        // Result
        gameResultPanel.setSize(950, 100);
        gameResultPanel.setLocation(550, 600);
        gameResultPanel.setLayout(null);
        gameResultPanel.add(resultLabel);
    }

    private void setEvents() {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                int finalX = x;
                int finalY = y;
                JButton button = gridButtons.get(x).get(y);
                button.addActionListener(
                        e -> {
                            String result = eventCommunicator.clickGridButton(finalX, finalY);
                            button.setText(result);
                            pitchesCount++;
                            if (result.equals("Out!⚾")) {
                                outCount++;
                            }
                            stateLabel.setText("현재 투구 수: " + pitchesCount + "    아웃: " + outCount);
                            if (outCount == 3) {
                                resultLabel.setText("우승입니다!!🏆 투구 수: " + pitchesCount);
                            }
                        }
                );
            }
        }

        restartButton.addActionListener(
                e -> {
                    Application.main(new String[]{});
                }
        );
    }
}
