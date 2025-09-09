package main;

public class GameLoop implements Runnable {
    private GamePanel gamePanel;
    private int fps;

    public GameLoop(GamePanel gamePanel, int fps) {
        this.gamePanel = gamePanel;
        this.fps = fps;
    }
       /*
     * Implementation of game loop logic
     */
    @Override
    public void run() {
        double drawInterval = 1000000000/fps; //drawing interval for 60 fps
        double delta =0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (true){
                currentTime =System.nanoTime();
                delta += (currentTime - lastTime) / drawInterval;
                lastTime = currentTime;
                if (delta >=1){
                    gamePanel.update();
                    gamePanel.repaint();
                    delta --;
                    }
                }
            }


}
