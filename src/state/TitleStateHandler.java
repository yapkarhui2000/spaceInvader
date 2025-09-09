package state;
import main.GamePanel;


public class TitleStateHandler extends stateHandler {
    @Override
    public void handleState(GamePanel gamePanel) {
        if (gamePanel.gameStatus.isTitle() && gamePanel.getKeyHandle().isKeyPressed("S")) {
            gamePanel.gameStatus.setGameState(gamePanel.gameStatus.playState);
        } else if (nextHandler != null) {
            nextHandler.handleState(gamePanel);
        }
    }
}
