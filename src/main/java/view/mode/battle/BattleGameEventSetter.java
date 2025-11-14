package view.mode.battle;

import communicator.EventCommunicator;
import dto.SizeDto;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import view.mode.battle.panel.BattleGameComputerGridPanel;
import view.mode.battle.panel.BattleGameComputerStatePanel;
import view.mode.battle.panel.BattleGameMainStatePanel;
import view.mode.battle.panel.BattleGameRulePanel;
import view.mode.battle.panel.BattleGameUserGridPanel;
import view.mode.battle.panel.BattleGameUserStatePanel;

public class BattleGameEventSetter {

    private final BattleGameRulePanel battleGameRulePanel;
    private final BattleGameMainStatePanel battleGameMainStatePanel;
    private final BattleGameUserStatePanel battleGameUserStatePanel;
    private final BattleGameUserGridPanel battleGameUserGridPanel;
    private final BattleGameComputerStatePanel battleGameComputerStatePanel;
    private final BattleGameComputerGridPanel battleGameComputerGridPanel;

    private final EventCommunicator eventCommunicator;
    private SizeDto size;

    public BattleGameEventSetter(EventCommunicator eventCommunicator, SizeDto size,
                                 BattleGameRulePanel battleGameRulePanel,
                                 BattleGameMainStatePanel battleGameMainStatePanel,
                                 BattleGameUserStatePanel battleGameUserStatePanel,
                                 BattleGameUserGridPanel battleGameUserGridPanel,
                                 BattleGameComputerStatePanel battleGameComputerStatePanel,
                                 BattleGameComputerGridPanel battleGameComputerGridPanel) {
        this.eventCommunicator = eventCommunicator;
        this.size = size;
        this.battleGameRulePanel = battleGameRulePanel;
        this.battleGameMainStatePanel = battleGameMainStatePanel;
        this.battleGameUserStatePanel = battleGameUserStatePanel;
        this.battleGameUserGridPanel = battleGameUserGridPanel;
        this.battleGameComputerStatePanel = battleGameComputerStatePanel;
        this.battleGameComputerGridPanel = battleGameComputerGridPanel;
    }

    public void setEvents() {
        setEventOfUserGridButtons();

        setEventOfComputerGridButtons();

        battleGameRulePanel.getBackButton().addActionListener(e -> {
            eventCommunicator.clickBackButton();
        });

        battleGameRulePanel.getRestartButton().addActionListener(e -> {
            eventCommunicator.clickBattleGameRestartButton();
        });

//        battleGameRulePanel.getSizeInputTextField().addActionListener(e -> {
//            setEventOfSizeInput();
//        });
//
//        battleGameRulePanel.getSizeInputButton().addActionListener(e -> {
//            setEventOfSizeInput();
//        });
    }

    private void setEventOfUserGridButtons() {
        List<List<JButton>> userButtons = battleGameUserGridPanel.getButtons();
        for (int x = 0; x < size.size(); x++) {
            for (int y = 0; y < size.size(); y++) {
                JButton button = userButtons.get(x).get(y);
                setEventOfUserGridButton(button, x, y);
            }
        }
    }

    private void setEventOfUserGridButton(JButton button, int x, int y) {
        button.addActionListener(e -> {
            if (battleGameMainStatePanel.isGameOver()) {
                return;
            }

            String result = eventCommunicator.clickBattleGameUserGridButton(x, y);

            showResultForEachButton(button, result);

            ShowResultForFinalButton(button);
        });
    }

    private void setEventOfComputerGridButtons() {
        List<List<JButton>> computerButtons = battleGameComputerGridPanel.getButtons();
        for (int x = 0; x < size.size(); x++) {
            for (int y = 0; y < size.size(); y++) {
                JButton button = computerButtons.get(x).get(y);
                setEventOfComputerGridButton(button, x, y);
            }
        }
    }

    private void setEventOfComputerGridButton(JButton button, int x, int y) {
        button.addActionListener(e -> {
            if (battleGameComputerStatePanel.isSelectionComplete()) {
                return;
            }

            eventCommunicator.clickBattleGameComputerGridButton(x, y);
            button.setBackground(new Color(251, 192, 192));
            battleGameComputerStatePanel.increaseSelectedOutCount();

            for (ActionListener actionListener : button.getActionListeners()) {
                button.removeActionListener(actionListener);
            }

            if (battleGameComputerStatePanel.isSelectionComplete()) {

            }
        });
    }

    private void ShowResultForFinalButton(JButton button) {
        if (battleGameUserStatePanel.isMaxOutCount()) {
//            battleGameMainStatePanel.showResult(battleGameUserStatePanel.getPitchesCount());
            battleGameUserStatePanel.setGameOver();
            battleGameUserGridPanel.disableAllGridButtons();
        }
    }

    private void showResultForEachButton(JButton button, String result) {
        button.setText(result);
        button.setForeground(Color.BLUE);
        battleGameUserStatePanel.increasePitchesCount();

        if (result.equals("Out!⚾")) {
            button.setForeground(Color.RED);
            battleGameUserStatePanel.increaseOutCount();
        }

        battleGameUserGridPanel.disableGridButton(button);
        battleGameUserStatePanel.updateState();
    }

    private void setEventOfSizeInput() {
//        changeGridSize();
        battleGameUserGridPanel.setUserGridComponents(size);
        battleGameComputerGridPanel.setComputerGridComponents(size);
        setEventOfUserGridButtons();
        battleGameUserStatePanel.resetState();
        battleGameComputerStatePanel.resetState();
    }
//    private void changeGridSize() {
//        String sizeInput = gameRulePanel.getInputText();
//        try {
//            size = eventCommunicator.inputSizeInText(sizeInput);
//        } catch (IllegalArgumentException error) {
//            gameRulePanel.showErrorMessage(error);
//            return;
//        }
//        gameRulePanel.showSuccessMessage(size.size());

//    }
}
