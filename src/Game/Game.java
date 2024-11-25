package Game;

import Game.Monster.Red_Ghost;

import javax.swing.*;
import java.awt.*;

public class Game {

    static int score;
    static int allScore;

    static JPanel[][] coins;

    static Red_Ghost red_ghost;

    static JPanel player;
    private static  int [][] Map;

    public static void main(String[] args) {
        Map = D_Map.D_Map1;

        JFrame jFrame = new JFrame("My-Pac-Men-2025");

        int winWidth = Map[0].length * 20;
        int winHeight = Map.length * 20;

        JLayeredPane layeredPane= new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(winWidth,winHeight));

        JPanel mapjpanel = new JPanel(new GridLayout(Map.length, Map[0].length));
        mapjpanel.setBounds(0,0,winWidth,winHeight);

        coins = new JPanel[Map.length][Map[0].length];

        for (int i = 0; i < Map.length; i++) {
            for (int j = 0; j < Map[0].length; j++) {
                JPanel jPanel1 = new JPanel();
                if (Map[i][j] == 0) {
                    jPanel1.setBackground(Color.BLACK);
                    //jPanel1.setLayout(new GridLayout()); // שהמטבע יהיה באמצע הפאנל
                    JPanel coin = new JPanel();
                    coin.setPreferredSize(new Dimension(6,6));
                    coin.setBackground(Color.YELLOW);
                    jPanel1.add(coin);
                    coins[i][j] = coin;
                    allScore++;

                }
                if (Map[i][j] == 1) {
                    jPanel1.setBackground(Color.BLUE);

                }
                if (Map[i][j] == 2) {
                    jPanel1.setBackground(Color.YELLOW);

                }
                if (Map[i][j] == 3) {
                    jPanel1.setBackground(Color.BLACK);

                }
                if (Map[i][j] == 5) {
                    jPanel1.setBackground(Color.BLACK);

                }
                mapjpanel.add(jPanel1);
            }

        } // and of loop.


        player = new JPanel();
        player.setBounds(260,460, 20, 20);
        player.setBackground(Color.red);

        red_ghost = new Red_Ghost();
        layeredPane.add(red_ghost, JLayeredPane.PALETTE_LAYER);


        layeredPane.add(mapjpanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(player, JLayeredPane.PALETTE_LAYER);

        jFrame.add(layeredPane);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.addKeyListener(new Key());
        jFrame.setVisible(true);
        jFrame.setFocusable(true);
        jFrame.requestFocus();

    }
}
