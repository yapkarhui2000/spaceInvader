package state;
import main.GamePanel;
public abstract class stateHandler {
    protected stateHandler nextHandler;

    public void setNextHandler(stateHandler handler) {
        this.nextHandler = handler;
    }

    public abstract void handleState(GamePanel gamePanel);
}
