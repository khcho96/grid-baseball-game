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

    private final BattleGameRulePanel gameRulePanel = new BattleGameRulePanel();
    private final BattleGameUserStatePanel userStatePanel = new BattleGameUserStatePanel();
    private final BattleGameUserGridPanel userGridPanel = new BattleGameUserGridPanel();
    private final BattleGameComputerStatePanel computerStatePanel = new BattleGameComputerStatePanel();
    private final BattleGameComputerGridPanel computerGridPanel = new BattleGameComputerGridPanel();
    private final BattleGameMainStatePanel mainStatePanel = new BattleGameMainStatePanel(userStatePanel, computerStatePanel);

    private final SizeDto size;

    public BattleGameView(SizeDto size) {
        setTitle("격자 야구 게임");
        setSize(1500, 900);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        mainStatePanel.setMainStateComponents();
        userStatePanel.setUserStateComponents();
        userGridPanel.setUserGridComponents(size);
        computerStatePanel.setComputerStateComponents();
        computerGridPanel.setComputerGridComponents(size);
    }

    private void setPanel() {
        gameRulePanel.setRulePanel();
        mainStatePanel.setMainStatePanel();
        userStatePanel.setUserStatePanel();
        userGridPanel.setUserGridPanel(size);
        computerStatePanel.setComputerStatePanel();
        computerGridPanel.setComputerGridPanel(size);
    }
}
