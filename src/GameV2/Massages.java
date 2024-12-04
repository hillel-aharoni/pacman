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
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(44, 62, 80));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // כותרת המשחק נגמר
        JLabel gameOverLabel = new JLabel("Game Over!");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gameOverLabel.setForeground(Color.WHITE);
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // תצוגת הניקוד
        JLabel scoreLabel = new JLabel("הניקוד הסופי שלך: " + Game.score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setForeground(new Color(46, 204, 113));
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // כפתורים
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false);

        JButton restartButton = new JButton("שחק שוב");
        JButton exitButton = new JButton("יציאה");

        // עיצוב כפתורים
        for (JButton button : new JButton[]{restartButton, exitButton}) {
            button.setFont(new Font("Arial", Font.BOLD, 14));
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(52, 152, 219));
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        exitButton.setBackground(new Color(231, 76, 60));

        buttonPanel.add(restartButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(exitButton);

        panel.add(gameOverLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(scoreLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(buttonPanel);

        JDialog dialog = new JDialog();
        dialog.setUndecorated(true);
        dialog.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        dialog.setBackground(new Color(0, 0, 0, 0));
        dialog.setContentPane(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);

        restartButton.addActionListener(e -> {
            dialog.dispose();
            Game.resetGame();
        });

        exitButton.addActionListener(e -> {
            dialog.dispose();
            System.exit(0);
        });

        dialog.setVisible(true);
    }

    public static void victory() {
        JOptionPane.showMessageDialog(null, "ניצחת!");
        System.exit(0);
    }
}