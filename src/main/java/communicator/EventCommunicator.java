package communicator;

import controller.GameController;
import dto.SizeDto;

public class EventCommunicator {

    private final GameController gameController;

    public EventCommunicator() {
        gameController = GameController.getInstance();
    }

    public String clickGridButton(int x, int y) {
        return gameController.transferGridButtonEvent(x, y);
    }

    public SizeDto inputSizeInText(String sizeInput) {
        return gameController.transferSizeInputEvent(sizeInput);
    }

    public void clickRestartButton() {
        gameController.restartGame();
    }
}
