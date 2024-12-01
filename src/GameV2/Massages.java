package GameV2;

import javax.swing.*;
import java.awt.*;

public class Massages {
    public static JLabel scoreLabel;
    public static JLabel lives;
    private static int livesCount = 3;

    static {
        scoreLabel = new JLabel("ניקוד : 0");
        scoreLabel.setFont(new Font("Ariel", Font.BOLD, 20));
        scoreLabel.setForeground(Color.black);
        scoreLabel.setBounds(10, 10, 100, 30);
        scoreLabel.setLocation(420, 610);

        lives = new JLabel("חיים : 3");
        lives.setFont(new Font("Ariel", Font.BOLD, 20));
        lives.setForeground(Color.black);
        lives.setBounds(10, 10, 100, 30);
        lives.setLocation(320, 610);
    }

    public static void loseLife() {
        livesCount--;
        lives.setText("חיים : " + livesCount);
        
        if (livesCount <= 0) {
            showGameOverDialog();
        }
    }

    public static void resetLives() {
        livesCount = 3;
        lives.setText("חיים : " + livesCount);
    }

    private static void showGameOverDialog() {
        int choice = JOptionPane.showOptionDialog(null,
            "Game Over!\nמה ברצונך לעשות?",
            "Game Over",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new String[]{"משחק חדש", "יציאה"},
            "משחק חדש");

        if (choice == 0) {
            // התחל משחק חדש
            Game.resetGame();
        } else {
            System.exit(0);
        }
    }

    public static void victory() {
        JOptionPane.showMessageDialog(null, "ניצחת!");
        System.exit(0);
    }
}