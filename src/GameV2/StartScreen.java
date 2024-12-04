package GameV2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartScreen extends JPanel {
    private JButton startButton;
    private Timer animationTimer;
    private float hue = 0;
    private final Color TURQUOISE = new Color(64, 224, 208);
    private final Color BRIGHT_YELLOW = new Color(255, 255, 0);
    private final Color BRIGHT_PINK = new Color(255, 20, 147);
    private final Color BRIGHT_ORANGE = new Color(255, 140, 0);
    
    public StartScreen(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(0, 0, 40));  // כחול כהה במקום שחור
        setLayout(null);
        
        // כותרת המשחק
        JLabel titleLabel = new JLabel("PAC-MAN-HILLEL");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 42));
        titleLabel.setForeground(BRIGHT_YELLOW);
        titleLabel.setBounds(width/2 - 200, height/4, 400, 60);
        add(titleLabel);

        // תת כותרת
        JLabel subtitleLabel = new JLabel("Start to Play!");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        subtitleLabel.setForeground(TURQUOISE);
        subtitleLabel.setBounds(width/2 - 100, height/4 + 70, 200, 30);
        add(subtitleLabel);
        
        // כפתור התחלה
        startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setBackground(BRIGHT_ORANGE);
        startButton.setForeground(Color.WHITE);
        startButton.setBounds(width/2 - 100, height/2, 200, 50);
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setOpaque(true);
        add(startButton);

        // אנימציה
        animationTimer = new Timer(50, e -> {
            hue += 0.02f;
            if (hue > 1) hue = 0;
            repaint();
        });
        animationTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // רקע דינמי
        drawBackground(g2d);
        
        // פקמן נע
        drawMovingPacman(g2d);
        
        // רוחות רפאים צבעוניות
        drawGhosts(g2d);
        
        // נקודות מהבהבות
        drawDots(g2d);
    }

    private void drawBackground(Graphics2D g2d) {
        // יצירת גרדיאנט דינמי ברקע
        GradientPaint gradient = new GradientPaint(
            0, 0, new Color(0, 0, 40),
            getWidth(), getHeight(),
            new Color(0, (int)(80 + Math.sin(hue * 2) * 20), 100)
        );
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawMovingPacman(Graphics2D g2d) {
        // ציור פקמן נע
        g2d.setColor(BRIGHT_YELLOW);
        int pacmanSize = 40;
        int x = (int)(Math.sin(hue * 2) * 100 + getWidth()/2);
        int y = getHeight()/2 + 100;
        int mouthAngle = (int)(Math.abs(Math.sin(hue * 8)) * 45);
        g2d.fillArc(x - pacmanSize/2, y - pacmanSize/2, pacmanSize, pacmanSize, mouthAngle, 360 - 2*mouthAngle);
    }

    private void drawGhosts(Graphics2D g2d) {
        Color[] ghostColors = {BRIGHT_PINK, TURQUOISE, BRIGHT_ORANGE, new Color(255, 0, 0)};
        for (int i = 0; i < 4; i++) {
            int x = (int)(Math.sin(hue * 2 + i * Math.PI/2) * 150 + getWidth()/2);
            int y = (int)(Math.cos(hue * 2 + i * Math.PI/2) * 100 + getHeight()/2);
            drawGhost(g2d, x, y, ghostColors[i]);
        }
    }

    private void drawGhost(Graphics2D g2d, int x, int y, Color color) {
        g2d.setColor(color);
        // גוף הרוח
        g2d.fillArc(x - 15, y - 15, 30, 30, 0, 180);
        g2d.fillRect(x - 15, y, 30, 15);
        // רגליים
        for (int i = 0; i < 3; i++) {
            g2d.fillArc(x - 15 + i * 10, y + 15, 10, 10, 180, 180);
        }
        // עיניים
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x - 8, y - 5, 6, 6);
        g2d.fillOval(x + 2, y - 5, 6, 6);
        g2d.setColor(Color.BLUE);
        g2d.fillOval(x - 6, y - 3, 2, 2);
        g2d.fillOval(x + 4, y - 3, 2, 2);
    }

    private void drawDots(Graphics2D g2d) {
        g2d.setColor(BRIGHT_YELLOW);
        for (int i = 0; i < 20; i++) {
            double angle = hue * 4 + i * Math.PI/10;
            int x = (int)(Math.sin(angle) * 200 + getWidth()/2);
            int y = (int)(Math.cos(angle) * 200 + getHeight()/2);
            int dotSize = (int)(4 + Math.sin(hue * 4 + i) * 2);
            g2d.fillOval(x - dotSize/2, y - dotSize/2, dotSize, dotSize);
        }
    }
    
    public void addStartButtonListener(ActionListener listener) {
        startButton.addActionListener(e -> {
            animationTimer.stop();
            listener.actionPerformed(e);
        });
    }
}
