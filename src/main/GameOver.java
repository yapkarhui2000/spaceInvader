package main;

import entity.Monster;
import entity.Player;

public class GameOver {
     /*
     * game over conditions
     */
    public boolean isgameOver(Player player,Monster monster){
        // player lose if monster reach y=620
        for(int i =0; i<monster.xPositions.length;i++){
            for(int j=0;j<monster.yPositions.length;j++){
                if(monster.monsterAlive[i][j] && monster.yPositions[j]>=620){ //check if monsters reach y at 620;
                    return true;
                }
            }
        }
        //if the player's Health reaches 0
        if(player.playerHealth <=0){
            return true;
        }
        return false;
    }
}
