package main.view;

import java.awt.Color;
import main.communicator.EventCommunicator;

import java.awt.Font;
import javax.swing.JFrame;
import main.dto.SizeDto;
import main.view.panel.GameGridPanel;
import main.view.panel.GameResultPanel;
import main.view.panel.GameRulePanel;
import main.view.panel.GameStatePanel;
import main.view.panel.GameTitlePanel;

public class GameView extends JFrame {

    // panel
    private final GameRulePanel gameRulePanel = new GameRulePanel();
    private final GameTitlePanel gameTitlePanel = new GameTitlePanel();
    private final GameStatePanel gameStatePanel = new GameStatePanel();
    private final GameGridPanel gameGridPanel = new GameGridPanel();
    private final GameResultPanel gameResultPanel = new GameResultPanel();

    private final EventCommunicator eventCommunicator = new EventCommunicator();
    private final SizeDto size;
    private final EventSetter eventSetter;

    public GameView(SizeDto size) {
        setTitle("격자 야구 게임"); // 프레임 제목 설정.
        setSize(1500, 900); // 프레임의 크기 설정.
        setResizable(true); // 프레임의 크기 변경 못하게 설정.
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 프레임의 x버튼 누르면 종료;

        setLayout(null);

        this.size = size;
        eventSetter = new EventSetter(eventCommunicator, size, gameRulePanel,
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

    private void setEvents() {

    }
}
