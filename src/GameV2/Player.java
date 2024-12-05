package GameV2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Player extends JPanel {

    private ImageIcon gifIcon;
    private int gifWidth;
    private int gifHeight;
    private boolean isPowered = false;
    private Timer powerTimer;
    private Timer movementTimer;
    private int currentDirection = KeyEvent.VK_RIGHT; // כיוון ברירת מחדל
    private boolean isMoving = false;

    public Player() {
        setBounds(260, 460, 40, 40); // התאמת הגבולות לגודל הרצוי
        setOpaque(false);
        loadImage("C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\right.gif");
        gifWidth = 20;  // רוחב מותאם
        gifHeight = 20; // גובה מותאם
        setupMovementTimer();
    }

    private void setupMovementTimer() {
        movementTimer = new Timer(50, e -> moveInCurrentDirection());
        movementTimer.setRepeats(true);
        movementTimer.start();
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

    public void setDirection(int direction) {
        if (direction == KeyEvent.VK_UP || direction == KeyEvent.VK_DOWN ||
            direction == KeyEvent.VK_LEFT || direction == KeyEvent.VK_RIGHT) {
            currentDirection = direction;
            isMoving = true;
        }
    }

    private void moveInCurrentDirection() {
        if (!isMoving) return;

        int newX = getX();
        int newY = getY();

        switch (currentDirection) {
            case KeyEvent.VK_UP:
                newY -= 20;
                break;
            case KeyEvent.VK_DOWN:
                newY += 20;
                break;
            case KeyEvent.VK_LEFT:
                newX -= 20;
                break;
            case KeyEvent.VK_RIGHT:
                newX += 20;
                break;
        }

        int mapX = newX / 20;
        int mapY = newY / 20;

        // בדיקת התנגשות עם קירות
        if (mapX >= 0 && mapX < D_Map.D_Map[0].length &&
            mapY >= 0 && mapY < D_Map.D_Map.length &&
            D_Map.D_Map[mapY][mapX] != 1) {
            setLocation(newX, newY);
            
            // טיפול במעבר דרך המנהרה
            if (newX == 520 && newY == 280) {
                setLocation(0, 280);
            } else if (newX == 0 && newY == 280) {
                setLocation(520, 280);
            }

            // בדיקת אכילת נקודות כוח
            if (Game.powerPellets[mapY][mapX] != null && !Game.powerPellets[mapY][mapX].isCollected()) {
                Game.powerPellets[mapY][mapX].collect();
            }

            // בדיקת אכילת מטבעות
            if (Game.coins[mapY][mapX] != null) {
                Game.coins[mapY][mapX].setVisible(false);
                Game.coins[mapY][mapX] = null;
                Game.score++;
                Massages.scoreLabel.setText("ניקוד :" + Game.score);
                
                // בדיקה אם נאספו כל הנקודות במפה
                boolean allCoinsCollected = true;
                for (int i = 0; i < Game.coins.length; i++) {
                    for (int j = 0; j < Game.coins[i].length; j++) {
                        if (Game.coins[i][j] != null) {
                            allCoinsCollected = false;
                            break;
                        }
                    }
                }
                if (allCoinsCollected) {
                    Massages.victory();
                }
            }
        } else {
            isMoving = false; // עצירה כשנתקלים בקיר
        }
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