package GameV2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class VictoryFireworks extends JPanel {
    private ArrayList<Firework> fireworks;
    private Timer animationTimer;
    private Random random;
    private final int width;
    private final int height;

    public VictoryFireworks(int width, int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));
        setBackground(new Color(0, 0, 0, 0));
        setOpaque(false);
        
        fireworks = new ArrayList<>();
        random = new Random();
        
        // יצירת זיקוקים חדשים כל 100 מילישניות
        animationTimer = new Timer(100, e -> {
            updateFireworks();
            if (random.nextInt(100) < 20) { // 20% סיכוי ליצירת זיקוק חדש
                createNewFirework();
            }
            repaint();
        });
    }

    public void start() {
        animationTimer.start();
    }

    public void stop() {
        animationTimer.stop();
    }

    private void createNewFirework() {
        int startX = random.nextInt(width);
        int startY = height;
        int targetY = random.nextInt(height / 2); // גובה הפיצוץ
        Color color = new Color(
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        );
        fireworks.add(new Firework(startX, startY, targetY, color));
    }

    private void updateFireworks() {
        for (int i = fireworks.size() - 1; i >= 0; i--) {
            Firework firework = fireworks.get(i);
            firework.update();
            if (firework.isDone()) {
                fireworks.remove(i);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Firework firework : fireworks) {
            firework.draw(g2d);
        }
    }

    private class Firework {
        private double x, y;
        private final int targetY;
        private final Color color;
        private boolean exploded;
        private ArrayList<Spark> sparks;
        private final int riseSpeed = 10;
        private int age;

        public Firework(int x, int targetY, int groundY, Color color) {
            this.x = x;
            this.y = groundY;
            this.targetY = targetY;
            this.color = color;
            this.exploded = false;
            this.sparks = new ArrayList<>();
            this.age = 0;
        }

        public void update() {
            if (!exploded) {
                // עליית הזיקוק
                y -= riseSpeed;
                if (y <= targetY) {
                    explode();
                }
            } else {
                // עדכון הניצוצות
                for (Spark spark : sparks) {
                    spark.update();
                }
                age++;
            }
        }

        private void explode() {
            exploded = true;
            // יצירת ניצוצות בכל הכיוונים
            for (int i = 0; i < 50; i++) {
                double angle = random.nextDouble() * 2 * Math.PI;
                double speed = 2 + random.nextDouble() * 4;
                sparks.add(new Spark(
                    x, y,
                    Math.cos(angle) * speed,
                    Math.sin(angle) * speed,
                    color
                ));
            }
        }

        public void draw(Graphics2D g) {
            if (!exploded) {
                // ציור הזיקוק העולה
                g.setColor(color);
                g.fillOval((int)x - 2, (int)y - 2, 4, 4);
                
                // ציור שובל
                g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 100));
                g.drawLine((int)x, (int)y, (int)x, (int)y + 10);
            } else {
                // ציור הניצוצות
                for (Spark spark : sparks) {
                    spark.draw(g);
                }
            }
        }

        public boolean isDone() {
            return exploded && age > 50; // הזיקוק נעלם אחרי 50 פריימים מהפיצוץ
        }
    }

    private class Spark {
        private double x, y;
        private double dx, dy;
        private final Color color;
        private double alpha = 255;
        
        public Spark(double x, double y, double dx, double dy, Color color) {
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
                    (int)Math.max(0, Math.min(255, alpha))
                ));
                g.fillOval((int)x - 1, (int)y - 1, 3, 3);
            }
        }
    }
}
