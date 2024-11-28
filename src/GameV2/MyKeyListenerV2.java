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
        int newX = Game.player.getX();
        int newY = Game.player.getY();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                Game.player.loadImage("C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\up.gif");
                newY -= 20;
                break;
            case KeyEvent.VK_DOWN:
                Game.player.loadImage("C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\down.gif");
                newY += 20;
                break;
            case KeyEvent.VK_LEFT:
                Game.player.loadImage("C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\left.gif");
                newX -= 20;
                break;
            case KeyEvent.VK_RIGHT:
                Game.player.loadImage("C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\right.gif");
                newX += 20;
                break;

        }
//        System.out.println("x = " + newX + ", y = " + newY);

        int mapX=newX/20;
        int mapY =newY/20;

        // אם זה לא שחור שלא יעלה - כלומר שלא יעלה על הקירות
        if (mapX>= 0 && mapX < D_Map.D_Map[0].length &&
                mapY>= 0 && mapY <D_Map.D_Map.length &&
                D_Map.D_Map[mapY][mapX] != 1 ){

            Game.player.setLocation(newX, newY);
        }
        System.out.println("x="+newX+" y=" +newY);
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

        if (newX == 520 && newY == 280) {
            Game.player.setLocation(0, 280);
        }
        if (newX == 0 && newY == 280) {
            Game.player.setLocation(520, 280);

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {


    }
}