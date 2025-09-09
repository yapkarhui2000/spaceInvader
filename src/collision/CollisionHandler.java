package collision;

import main.GamePanel;

public abstract class CollisionHandler {
    protected CollisionHandler nextHandler;

    public void setNextHandler(CollisionHandler handler) {
        this.nextHandler = handler;
    }

    public abstract void handleCollision(GamePanel gamePanel);
}
