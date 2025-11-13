package application;

import controller.BattleGameController;
import controller.GameController;
import controller.SingleGameController;

public class Application {

    public static void main(String[] args) {
        GameController gameController = GameController.getInstance(
                SingleGameController.getInstance(),
                BattleGameController.getInstance()
        );
        gameController.startInitView();
    }
}
