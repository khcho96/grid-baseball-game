package view.mode.battle;

import communicator.EventCommunicator;
import dto.SizeDto;
import javax.swing.JFrame;
import view.mode.battle.panel.BattleGameComputerGridPanel;
import view.mode.battle.panel.BattleGameComputerStatePanel;
import view.mode.battle.panel.BattleGameMainStatePanel;
import view.mode.battle.panel.BattleGameRulePanel;
import view.mode.battle.panel.BattleGameUserGridPanel;
import view.mode.battle.panel.BattleGameUserStatePanel;

public class BattleGameView extends JFrame {

    // panel
    private final BattleGameRulePanel gameRulePanel = new BattleGameRulePanel();
    private final BattleGameMainStatePanel mainStatePanel = new BattleGameMainStatePanel();
    private final BattleGameUserStatePanel userStatePanel = new BattleGameUserStatePanel();
    private final BattleGameUserGridPanel userGridPanel = new BattleGameUserGridPanel();
    private final BattleGameComputerStatePanel computerStatePanel = new BattleGameComputerStatePanel();
    private final BattleGameComputerGridPanel computerGridPanel = new BattleGameComputerGridPanel();

    private final SizeDto size;

    public BattleGameView(SizeDto size) {
        setTitle("격자 야구 게임"); // 프레임 제목 설정.
        setSize(1500, 900); // 프레임의 크기 설정.
        setResizable(true); // 프레임의 크기 변경 못하게 설정.
        setDefaultCloseOperation(EXIT_ON_CLOSE); // 프레임의 x버튼 누르면 종료;
        setLayout(null);

        this.size = size;
        BattleGameEventSetter eventSetter = new BattleGameEventSetter(new EventCommunicator(), size, gameRulePanel,
                mainStatePanel, userStatePanel, userGridPanel, computerStatePanel, computerGridPanel);
        setComponents();
        setPanel();
        eventSetter.setEvents();

        add(gameRulePanel);
        add(mainStatePanel);
        add(userStatePanel);
        add(userGridPanel);
        add(computerStatePanel);
        add(computerGridPanel);

        setVisible(true);
    }

    private void setComponents() {
        gameRulePanel.setRuleComponents();
//        mainStatePanel.setMainStateComponents();
//        userStatePanel.setUserStateComponents();
//        userGridPanel.setUserGridComponents(size);
//        computerStatePanel.setUserStateComponents();
//        computerGridPanel.setUserGridComponents(size);
    }

    private void setPanel() {
        gameRulePanel.setRulePanel();
//        mainStatePanel.setMainStatePanel();
//        userStatePanel.setUserStatePanel();
//        userGridPanel.setUserGridPanel(size);
//        computerStatePanel.setUserStatePanel();
//        computerGridPanel.setUserGridPanel(size);
    }
}
