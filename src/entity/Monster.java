package entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.util.Random;
import main.GamePanel;


public class Monster extends Entity{

    GamePanel gp;
    Random rand = new Random();

    //variables
    private int monsterSpace = 40;
    private int xOffSet=0;
    private int yOffSet=0;
    private float speed =1;
    private boolean toRight = true;
    public int[] xPositions = new int[12];
    public int[] yPositions = new int[5];
    public boolean [][] monsterAlive;
    private MonsterProjectile [] projectiles;
    private MonsterAnimation animation = new MonsterAnimation();
    private int maxBullets =3;



    public Monster (GamePanel gp){
        this.gp = gp;
        this.projectiles = new MonsterProjectile[maxBullets];
        for(int i=0; i<maxBullets;i++){
            projectiles[i] = new MonsterProjectile();
        }
        getMonsterImage();
        getMonsterAttackImage();
        getExplosionImage();
        xPosition();
        yPosition();
        monsterStatus();

    }
    /*
     * get monster's image
     */
    public void getMonsterImage(){
        try {
             upperMonster1= ImageIO.read(getClass().getResourceAsStream("/images/alien20.1.png"));
             upperMonster2= ImageIO.read(getClass().getResourceAsStream("/images/alien20.png"));
             middleMonster1= ImageIO.read(getClass().getResourceAsStream("/images/alien12.png"));
             middleMonster2= ImageIO.read(getClass().getResourceAsStream("/images/alien12.1.png"));
             lowerMonster1= ImageIO.read(getClass().getResourceAsStream("/images/alien10.1.png"));
             lowerMonster2= ImageIO.read(getClass().getResourceAsStream("/images/alien10.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * get monster's bullet image
     */
    public void getMonsterAttackImage(){
        try{
            monsterAttack1= ImageIO.read(getClass().getResourceAsStream("/images/bullet.1.png"));
            monsterAttack2= ImageIO.read(getClass().getResourceAsStream("/images/bullet.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    /*
     * get explosion image for monster that got hit 
     */
    public void getExplosionImage(){
        try {
            explosion = ImageIO.read(getClass().getResourceAsStream("/images/explode.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * monster's dancing animation
     */
    public void monsterAnimation(){
        frameNum = animation.updatemonsterAnimation();
    }
    /*
     * To set each x position of monsters 
    */
    public void xPosition(){
        for(int i =0;i<xPositions.length;i++){
            xPositions[i]= i* monsterSpace+xOffSet;  //set monster x position and move it with Offset
        }
    }
    /*
     * To set each y position of monsters
     */
    public void yPosition(){
        for(int i=0;i<yPositions.length;i++){
            yPositions[i]= 50 + yOffSet +(i*50); //set monster y position  and move it with offset
        }
    }
    /*
     * To check if the monster still alive
     */
    public void monsterStatus(){
        monsterAlive = new boolean[xPositions.length][yPositions.length]; //get alive monster x and y position
        for(int i =0;i<xPositions.length;i++){ 
            for(int j=0;j< yPositions.length;j++){ 
                monsterAlive[i][j]=true; 
            }
        }
    }
    /*
     * Monster's movement
     */
    public void monsterMovement(){
        if(toRight){  
            xOffSet+=speed; // make monster move to the right
            if(xOffSet>255){
                toRight = false; //change direction for the monster 
                yOffSet +=40; //move the monster down when it hit 255
                speed+=0.15; //increase the speed of the monster
            }
        }else{
            xOffSet-=speed;
            if(xOffSet<0){
                toRight=true; //change direction for the monster
                yOffSet+=40; //move the monster down when it hit 0
                speed+=0.15; //increase the speed of the monster
            }
        }
      
    }
    /*
     * monster's bullet movement
     */
    public void bulletMovement(){
        for(int i=0;i< projectiles.length;i++){
           MonsterProjectile projectile = projectiles[i];
            if(!projectile.isActive()){
                int randomX = xPositions[rand.nextInt(xPositions.length)];
                int startY = yPositions[0] +30; //only upper monster can shoot
                projectile.activate(randomX, startY); // activation of the monster's bullet
            }
            else if (projectile.getY()>=670){ //if the bullet off screen
                projectile.reset(); //destroy the bullet
            }
            else{
                projectile.projectileUpdate(); // control speed of the bullet
            } 
        }
    }
    /*
     * reset the monster back to the default value when player pressed restart 
     */
    public void resetMonsters(){
        //reset position
        xOffSet =0;
        yOffSet =0;
        speed =1;
        //reset monsters position
        xPosition();
        yPosition();

        //return destroyed monster
        for(int i =0;i<xPositions.length;i++){
            for(int j=0;j< yPositions.length;j++){
                monsterAlive[i][j]=true;
            }
        }
        // Deactivate monster bullets
        for (int i = 0; i < projectiles.length; i++) {
            projectiles[i].reset();
        }
    }
    public MonsterProjectile[] getMonsterProjectiles(){
        return projectiles;
    }
    
    
    /*
     * update actions for monsters
     */
    public void update(){
        monsterAnimation();
        monsterMovement();
        bulletMovement();
        xPosition();
        yPosition();
    }
    /*
     * draw the explosion image when monster got hit 
     */
    public void drawExplosion(Graphics2D g2, int x, int y) {
        g2.drawImage(explosion, x, y, 30, 30, null);
    }
    /*
     * draw monster and making animation for monster
     */
    public void drawMonsters(Graphics2D  g2){
        for (int i=0; i<xPositions.length;i++){ //locate monster's x position
            for(int j=0;j<yPositions.length;j++){ //locate monster's y position
                if(monsterAlive[i][j]){ //only draw monster that are alive 
                    if (frameNum==1){  // draw monster first animation
                        if(j ==0){ //upper monster
                            g2.drawImage(upperMonster1,xPositions[i],yPositions[j],30,40,null); 
                        }else if(j ==1 || j ==2){ //middle monster
                            g2.drawImage(middleMonster1,xPositions[i],yPositions[j],30,40,null);
                        }else{//lower
                            g2.drawImage(lowerMonster1,xPositions[i],yPositions[j],30,40,null);
                        }
                    }
                    else if(frameNum==2){ //draw monster second animation
                        if(j==0){//upper monster
                            g2.drawImage(upperMonster2,xPositions[i],yPositions[j],30,40,null);
                        }else if(j ==1 || j ==2){ //middle monster
                            g2.drawImage(middleMonster2,xPositions[i],yPositions[j],30,40,null);
                        }else{ //lower monster
                            g2.drawImage(lowerMonster2,xPositions[i],yPositions[j],30,40,null);
                        }
                    }

                }
            }
            drawMonsterProjectile(g2);
        }
        //draw bullet for monster
    }
    public void drawMonsterProjectile (Graphics2D g2){
        for(int i =0;i<projectiles.length;i++){
            MonsterProjectile projectile = projectiles[i];
            if(projectile.isActive()){
                g2.drawImage(monsterAttack1,projectile.getX(),projectile.getY(),20,20,null);
            }
        }
    }
}
