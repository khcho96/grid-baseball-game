package main.view;

import java.awt.Color;
import java.util.List;
import javax.swing.JButton;
import main.communicator.EventCommunicator;
import main.dto.SizeDto;
import main.view.panel.GameGridPanel;
import main.view.panel.GameResultPanel;
import main.view.panel.GameRulePanel;
import main.view.panel.GameStatePanel;

public class EventSetter {

    private final GameRulePanel gameRulePanel;
    private final GameStatePanel gameStatePanel;
    private final GameGridPanel gameGridPanel;
    private final GameResultPanel gameResultPanel;

    private final EventCommunicator eventCommunicator;
    private SizeDto size;

    public EventSetter(EventCommunicator eventCommunicator, SizeDto size, GameRulePanel gameRulePanel,
                       GameStatePanel gameStatePanel, GameGridPanel gameGridPanel, GameResultPanel gameResultPanel) {
        this.eventCommunicator = eventCommunicator;
        this.size = size;
        this.gameRulePanel = gameRulePanel;
        this.gameStatePanel = gameStatePanel;
        this.gameGridPanel = gameGridPanel;
        this.gameResultPanel = gameResultPanel;
    }

    public void setEvents() {
        setEventOfGridButtons();

        gameStatePanel.getRestartButton().addActionListener(
                e -> {
                    eventCommunicator.clickRestartButton();
                }
        );

        gameRulePanel.getSizeInputTextField().addActionListener(
                e -> {
                    changeGridSize();
                    gameGridPanel.setGridComponents(size);
                    gameGridPanel.setGridPanel(size);
                    setEventOfGridButtons();
                    gameStatePanel.resetState();
                    gameResultPanel.disableResultView();
                }
        );

        gameRulePanel.getSizeInputButton().addActionListener(
                e -> {
                    changeGridSize();
                    gameGridPanel.setGridComponents(size);
                    gameGridPanel.setGridPanel(size);
                    setEventOfGridButtons();
                    gameStatePanel.resetState();
                    gameResultPanel.disableResultView();
                }
        );
    }

    public void setEventOfGridButtons() {
        List<List<JButton>> buttons = gameGridPanel.getButtons();
        for (int x = 0; x < size.size(); x++) {
            for (int y = 0; y < size.size(); y++) {
                int finalX = x;
                int finalY = y;
                JButton button = buttons.get(x).get(y);
                button.addActionListener(
                        e -> {
                            if (gameStatePanel.isGameOver()) {
                                return;
                            }

                            String result = eventCommunicator.clickGridButton(finalX, finalY);

                            button.setText(result);
                            button.setForeground(Color.BLUE);
                            gameStatePanel.increasePitchesCount();

                            if (result.equals("Out!⚾")) {
                                button.setForeground(Color.RED);
                                gameStatePanel.increaseOutCount();
                            }

                            gameStatePanel.updateState();

                            if (gameStatePanel.isMaxOutCount()) {
                                gameResultPanel.showResult(gameStatePanel.getPitchesCount());
                                gameStatePanel.setGameOver();
                                gameGridPanel.disableAllGridButtons();
                            }

                            gameGridPanel.disableGridButton(button);
                        }
                );
            }
        }
    }

    private void changeGridSize() {
        String sizeInput = gameRulePanel.getInputText();
        try {
            size = eventCommunicator.inputSizeInText(sizeInput);
        } catch (IllegalArgumentException error) {
            gameRulePanel.showErrorMessage(error);
            return;
        }
        gameRulePanel.showSuccessMessage(size.size());
    }
}
