package GameV2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Player extends JPanel {

    private ImageIcon gifIcon;
    private int gifWidth;
    private int gifHeight;
    private boolean isPowered = false;
    private Timer powerTimer;

    public Player() {
        setBounds(260, 460, 40, 40); // התאמת הגבולות לגודל הרצוי
        setOpaque(false);
        loadImage("C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\right.gif");
        gifWidth = 20;  // רוחב מותאם
        gifHeight = 20; // גובה מותאם
    }

    public void loadImage(String path) {
        gifIcon = new ImageIcon(path);
    }

    public void activatePowerMode() {
        isPowered = true;
        if (powerTimer != null) {
            powerTimer.stop();
        }
        
        // הפעלת מצב כוח ל-4 שניות
        powerTimer = new Timer(4000, e -> {
            isPowered = false;
            ((Timer)e.getSource()).stop();
        });
        powerTimer.setRepeats(false);
        powerTimer.start();
    }

    public boolean isPowered() {
        return isPowered;
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