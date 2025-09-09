package main;

public class PauseGame {
    public void pauseGameTemporarily(GameStatusManage gameStatus) {
        gameStatus.setGameState(gameStatus.pauseState);
    
        // Schedule a task to resume the game after 1 second
        new javax.swing.Timer(1000, e -> {
            if (!gameStatus.isGameOver()) {
                gameStatus.setGameState(gameStatus.playState);
            }
        }).start();
    }
}
