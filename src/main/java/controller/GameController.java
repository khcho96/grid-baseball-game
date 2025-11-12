package controller;

import dto.SizeDto;
import service.GameService;
import view.GameView;

public class GameController {

    private static GameController isExist;
    private final GameService gameService;

    private GameController(GameService gameService) {
        this.gameService = gameService;
    }

    public static GameController getInstance() {
        if (isExist == null) {
            isExist = new GameController(new GameService());
        }
        return isExist;
    }

    public void startGame() {
        SizeDto size = gameService.setInitGame();
        new GameView(size);
    }

    public void restartGame() {
        SizeDto size = gameService.handleRestartGame();
        new GameView(size);
    }

    public String transferGridButtonEvent(int x, int y) {
        return gameService.handleGridButtonEvent(x, y);
    }

    public SizeDto transferSizeInputEvent(String sizeInput) {
        return gameService.handleSizeInputEvent(sizeInput);
    }
}
