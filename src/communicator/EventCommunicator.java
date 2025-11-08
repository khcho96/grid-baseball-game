package communicator;

import controller.GameController;

public class EventCommunicator {

    private final GameController gameController;

    public EventCommunicator() {
        this.gameController = GameController.getInstance();
    }

    public String clickGridButton(int x, int y) {
        return gameController.transferGridButtonEvent(x, y);
    }
}
