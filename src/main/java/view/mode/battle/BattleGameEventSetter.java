package view.mode.battle;

import communicator.EventCommunicator;
import domain.vo.Coordinate;
import dto.SizeDto;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.Timer;
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

        battleGameRulePanel.getSizeInputTextField().addActionListener(e -> {
            setEventOfSizeInput();
        });

        battleGameRulePanel.getSizeInputButton().addActionListener(e -> {
            setEventOfSizeInput();
        });
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
            if (!battleGameUserGridPanel.isClickable()) {
                return;
            }

            if (battleGameMainStatePanel.isGameOver()) {
                return;
            }

            String userResult = eventCommunicator.clickBattleGameUserGridButton(x, y);
            showResultForEachUserButton(button, userResult);
            ShowResultForFinalUserButton(button);

            battleGameUserGridPanel.setClickable(false);

            battleGameMainStatePanel.setVisibleFalse(battleGameMainStatePanel.getUserTurnIcon(), battleGameMainStatePanel.getUserSelectLabel());
            battleGameMainStatePanel.setVisibleTrue(battleGameMainStatePanel.getComputerTurnIcon(), battleGameMainStatePanel.getComputerSelectLabel1());
            Timer timer1 = new Timer(1000, event1 -> {
                battleGameMainStatePanel.setVisibleFalse(battleGameMainStatePanel.getComputerSelectLabel1());
                battleGameMainStatePanel.setVisibleTrue(battleGameMainStatePanel.getComputerSelectLabel2());

                Timer timer2 = new Timer(1000, event2 -> {
                    battleGameMainStatePanel.setVisibleFalse(battleGameMainStatePanel.getComputerSelectLabel2());
                    battleGameMainStatePanel.setVisibleTrue(battleGameMainStatePanel.getComputerSelectLabel3());

                    Timer timer3 = new Timer(1000, event3 -> {

                        Coordinate coordinate;
                        while (true) {
//                            List<Integer> oldCoordinate = RandomGenerator.generateCoordinate(Size.newInstance(String.valueOf(size.size())));
                            coordinate = eventCommunicator.getSmartCoordinate();
                            if (!battleGameComputerStatePanel.isSelected(coordinate)) {
                                battleGameComputerStatePanel.addSelectedButton(coordinate);
                                break;
                            }
                        }

//                        int randomX = oldCoordinate.get(0);
//                        int randomY = oldCoordinate.get(1);
//                        String computerResult = eventCommunicator.doRandomlyClickButton(oldCoordinate);
                        String computerResult = eventCommunicator.doSmartClickButton(coordinate);

                        JButton selectedButton = battleGameComputerGridPanel.getButtons().get(coordinate.getX()).get(coordinate.getY());
                        selectedButton.doClick(200);
                        showResultForEachComputerButton(selectedButton, computerResult);
                        ShowResultForFinalComputerButton(selectedButton);

                        if (battleGameMainStatePanel.isGameOver()) {
                            battleGameMainStatePanel.showWinner();
                            battleGameUserGridPanel.disableAllGridButtons();
                            return;
                        }

                        battleGameMainStatePanel.setVisibleFalse(
                                battleGameMainStatePanel.getComputerTurnIcon(),
                                battleGameMainStatePanel.getComputerSelectLabel3()
                        );
                        battleGameMainStatePanel.setVisibleTrue(
                                battleGameMainStatePanel.getUserTurnIcon(),
                                battleGameMainStatePanel.getUserSelectLabel()
                        );
                        battleGameUserGridPanel.setClickable(true);
                    });
                    timer3.setRepeats(false); // 한 번만 실행
                    timer3.start();
                });
                timer2.setRepeats(false); // 한 번만 실행
                timer2.start();
            });
            timer1.setRepeats(false); // 한 번만 실행
            timer1.start();
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
            button.setOpaque(true); // 배경색 칠할 수 있게
            button.setContentAreaFilled(true); // 기본 배경 그리기 허용
            battleGameComputerStatePanel.increaseSelectedOutCount();

            for (ActionListener actionListener : button.getActionListeners()) {
                button.removeActionListener(actionListener);
            }

            if (battleGameComputerStatePanel.isSelectionComplete()) {
                battleGameMainStatePanel.setVisibleFalse(battleGameMainStatePanel.getUserTurnIcon(), battleGameMainStatePanel.getOutZoneSelectLabel());
                battleGameMainStatePanel.setVisibleTrue(battleGameMainStatePanel.getGameStartLabel1(), battleGameMainStatePanel.getLeftGameStartLabel1Icon(),
                        battleGameMainStatePanel.getRightGameStartLabel1Icon(),  battleGameMainStatePanel.getGameStartLabel2());

                Timer timer = new Timer(2000, event -> {
                    // 2초 뒤에 하고 싶은 작업
                    battleGameMainStatePanel.setVisibleFalse(battleGameMainStatePanel.getGameStartLabel1(), battleGameMainStatePanel.getLeftGameStartLabel1Icon(),
                            battleGameMainStatePanel.getRightGameStartLabel1Icon(), battleGameMainStatePanel.getGameStartLabel2());
                    battleGameMainStatePanel.setVisibleTrue(battleGameMainStatePanel.getUserTurnIcon(), battleGameMainStatePanel.getUserSelectLabel());
                    battleGameUserGridPanel.setClickable(true);
                });
                timer.setRepeats(false); // 한 번만 실행
                timer.start();
            }
        });
    }

    private void ShowResultForFinalUserButton(JButton button) {
        if (battleGameUserStatePanel.isMaxOutCount()) {
            battleGameUserStatePanel.setGameOverTrue();
            battleGameUserGridPanel.disableAllGridButtons();
        }
    }

    private void showResultForEachUserButton(JButton button, String result) {
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

    private void ShowResultForFinalComputerButton(JButton button) {
        if (battleGameComputerStatePanel.isMaxOutCount()) {
            battleGameComputerStatePanel.setGameOverTrue();
        }
    }

    private void showResultForEachComputerButton(JButton button, String result) {
        button.setText(result);
        button.setForeground(Color.BLUE);
        battleGameComputerStatePanel.increasePitchesCount();

        if (result.equals("Out!⚾")) {
            button.setForeground(Color.RED);
            battleGameComputerStatePanel.increaseOutCount();
        }

        battleGameComputerStatePanel.updateState();
    }

    private void setEventOfSizeInput() {
        try {
            changeGridSize();
        } catch (IllegalArgumentException error) {
            battleGameRulePanel.showErrorMessage(error);
            return;
        }
        battleGameRulePanel.showSuccessMessage(size.size());

        battleGameUserGridPanel.setUserGridComponents(size);
        battleGameUserGridPanel.setUserGridPanel(size);
        battleGameComputerGridPanel.setComputerGridComponents(size);
        battleGameComputerGridPanel.setComputerGridPanel(size);
        setEventOfUserGridButtons();
        setEventOfComputerGridButtons();
        battleGameUserStatePanel.resetState();
        battleGameComputerStatePanel.resetState();
        battleGameMainStatePanel.restartGame();
    }

    private void changeGridSize() {
        String sizeInput = battleGameRulePanel.getInputText();
        size = eventCommunicator.inputSizeInTextForBattleGame(sizeInput);
    }
}
