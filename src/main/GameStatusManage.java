package main;

public class GameStatusManage {
    private int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;

    public GameStatusManage() {
        this.gameState = titleState; // Default to title state
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int state) {
        this.gameState = state;
    }

    public boolean isGameOver() {
        return gameState == gameOverState;
    }

    public boolean isPlaying() {
        return gameState == playState;
    }

    public boolean isPaused() {
        return gameState == pauseState;
    }

    public boolean isTitle() {
        return gameState == titleState;
    }
}
