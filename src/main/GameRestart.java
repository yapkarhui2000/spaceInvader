package main;

import entity.Player;
import entity.Monster;

public class GameRestart {
    /*
     * restart the game when player press r at game over screen
     */
    public void restartGame(Player player,Monster monster,GameStatusManage gameStatus){
        player.playerHealth =3;
        player.score =0;
        player.setPlayerValue();
        monster.resetMonsters();
        gameStatus.setGameState(gameStatus.playState);
    }
}
