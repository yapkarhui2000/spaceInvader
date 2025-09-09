package state;

import main.GamePanel;

public class playStateHandler extends stateHandler {
    @Override
    public void handleState(GamePanel gamePanel) {
        if (gamePanel.gameStatus.isGameOver() && gamePanel.getKeyHandle().isKeyPressed("R")) {
            gamePanel.restartGame();
        } else if (nextHandler != null) {
            nextHandler.handleState(gamePanel);
        }
    }
}
