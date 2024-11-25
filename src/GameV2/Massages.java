package GameV2;

import javax.swing.*;
import java.awt.*;

public class Massages {

    public static JLabel scoreLabel;
    public static JLabel lives;


    static {
        scoreLabel = new JLabel("ניקוד : 100");
        scoreLabel.setFont(new Font("Ariel", Font.BOLD,20));
        scoreLabel.setForeground(Color.black);
        scoreLabel.setBounds(10,10,100,30);
        scoreLabel.setLocation(420,610);
    }

    static {
        lives = new JLabel("חיים : 3 ");
        lives.setFont(new Font("Ariel", Font.BOLD,20));
        lives.setForeground(Color.black);
        lives.setBounds(10,10,100,30);
        lives.setLocation(320,610);
    }

    public static void victory() {
        // יצירת תווית עם הטקסט והפונט הרצוי
        JLabel messageLabel = new JLabel("חתיכת פג בטח אתה מעוניין לדעת מה הניקוד שלך זה מה שאתה יודע לעשות לשחק פקמן אולי פעם הבאה תצליח יותר קח :  " + Game.score);
        messageLabel.setFont(new Font("David", Font.BOLD, 18)); // שינוי הפונט לפונט הרצוי
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER); // מיקום הטקסט במרכז

        // הצגת תיבת הודעה מותאמת אישית
        JOptionPane.showMessageDialog(null, messageLabel, "ניצחון", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0); // מסיים את התכנית וסוגר את החלון
    }

}
