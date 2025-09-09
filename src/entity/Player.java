package entity;

import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandling;

public class Player extends Entity{
    private Projectile projectile;
    GamePanel gamePanel;
    KeyHandling keyhandling;

    //variables
    public int x,y;
    public int playerHealth=3;
    public int score = 0;
    
    
    public Player(GamePanel gamePanel,KeyHandling keyHandling){
        this.gamePanel = gamePanel;
        this.keyhandling = keyHandling;
        setPlayerValue();
        getPlayerImage();
        getExplosionImage();
        getAttackImage();
        projectile = new Projectile(x+15, y);

    }
    /*
     * player's default value
     */
    public void setPlayerValue (){
        x = PlayerDefaultValue.startX;
        y = PlayerDefaultValue.StartY;
        bulletSpeed = PlayerDefaultValue.bulletSpeed;
        speed = PlayerDefaultValue.speed;
    }
      /*
     * get player's image
     */
    public void getPlayerImage() {
        try {
            playerImage = ImageIO.read(getClass().getResourceAsStream("/images/player.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * get the bullet image
     */
    public void getAttackImage (){
        try {
            playerAttack = ImageIO.read(getClass().getResourceAsStream("/images/playerbullet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * get the explosion image
     */
    public void getExplosionImage(){
        try {
            explosion = ImageIO.read(getClass().getResourceAsStream("/images/explode.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * player's movement 
     */
    public void playerMovement(){
         if (keyhandling.leftKeyPressed && x >= PlayerDefaultValue.minimumX) {//if its pressed and to keep it not moving out of screen(left)
            x -= speed;
        } else if (keyhandling.rightKeyPressed && x < PlayerDefaultValue.maximumX) {// to keep it not moving out of screen(right)
            x += speed;
        }
    }
    public Projectile getProjectile(){
        return projectile;
    }
    /*
     * player'shoot projectile
     */
    public void playerAttack(){
          if (keyhandling.spacePressed && !projectile.isActive()){
            projectile.reset(x+15, y);
        }
    }
    /*
     * player's bullet movement
     */
    public void playerBulletMovement(){
        if(!projectile.isOffScreen() && projectile.isActive()){
            projectile.move(); // get the bullet to move along y-axis with default bullet speed
        }
    }
    /*
     * update the player's action
     */
    public void update(){
        playerMovement();
        playerAttack();
        playerBulletMovement();
    }
}
   