package GameV2;

import javax.swing.*;
import java.awt.*;

public class Player extends JPanel {

    private ImageIcon gifIcon;
    private int gifWidth;
    private int gifHeight;

    public Player() {
        setBounds(260, 460, 40, 40); // התאמת הגבולות לגודל הרצוי
        setOpaque(false);
        loadImage("C:\\Users\\HOME\\IdeaProjects\\JFrame\\src\\GameV2\\img\\pacman.png");
        gifWidth = 20;  // רוחב מותאם
        gifHeight = 20; // גובה מותאם
    }

    public void loadImage(String path) {
        gifIcon = new ImageIcon(path);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (gifIcon != null) {
            // Draw the scaled GIF
            g.drawImage(gifIcon.getImage(), 0, 0, gifWidth, gifHeight, this);
        }
    }


}