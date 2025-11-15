package view.init;

import communicator.EventCommunicator;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import view.util.ComponentSetter;

public class InitView extends JFrame {

    private final JLabel titleLabel = new JLabel("⚾ 격자 야구 게임 🧢");
    private final JLabel modeLabel = new JLabel("🕹 모드를 선택하세요 🕹");
    private final JButton singleButton = new JButton("혼자 플레이 👤");
    private final JButton battleButton = new JButton("컴퓨터와 대결 🤖");

    public InitView() {
        setTitle("격자 야구 게임");
        setSize(1500, 900); // 프레임의 크기 설정.
        setResizable(true); // 프레임의 크기 변경 못하게 설정.
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 프레임의 x버튼 누르면 종료;
        setLayout(null);

        InitEventSetter eventSetter = new InitEventSetter(new EventCommunicator(), singleButton, battleButton);

        setComponents();
        eventSetter.setEvents();

        add(titleLabel);
        add(modeLabel);
        add(singleButton);
        add(battleButton);

        setVisible(true);
    }

    private void setComponents() {
        ComponentSetter.setComponent(titleLabel, 500, 100, 550, 100, Font.BOLD, 50, Color.BLACK);
        ComponentSetter.setComponent(modeLabel, 300, 30, 635, 250, Font.BOLD, 25, Color.BLACK);
        ComponentSetter.setComponent(singleButton, 150, 50, 685, 320, Font.BOLD, 15, Color.BLACK);
        ComponentSetter.setComponent(battleButton, 150, 50, 685, 380, Font.BOLD, 15, Color.BLACK);
        singleButton.setFocusPainted(false); // 포커스 하이라이트 숨김
        battleButton.setFocusPainted(false); // 포커스 하이라이트 숨김
    }
}
