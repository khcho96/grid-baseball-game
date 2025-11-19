package view.mode.single;

import communicator.EventCommunicator;

import javax.swing.JFrame;
import dto.SizeDto;
import view.mode.single.panel.SingleGameGridPanel;
import view.mode.single.panel.SingleGameResultPanel;
import view.mode.single.panel.SingleGameRulePanel;
import view.mode.single.panel.SingleGameStatePanel;

public class SingleGameView extends JFrame {

    private final SingleGameRulePanel gameRulePanel = new SingleGameRulePanel();
    private final SingleGameStatePanel gameStatePanel = new SingleGameStatePanel();
    private final SingleGameGridPanel gameGridPanel = new SingleGameGridPanel();
    private final SingleGameResultPanel gameResultPanel = new SingleGameResultPanel();

    private final SizeDto size;

    public SingleGameView(SizeDto size) {
        setTitle("격자 야구 게임"); // 프레임 제목 설정.
        setSize(1500, 900); // 프레임의 크기 설정.
        setResizable(false); // 프레임의 크기 변경 못하게 설정.
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 프레임의 x버튼 누르면 종료;
        setLayout(null);

        this.size = size;
        SingleGameEventSetter eventSetter = new SingleGameEventSetter(new EventCommunicator(), size, gameRulePanel,
                gameStatePanel, gameGridPanel, gameResultPanel);
        setComponents();
        setPanel();
        eventSetter.setEvents();

        add(gameRulePanel);
        add(gameStatePanel);
        add(gameGridPanel);
        add(gameResultPanel);

        setVisible(true);
    }

    private void setComponents() {
        gameRulePanel.setRuleComponents();
        gameStatePanel.setStateComponents();
        gameGridPanel.setGridComponents(size);
        gameResultPanel.setResultComponents();
    }

    private void setPanel() {
        gameRulePanel.setRulePanel();
        gameStatePanel.setStatePanel();
        gameGridPanel.setGridPanel(size);
        gameResultPanel.setResultPanel();
    }
}
