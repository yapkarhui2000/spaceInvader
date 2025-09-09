package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;

import entity.Monster;
import entity.Player;
import entity.PlayerRenderer;


public class Renderer {
    private PlayerRenderer playerRenderer = new PlayerRenderer();

    public void drawTitleScreen(Graphics2D g2, int screenWidth, int screenHeight) {
        String title = "Space Invader";
        String text = "Press S to Start";
        int titleX = (screenWidth - g2.getFontMetrics().stringWidth(title))/5 ;
        int titleY = screenHeight / 2;

        g2.setFont(new Font("Arial", Font.BOLD, 60));
        g2.setColor(Color.GREEN);
        g2.drawString(title, titleX, titleY);
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        g2.setColor(Color.RED);
        g2.drawString(text, titleX, titleY + 70);
    }

    public void drawGameOverScreen(Graphics2D g2, int screenWidth, int screenHeight) {
        String text = "Game Over";
        String text2 = "Press R to Restart";
        int x = (screenWidth - g2.getFontMetrics().stringWidth(text))/5 ;
        int y = screenHeight / 2;

        g2.setFont(new Font("Arial", Font.BOLD, 60)); // For title
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        g2.setColor(Color.RED);
        g2.drawString(text2, x, y + 50);
    }

    public void drawGameElements(Graphics2D g2, Player player, Monster monster) {
        playerRenderer.drawPlayer(g2, player);
        playerRenderer.drawPlayerHealth(g2, player);
        playerRenderer.drawPlayerScore(g2, player);
        monster.drawMonsters(g2);
    }
}
