package NewGame;

import Game.Key;

import javax.swing.*;
import java.awt.*;

public class MyGame {

    static int score;
    static int allScore;

    static JPanel[][] coins;

    static JPanel player;
    static private int[][]  Map;

    public static void main(String[] args) {
        Map = MyD_map.D_map1;

        JFrame jFrame = new JFrame("new pacman");

        int winWidth = Map[0].length * 20;
        int winHeight = Map.length * 20;

        JLayeredPane jLayeredPane = new JLayeredPane();
        jLayeredPane.setPreferredSize(new Dimension(winWidth, winHeight));

        JPanel mapJpanel = new JPanel(new GridLayout(Map.length, Map[0].length));
        mapJpanel.setBounds(0,0,winWidth,winHeight);

        coins = new JPanel[Map.length][Map[0].length];

        for (int i = 0; i < Map.length; i++) {
            for (int j = 0; j < Map[0].length; j++) {
                JPanel jPanel1 = new JPanel();
                if (Map[i][j] == 0){
                    jPanel1.setBackground(Color.BLACK);
                    JPanel coin = new JPanel();
                    coin.setPreferredSize(new Dimension(6,6));
                    coin.setBackground(Color.yellow);
                    jPanel1.add(coin);
                    coins[i][j] = coin;
                    allScore++;
                }
                if (Map[i][j] == 1){
                    jPanel1.setBackground(Color.BLUE);

                }
                if (Map[i][j] == 2){
                    jPanel1.setBackground(Color.YELLOW);

                }
                if (Map[i][j] == 3){
                    jPanel1.setBackground(Color.BLACK);

                }
                if (Map[i][j] == 5){
                    jPanel1.setBackground(Color.black);

                }
                mapJpanel.add(jPanel1);
            }
        }// and of loop;

        player = new JPanel();
        player.setBounds(260,460, 20, 20);
        player.setBackground(Color.CYAN);

        jLayeredPane.add(mapJpanel, JLayeredPane.DEFAULT_LAYER);
        jLayeredPane.add(player, JLayeredPane.PALETTE_LAYER);

        jFrame.add(jLayeredPane);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(3);
        jFrame.addKeyListener(new MyKey());
        jFrame.setFocusable(true);
        jFrame.requestFocus();

    } // and of the main;
}
