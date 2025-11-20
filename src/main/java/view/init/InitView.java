package view.init;

import communicator.EventCommunicator;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import view.util.ComponentSetter;

public class InitView extends JFrame {

    private final JLabel titleLabel = new JLabel("⚾ 격자 야구 게임 🧢");
    private final JLabel modeLabel = new JLabel("모드를 선택하세요");
    private final JLabel modeLeftIconLabel = new JLabel();
    private final JLabel modeRightIconLabel = new JLabel();
    private final JButton singleButton = new JButton("혼자 플레이");
    private final JButton battleButton = new JButton("컴퓨터와 대결");

    public InitView() {
        setTitle("격자 야구 게임");
        setSize(1500, 900); // 프레임의 크기 설정.
        setResizable(false); // 프레임의 크기 변경 못하게 설정.
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 프레임의 x버튼 누르면 종료;
        setLayout(null);

        InitEventSetter eventSetter = new InitEventSetter(new EventCommunicator(), singleButton, battleButton);

        setIcons();
        setComponents();
        eventSetter.setEvents();

        add(titleLabel);
        add(modeLabel);
        add(modeLeftIconLabel);
        add(modeRightIconLabel);
        add(singleButton);
        add(battleButton);

        setVisible(true);
    }

    private void setIcons() {
        ImageIcon joystick  = ComponentSetter.loadIcon(this, "/icon/joystick.png", 30, 30);
        ImageIcon human = ComponentSetter.loadIcon(this, "/icon/human.png", 15, 15);
        ImageIcon robot = ComponentSetter.loadIcon(this, "/icon/robot.png", 15, 15);

        modeLeftIconLabel.setIcon(joystick);
        modeLeftIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        modeRightIconLabel.setIcon(joystick);
        modeRightIconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        singleButton.setIcon(human);
        singleButton.setHorizontalTextPosition(SwingConstants.LEFT);
        singleButton.setIconTextGap(5);

        battleButton.setIcon(robot);
        battleButton.setHorizontalTextPosition(SwingConstants.LEFT);
        battleButton.setIconTextGap(5);
    }

    private void setComponents() {
        ComponentSetter.setComponent(titleLabel, 500, 100, 550, 100, Font.BOLD, 50, Color.BLACK);
        ComponentSetter.setComponent(modeLabel, 200, 30, 670, 250, Font.BOLD, 25, Color.BLACK);
        ComponentSetter.setComponent(modeLeftIconLabel, 20, 30, 635, 250, Font.BOLD, 25, Color.BLACK);
        ComponentSetter.setComponent(modeRightIconLabel, 20, 30, 865, 250, Font.BOLD, 25, Color.BLACK);
        ComponentSetter.setComponent(singleButton, 150, 50, 685, 320, Font.BOLD, 15, Color.BLACK);
        ComponentSetter.setComponent(battleButton, 150, 50, 685, 380, Font.BOLD, 15, Color.BLACK);
        singleButton.setFocusPainted(false); // 포커스 하이라이트 숨김
        battleButton.setFocusPainted(false); // 포커스 하이라이트 숨김
    }
}
