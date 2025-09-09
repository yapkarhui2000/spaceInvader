package collision;

import main.GamePanel;
import entity.MonsterProjectile;

public class MonsterBulletCollisionHandler extends CollisionHandler {
    @Override
    public void handleCollision(GamePanel gamePanel) {
        MonsterProjectile[] monsterProjectiles = gamePanel.getMonster().getMonsterProjectiles();
        for (int i = 0; i <monsterProjectiles.length; i++) {
            MonsterProjectile projectile = monsterProjectiles[i];
            if (projectile.isActive() && isProjectileHit(gamePanel,projectile)){
                handlePlayerHit(gamePanel,projectile);
                return;
                
            }
        }
        if (nextHandler != null) {
            nextHandler.handleCollision(gamePanel);
        }
    }
    /*
     * player hitbox
     */
    private boolean isProjectileHit(GamePanel gp,MonsterProjectile projectile){
        //adjust player's hitbox
        return projectile.getX() >= gp.getPlayer().x -20&& 
                projectile.getX() <= gp.getPlayer().x +20 &&
                projectile.getY() >= gp.getPlayer().y &&
                projectile.getY() <= gp.getPlayer().y +40;
    }
    /*
     * 
     */
    private void handlePlayerHit(GamePanel gp, MonsterProjectile projectile){
        gp.getPlayer().playerHealth -=1;

        projectile.reset(); //clear projectile from screen

        //check if player's Health reach 0
        if(gp.getPlayer().playerHealth<=0){ 
            gp.gameStatus.setGameState(gp.gameStatus.gameOverState); //change state to game over
        }else{
            gp.pauseGameTemporarily();
            gp.getPlayer().setPlayerValue();
        }

    }
}
    




        