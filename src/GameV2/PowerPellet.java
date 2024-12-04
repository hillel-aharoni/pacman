package GameV2;

import javax.swing.*;
import java.awt.*;

public class PowerPellet extends JPanel {
    private boolean isVisible = true;
    private boolean isCollected = false;
    private Timer blinkTimer;
    
    public PowerPellet() {
        setPreferredSize(new Dimension(16, 16));
        setBackground(Color.BLACK);
        setOpaque(false);
        
        // יצירת טיימר להבהוב כל חצי שניה
        blinkTimer = new Timer(500, e -> {
            if (!isCollected) {
                isVisible = !isVisible;
                repaint();
            }
        });
        blinkTimer.start();
    }
    
    public PowerPellet(int x, int y) {
        this();
        setBounds(x, y, 16, 16);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isVisible && !isCollected) {
            g.setColor(Color.WHITE);
            int size = Math.min(getWidth(), getHeight()) - 4;
            g.fillOval(2, 2, size, size);
        }
    }
    
    public void collect() {
        isCollected = true;
        isVisible = false;
        Game.score += 50;  // הוספת 50 נקודות
        Game.player.activatePowerMode();  // הפעלת מצב כוח לפקמן

        Massages.scoreLabel.setText("ניקוד : " + Game.score);
        repaint();
    }

    public boolean isCollected() {
        return isCollected;
    }
}
