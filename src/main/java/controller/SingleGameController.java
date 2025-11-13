package controller;

import dto.SizeDto;
import service.SingleGameService;
import view.mode.single.SingleGameView;

public class SingleGameController {

    private static SingleGameController isExist;
    private final SingleGameService gameService;

    private SingleGameController(SingleGameService gameService) {
        this.gameService = gameService;
    }

    public static SingleGameController getInstance() {
        if (isExist == null) {
            isExist = new SingleGameController(new SingleGameService());
        }
        return isExist;
    }

    public void startSingleGame() {
        SizeDto size = gameService.setInitGame();
        new SingleGameView(size);
    }

    public void restartSingleGame() {
        SizeDto size = gameService.handleRestartGame();
        new SingleGameView(size);
    }

    public String transferGridButtonEvent(int x, int y) {
        return gameService.handleGridButtonEvent(x, y);
    }

    public SizeDto transferSizeInputEvent(String sizeInput) {
        return gameService.handleSizeInputEvent(sizeInput);
    }
}
