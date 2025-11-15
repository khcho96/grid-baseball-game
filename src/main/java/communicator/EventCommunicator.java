package communicator;

import application.Application;
import controller.BattleGameController;
import controller.SingleGameController;
import dto.SizeDto;
import java.util.List;

public class EventCommunicator {

    private final SingleGameController singleGameController;
    private final BattleGameController battleGameController;

    public EventCommunicator() {
        singleGameController = SingleGameController.getInstance();
        battleGameController = BattleGameController.getInstance();
    }

    public void selectSingleGame() {
        singleGameController.startSingleGame();
    }

    public void selectBattleGame() {
        battleGameController.startBattleGame();
    }

    public void clickBackButton() {
        Application.main(new String[]{});
    }

    public String clickSingleGameUserGridButton(int x, int y) {
        return singleGameController.transferGridButtonEvent(x, y);
    }

    public String clickBattleGameUserGridButton(int x, int y) {
        return battleGameController.transferUserGridButtonEvent(x, y);
    }

    public void clickBattleGameComputerGridButton(int x, int y) {
        battleGameController.transferComputerGridButtonEvent(x, y);
    }

    public String doRandomlyClickButton(List<Integer> coordinate) {
        return battleGameController.doRandomlyClickButton(coordinate);
    }

    public SizeDto inputSizeInText(String sizeInput) {
        return singleGameController.transferSizeInputEvent(sizeInput);
    }

    public void clickSingleGameRestartButton() {
        singleGameController.restartSingleGame();
    }

    public void clickBattleGameRestartButton() {
        battleGameController.restartBattleGame();
    }
}
