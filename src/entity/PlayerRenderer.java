package entity;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;

public class PlayerRenderer {
    private Font arial = new Font("Arial", Font.BOLD, 30);

    /*
     * draw the player's image and projectile
     */
    public void drawPlayer(Graphics2D g2, Player player) {
        //draw player's image
        g2.drawImage(player.playerImage, player.x, player.y, 40, 40, null);
        //draw player's projectile
        Projectile projectile = player.getProjectile();
        if(!projectile.isOffScreen() && projectile.isActive()){
            g2.drawImage(player.playerAttack, projectile.getX(), projectile.getY(), 6, 10, null);
        }
    }
    /*
     * draw the player's health
     */
    public void drawPlayerHealth(Graphics2D g2, Player player) {
        int startX = 600;
        int startY = 0;
        int healthWidth = 40;
        for (int i = 0; i < player.playerHealth; i++) {
            g2.drawImage(player.playerImage, startX + (i * healthWidth), startY, healthWidth, 40, null);
        }
    }
    /*
     * draw player's score
     */
    public void drawPlayerScore(Graphics2D g2, Player player) {
        String text = "SCORES: " + player.score;
        int scoreX = 30;
        int scoreY = 40;
        g2.setFont(arial);
        g2.setColor(Color.WHITE);
        g2.drawString(text, scoreX, scoreY);
    }
    

}
