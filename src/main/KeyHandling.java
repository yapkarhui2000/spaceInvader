package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandling implements KeyListener {
    
    public boolean leftKeyPressed, rightKeyPressed,spacePressed,restartPressed,startPressed;
    GamePanel gp;

    public KeyHandling(GamePanel gp){
        this.gp =gp;
    }
    public boolean isKeyPressed(String key) {
        switch (key.toUpperCase()) {
            case "LEFT": return leftKeyPressed;
            case "RIGHT": return rightKeyPressed;
            case "SPACE": return spacePressed;
            case "S": return startPressed;
            case "R": return restartPressed;
            default: return false;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            leftKeyPressed = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightKeyPressed = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            spacePressed = true;
        }
        if (key == KeyEvent.VK_R) {
            restartPressed = true;
        }
        
        if (key == KeyEvent.VK_S) {
            startPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            leftKeyPressed = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            rightKeyPressed = false;
        }
        if (key == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        if (key == KeyEvent.VK_R) {
            restartPressed = false;
        }
        if (key == KeyEvent.VK_S) {
            startPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }


}
