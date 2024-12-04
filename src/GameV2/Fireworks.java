package GameV2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Fireworks extends JPanel {
    private ArrayList<Particle> particles;
    private Timer animationTimer;
    private Random random;
    private int width;
    private int height;

    public Fireworks(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
        
        particles = new ArrayList<>();
        random = new Random();
        
        // יצירת אנימציה
        animationTimer = new Timer(50, e -> {
            updateParticles();
            repaint();
            
            // יצירת זיקוק חדש באופן אקראי
            if (random.nextInt(100) < 10) {
                createFirework();
            }
        });
    }

    public void startAnimation() {
        animationTimer.start();
    }

    public void stopAnimation() {
        animationTimer.stop();
    }

    private void createFirework() {
        int x = random.nextInt(width);
        int y = random.nextInt(height / 2); // רק בחצי העליון של המסך
        Color color = new Color(
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        );
        
        // יצירת חלקיקים לזיקוק
        for (int i = 0; i < 50; i++) {
            double angle = random.nextDouble() * 2 * Math.PI;
            double speed = 2 + random.nextDouble() * 4;
            particles.add(new Particle(
                x, y,
                Math.cos(angle) * speed,
                Math.sin(angle) * speed,
                color
            ));
        }
    }

    private void updateParticles() {
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle p = particles.get(i);
            p.update();
            
            // הסרת חלקיקים שנעלמו
            if (p.alpha <= 0) {
                particles.remove(i);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Particle p : particles) {
            p.draw(g2d);
        }
    }

    // מחלקה פנימית לייצוג חלקיק בודד
    private class Particle {
        double x, y;
        double dx, dy;
        Color color;
        int alpha = 255;
        
        public Particle(double x, double y, double dx, double dy, Color color) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
            this.color = color;
        }
        
        public void update() {
            x += dx;
            y += dy;
            dy += 0.1; // כוח משיכה
            alpha -= 5; // דעיכה הדרגתית
        }
        
        public void draw(Graphics2D g) {
            if (alpha > 0) {
                g.setColor(new Color(
                    color.getRed(),
                    color.getGreen(),
                    color.getBlue(),
                    Math.max(0, Math.min(255, alpha))
                ));
                g.fillOval((int)x - 2, (int)y - 2, 4, 4);
            }
        }
    }
}
