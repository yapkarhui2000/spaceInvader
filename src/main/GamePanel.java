package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


import entity.Monster;
import entity.Player;
import collision.CollisionHandler;
import collision.MonsterBulletCollisionHandler;
import collision.PlayerBulletCollisionHandler;
import state.stateHandler;
import state.TitleStateHandler;
import state.playStateHandler;

public class GamePanel extends JPanel{
    // Game Settings
    public final int tileSize = 16;
    final int scale = 3;
    final int screenTile = tileSize * scale;
    final int maxScreenCol = 15;
    final int maxScreenRow = 15;
    final int screenWidth = screenTile * maxScreenCol;
    final int screenHeight =  screenTile * maxScreenRow;
    final int fps =60;


    //Game Logic
    public GameStatusManage gameStatus = new GameStatusManage();
    private GameOver gameOver = new GameOver();
    private GameRestart gameRestart = new GameRestart();
    private PauseGame pauseGame = new PauseGame();
    private KeyHandling keyHandle = new KeyHandling(this);
    Thread gameThread;

    //Renderer
    Renderer renderer = new Renderer();
    //Collision
    private CollisionHandler collisionHandler;
    private stateHandler stateHandler;

    //Entity
    Player player = new Player(this,keyHandle);
    Monster monster = new Monster(this);


    GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandle);
        this.setFocusable(true);
        collisionHandler = new PlayerBulletCollisionHandler();
        collisionHandler.setNextHandler(new MonsterBulletCollisionHandler());
        stateHandler = new TitleStateHandler();
        stateHandler.setNextHandler(new playStateHandler());
    }
    /*
     *To start the game
     */
    public void startGame(){
        gameThread = new Thread(new GameLoop(this, fps));
        gameThread.start();
        gameStatus.setGameState(gameStatus.titleState); // start the game with game title
    }
 
    public Monster getMonster() {
        return monster;
    }
    public Player getPlayer() {
        return player;
    }
    public KeyHandling getKeyHandle() {
        return keyHandle;
    }
    public void restartGame(){
        gameRestart.restartGame(player, monster, gameStatus);
    }
    public void pauseGameTemporarily() {
        pauseGame.pauseGameTemporarily(gameStatus);
    }

    
    public void paintComponent(Graphics graphic){
        super.paintComponent(graphic);

        Graphics2D graphic2 =(Graphics2D)graphic;
        if (gameStatus.isGameOver()){
            renderer.drawGameOverScreen(graphic2, screenWidth, screenHeight);
        }
        else if (gameStatus.isTitle()){
            renderer.drawTitleScreen(graphic2, screenWidth, screenHeight);
        }
        else{
            renderer.drawGameElements(graphic2, player, monster);
        }
        graphic2.dispose();

    }

    public void update(){
        stateHandler.handleState(this);
        if (gameStatus.isPlaying()){
            collisionHandler.handleCollision(this); 
            player.update();
            monster.update();
            if(gameOver.isgameOver(player, monster)){
                gameStatus.setGameState(gameStatus.gameOverState);
            }
        }
    }
}
