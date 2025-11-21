package view.mode.single;

import constant.Constant;
import java.awt.Color;
import java.util.List;
import javax.swing.JButton;
import communicator.EventCommunicator;
import dto.SizeDto;
import view.mode.single.panel.SingleGameGridPanel;
import view.mode.single.panel.SingleGameResultPanel;
import view.mode.single.panel.SingleGameRulePanel;
import view.mode.single.panel.SingleGameStatePanel;

public class SingleGameEventSetter {

    private final SingleGameRulePanel gameRulePanel;
    private final SingleGameStatePanel gameStatePanel;
    private final SingleGameGridPanel gameGridPanel;
    private final SingleGameResultPanel gameResultPanel;

    private final EventCommunicator eventCommunicator;
    private SizeDto size;

    public SingleGameEventSetter(EventCommunicator eventCommunicator, SizeDto size, SingleGameRulePanel gameRulePanel,
                                 SingleGameStatePanel gameStatePanel, SingleGameGridPanel gameGridPanel, SingleGameResultPanel gameResultPanel) {
        this.eventCommunicator = eventCommunicator;
        this.size = size;
        this.gameRulePanel = gameRulePanel;
        this.gameStatePanel = gameStatePanel;
        this.gameGridPanel = gameGridPanel;
        this.gameResultPanel = gameResultPanel;
    }

    public void setEvents() {
        setEventOfGridButtons();

        gameStatePanel.getBackButton().addActionListener(e -> {
            eventCommunicator.clickBackButton();
        });

        gameStatePanel.getRestartButton().addActionListener(e -> {
            eventCommunicator.clickSingleGameRestartButton();
        });

        gameRulePanel.getSizeInputTextField().addActionListener(e -> {
            setEventOfSizeInput();
        });

        gameRulePanel.getSizeInputButton().addActionListener(e -> {
            setEventOfSizeInput();
        });
    }

    private void setEventOfSizeInput() {
        changeGridSize();
        gameGridPanel.setGridComponents(size);
        gameGridPanel.setGridPanel(size);
        setEventOfGridButtons();
        gameStatePanel.resetState();
        gameResultPanel.disableResultView();
    }

    private void setEventOfGridButtons() {
        List<List<JButton>> buttons = gameGridPanel.getButtons();
        for (int x = 0; x < size.size(); x++) {
            for (int y = 0; y < size.size(); y++) {
                JButton button = buttons.get(x).get(y);
                setEventOfGridButton(button, x, y);
            }
        }
    }

    private void setEventOfGridButton(JButton button, int x, int y) {
        button.addActionListener(e -> {
            if (gameStatePanel.isGameOver()) {
                return;
            }

            String result = eventCommunicator.clickSingleGameUserGridButton(x, y);

            showResultForEachButton(button, result);

            ShowResultForFinalButton(button);
        });
    }

    private void ShowResultForFinalButton(JButton button) {
        if (gameStatePanel.isMaxOutCount()) {
            gameResultPanel.showResult(gameStatePanel.getPitchesCount());
            gameStatePanel.setGameOver();
            gameGridPanel.disableAllGridButtons();
        }

    }

    private void showResultForEachButton(JButton button, String result) {
        button.setText(result);
        button.setForeground(Color.BLUE);
        gameStatePanel.increasePitchesCount();

        if (result.equals(Constant.OUT_MESSAGE)) {
            button.setForeground(Color.RED);
            gameStatePanel.increaseOutCount();
        }

        gameGridPanel.disableGridButton(button);
        gameStatePanel.updateState();
    }

    private void changeGridSize() {
        String sizeInput = gameRulePanel.getInputText();
        try {
            size = eventCommunicator.inputSizeInTextForSingleGame(sizeInput);
        } catch (IllegalArgumentException error) {
            gameRulePanel.showErrorMessage(error);
            return;
        }
        gameRulePanel.showSuccessMessage(size.size());
    }
}