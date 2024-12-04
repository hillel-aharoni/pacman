package GameV2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKeyListenerV2 implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                Game.player.loadImage("C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\up.gif");
                Game.player.setDirection(KeyEvent.VK_UP);
                break;
            case KeyEvent.VK_DOWN:
                Game.player.loadImage("C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\down.gif");
                Game.player.setDirection(KeyEvent.VK_DOWN);
                break;
            case KeyEvent.VK_LEFT:
                Game.player.loadImage("C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\left.gif");
                Game.player.setDirection(KeyEvent.VK_LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                Game.player.loadImage("C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\right.gif");
                Game.player.setDirection(KeyEvent.VK_RIGHT);
                break;

        }

        // בדיקה אם יש נקודת כוח במיקום הנוכחי
        int mapX=Game.player.getX()/20;
        int mapY =Game.player.getY()/20;
        if (Game.powerPellets[mapY][mapX] != null && !Game.powerPellets[mapY][mapX].isCollected()) {
            Game.powerPellets[mapY][mapX].collect();
        }

        // בדיקה אם יש מטבע במיקום הנוכחי
        if (Game.coins[mapY][mapX] != null) {
            // הסרת מטבע מהמסך
            Game.coins[mapY][mapX].setVisible(false);
            Game.coins[mapY][mapX] = null;
            Game.score++;


            Massages.scoreLabel.setText("ניקוד :" + Game.score);

            System.out.println(Game.score);
            if (Game.score == Game.allScore){
                Massages.victory();
            }
        }

        if (Game.player.getX() == 520 && Game.player.getY() == 280) {
            Game.player.setLocation(0, 280);
        }
        if (Game.player.getX() == 0 && Game.player.getY() == 280) {
            Game.player.setLocation(520, 280);

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}