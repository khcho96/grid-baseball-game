package main.controller;

import main.GameService;
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

    public void gameStart() {
        new GameView();
        gameService.setGame();
    }

    public String transferGridButtonEvent(int x, int y) {
        return gameService.processGridButtonEvent(x, y);
    }
}
