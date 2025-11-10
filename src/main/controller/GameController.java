package main.controller;

import main.dto.SizeDto;
import main.service.GameService;
import main.view.GameView;

public class GameController {

    private static GameController isExist;
    private final GameService gameService;

    private GameController(main.service.GameService gameService) {
        this.gameService = gameService;
    }

    public static GameController getInstance() {
        if (isExist == null) {
            isExist = new GameController(new main.service.GameService());
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
