package controller;

import dto.SizeDto;
import java.util.List;
import service.BattleGameService;
import view.mode.battle.BattleGameView;

public class BattleGameController {

    private static BattleGameController isExist;
    private final BattleGameService gameService;

    private BattleGameController(BattleGameService gameService) {
        this.gameService = gameService;
    }

    public static BattleGameController getInstance() {
        if (isExist == null) {
            isExist = new BattleGameController(new BattleGameService());
        }
        return isExist;
    }

    public void startBattleGame() {
        SizeDto size = gameService.setInitGame();
        new BattleGameView(size);
    }

    public void restartBattleGame() {
        SizeDto size = gameService.handleRestartGame();
        new BattleGameView(size);
    }

    public String transferUserGridButtonEvent(int x, int y) {
        return gameService.handleUserGridButtonEvent(x, y);
    }

    public void transferComputerGridButtonEvent(int x, int y) {
        gameService.handleComputerGridButtonEvent(x, y);
    }

    public String doRandomlyClickButton(List<Integer> coordinate) {
        return gameService.computeRandomClickResult(coordinate);
    }

    public SizeDto transferSizeInputEvent(String sizeInput) {
        return gameService.handleSizeInputEvent(sizeInput);
    }
}
