package Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.IllegalFormatCodePointException;

public class Key implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int newX = Game.player.getX();
        int newY = Game.player.getY();

        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                newY -= 20;
              break;
            case KeyEvent.VK_DOWN:
                newY += 20;
               break;
            case KeyEvent.VK_LEFT:
                newX -= 20;
               break;
            case KeyEvent.VK_RIGHT:
                newX += 20;
               break;
        }

        //        System.out.println("x = " + newX + " y = " + newY);

        int mapX = newX / 20;
        int mapY = newY / 20;

        if (mapX >= 0 && mapX < D_Map.D_Map1[0].length &&
                mapY >= 0 && mapY < D_Map.D_Map1.length &&
                D_Map.D_Map1[mapY][mapX] != 1){

            Game.player.setLocation(newX, newY);
        }


        if (Game.coins[mapY][mapX] != null){ // בדיקה אם יש מטבע
            // הסרת מטבע מהפאנל
            Game.coins[mapY][mapX].setVisible(false);
            Game.coins[mapY][mapX] = null;
            Game.score ++;
            System.out.println(Game.score);
            if (Game.score == Game.allScore){
                System.out.println("מזל טוווובבב");
            }
        }


        if (newX == 520 && newY == 280){
            Game.player.setLocation(0,280);
        }
        if (newX == 0 && newY == 280){
            Game.player.setLocation(520,280);
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
