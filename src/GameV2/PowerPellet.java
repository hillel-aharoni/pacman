package GameV2;

import javax.swing.*;
import java.awt.*;

public class PowerPellet extends JPanel {
    private boolean isVisible = true;
    private Timer blinkTimer;
    
    public PowerPellet() {
        setPreferredSize(new Dimension(16, 16));
        setBackground(Color.WHITE);
        
        // יצירת טיימר להבהוב כל חצי שניה
        blinkTimer = new Timer(500, e -> {
            isVisible = !isVisible;
            repaint();
        });
        blinkTimer.start();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isVisible) {
            g.setColor(Color.WHITE);
            g.fillOval(0, 0, getWidth(), getHeight());
        }
    }
    
    public void collect() {
        blinkTimer.stop();
        setVisible(false);
    }
}
