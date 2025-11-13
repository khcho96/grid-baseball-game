package view.mode.single;

import communicator.EventCommunicator;

import javax.swing.JFrame;
import dto.SizeDto;
import view.mode.single.panel.SingleGameGridPanel;
import view.mode.single.panel.SingleGameResultPanel;
import view.mode.single.panel.SingleGameRulePanel;
import view.mode.single.panel.SingleGameStatePanel;
import view.mode.single.panel.SingleGameTitlePanel;

public class SingleGameView extends JFrame {

    // panel
    private final SingleGameRulePanel gameRulePanel = new SingleGameRulePanel();
    private final SingleGameTitlePanel gameTitlePanel = new SingleGameTitlePanel();
    private final SingleGameStatePanel gameStatePanel = new SingleGameStatePanel();
    private final SingleGameGridPanel gameGridPanel = new SingleGameGridPanel();
    private final SingleGameResultPanel gameResultPanel = new SingleGameResultPanel();

    private final EventCommunicator eventCommunicator = new EventCommunicator();
    private final SizeDto size;
    private final SingleGameEventSetter eventSetter;

    public SingleGameView(SizeDto size) {
        setTitle("격자 야구 게임"); // 프레임 제목 설정.
        setSize(1500, 900); // 프레임의 크기 설정.
        setResizable(true); // 프레임의 크기 변경 못하게 설정.
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 프레임의 x버튼 누르면 종료;
        setLayout(null);

        this.size = size;
        eventSetter = new SingleGameEventSetter(eventCommunicator, size, gameRulePanel,
                gameStatePanel, gameGridPanel, gameResultPanel);
        setComponents();
        setPanel();
        eventSetter.setEvents();

        add(gameRulePanel);
        add(gameTitlePanel);
        add(gameStatePanel);
        add(gameGridPanel);
        add(gameResultPanel);

        setVisible(true);
    }

    private void setComponents() {
        // rule
        gameRulePanel.setRuleComponents();
        // title
        gameTitlePanel.setTitleComponents();
        // state
        gameStatePanel.setStateComponents();
        // grid
        gameGridPanel.setGridComponents(size);
        // result
        gameResultPanel.setResultComponents();
    }

    private void setPanel() {
        // rule
        gameRulePanel.setRulePanel();
        // title
        gameTitlePanel.setTitlePanel();
        // state
        gameStatePanel.setStatePanel();
        // grid
        gameGridPanel.setGridPanel(size);
        // Result
        gameResultPanel.setResultPanel();
    }
}
