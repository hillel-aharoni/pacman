package GameV2.monster;

import GameV2.D_Map;
import GameV2.Game;

import javax.swing.*;
import java.awt.*;

public class Pink_Ghost extends JPanel {

    public static final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;

    private ImageIcon gifIcon; // משתנה לאחסון ה-GIF

    private int x = 280;
    private int y = 240;

    public Pink_Ghost(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        setBounds(x, y, Game.width, Game.height);
        setOpaque(false);
        loadGif(imagePath);
    }

    public void randomDir() {
        int direction = (int) (Math.random() * 4) + 1; // בחר כיוון אקראי בין 1 ל-4
        move(direction); // הזז את הרוח הרפאים בכיוון האקראי שנבחר
    }

    public void move(int dir) {
        int currentX = x;
        int currentY = y;
        switch (dir) {
            case UP -> currentY = y - 20;
            case DOWN -> currentY = y +20;
            case RIGHT -> currentX = x + 20;
            case LEFT -> currentX = x - 20;
        }
        if(D_Map.canMove(currentX, currentY)) {
            x = currentX;
            y = currentY;
        }
        setBounds(x, y, Game.width, Game.height);
    }

    public void loadGif(String imagePath) {
        gifIcon = new ImageIcon(imagePath);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gifIcon != null) {
            // ציור ה-GIF באמצעות ImageIcon
            gifIcon.paintIcon(this, g, 0, 0);
        }
    }
}
