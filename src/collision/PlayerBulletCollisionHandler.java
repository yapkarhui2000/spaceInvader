package collision;

import main.GamePanel;
import entity.Projectile;

public class PlayerBulletCollisionHandler extends CollisionHandler {
    @Override
    public void handleCollision(GamePanel gamePanel) {
        Projectile projectile = gamePanel.getPlayer().getProjectile();
        if (projectile != null && !projectile.isOffScreen()) {
            for (int i = 0; i < gamePanel.getMonster().xPositions.length; i++) {
                for (int j = 0; j < gamePanel.getMonster().yPositions.length; j++) {
                    //check if the monster still alive and if it got hit at its position
                    if (gamePanel.getMonster().monsterAlive[i][j] &&
                        projectile.getX() >= gamePanel.getMonster().xPositions[i] && 
                        projectile.getX() <= gamePanel.getMonster().xPositions[i] + 30 &&
                        projectile.getY() >= gamePanel.getMonster().yPositions[j] &&
                        projectile.getY() <= gamePanel.getMonster().yPositions[j] + 40) {
                        
                        // Monster hit
                        gamePanel.getMonster().monsterAlive[i][j] = false;
                        
                        // Reset projectile after it hit one monster
                        projectile.reset(-1, -1);
                        
                        // Update score 
                        if (j == 0) {
                            gamePanel.getPlayer().score += 30; //upper monster
                        } else if (j == 1 || j == 2) {
                            gamePanel.getPlayer().score += 20; //middle monster
                        } else {
                            gamePanel.getPlayer().score += 10; //lower monster
                        }
                        return; // exit after handling collision
                    }
                }
            }
        }


        if (nextHandler != null) {
            nextHandler.handleCollision(gamePanel);
        }
    }
}
