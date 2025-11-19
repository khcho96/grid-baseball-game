package controller;

import view.init.InitView;

public class GameController {

    private final SingleGameController singleGameController;
    private final BattleGameController battleGameController;

    private GameController(SingleGameController singleGameController, BattleGameController battleGameController) {
        this.singleGameController = singleGameController;
        this.battleGameController = battleGameController;
    }

    public static GameController getInstance(SingleGameController singleGameController, BattleGameController battleGameController) {
        return new GameController(singleGameController, battleGameController);
    }

    public void startInitView() {
        new InitView();
    }
}
