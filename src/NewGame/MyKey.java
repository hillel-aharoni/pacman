package NewGame;

import Game.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKey implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int newX = MyGame.player.getX();
        int newY = MyGame.player.getY();

        switch (e.getKeyCode()) {
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

        int mapX = newX / 20;
        int mapY = newY / 20;

        if (mapX >= 0 && mapX < MyD_map.D_map1[0].length &&
                mapY >= 0 && mapY < MyD_map.D_map1.length &&
                MyD_map.D_map1[mapY][mapX] != 1) {

            MyGame.player.setLocation(newX, newY);
        }


        if (MyGame.coins[mapY][mapX] != null){
            MyGame.coins[mapY][mapX].setVisible(false);
            MyGame.coins[mapY][mapX] = null;
            MyGame.score ++;
            System.out.println(MyGame.score);
            if (MyGame.score == MyGame.allScore){
                System.out.println("מזל טוווובבב");
            }
        }

        if (newX == 520 && newY == 280){
            MyGame.player.setLocation(0,280);
        }
        if (newX == 0 && newY == 280){
            MyGame.player.setLocation(520,280);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
