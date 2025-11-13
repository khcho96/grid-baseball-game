package communicator;

import controller.SingleGameController;
import dto.SizeDto;

public class EventCommunicator {

    private final SingleGameController singleGameController;
//    private final BattleGameController battleGameController;

    public EventCommunicator() {
        singleGameController = SingleGameController.getInstance();
//        battleGameController = SingleGameController.getInstance();
    }

    public void selectSingleGame() {
        singleGameController.startSingleGame();
    }

    public void selectBattleGame() {
//        battleGameController.startBattleGame();
    }

    public String clickGridButton(int x, int y) {
        return singleGameController.transferGridButtonEvent(x, y);
    }

    public SizeDto inputSizeInText(String sizeInput) {
        return singleGameController.transferSizeInputEvent(sizeInput);
    }

    public void clickRestartButton() {
        singleGameController.restartSingleGame();
    }
}
