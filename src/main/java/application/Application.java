package application;

import controller.GameController;

public class Application {

    public static void main(String[] args) {
        GameController.getInstance().startGame();
    }
}
