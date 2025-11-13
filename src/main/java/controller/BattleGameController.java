package controller;

import dto.SizeDto;
import service.GameService;
import view.mode.single.SingleGameView;

public class BattleGameController {

    private static BattleGameController isExist;
    private final GameService gameService;

    private BattleGameController(GameService gameService) {
        this.gameService = gameService;
    }

    public static BattleGameController getInstance() {
        if (isExist == null) {
            isExist = new BattleGameController(new GameService());
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
