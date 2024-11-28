package GameV2;

import GameV2.monster.Pink_Ghost;

import javax.swing.*;
import java.awt.*;

public class Game {

    public static final int width = 20;
    public static final int height = 20;

    static int score = 0;
    static int allScore = 0;
    static JPanel[][] coins; // מערך לשמירת המטבעות

    static Pink_Ghost pink_ghost1;
    static Pink_Ghost pink_ghost2;
    static Pink_Ghost pink_ghost3;
    static Pink_Ghost pink_ghost4;

    static Player player;
    private static int[][] Map;

    public static void main(String[] args) {
        Map = D_Map.D_Map;

        JFrame jFrame = new JFrame("My-Pac-Men-2024");

        int winWidth = Map[0].length * 20;
        int winHeight = Map.length * 20;

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(winWidth, winHeight));

        JPanel mapjpanel = new JPanel(new GridLayout(Map.length, Map[0].length));
        mapjpanel.setBounds(0, 0, winWidth, winHeight);

        coins = new JPanel[Map.length][Map[0].length];

        for (int i = 0; i < Map.length; i++) {
            for (int j = 0; j < Map[0].length; j++) {
                JPanel jPanel1 = new JPanel();
                if (Map[i][j] == 0) {
                    jPanel1.setBackground(Color.BLACK);
                    //   jPanel1.setLayout(new GridLayout()); // שהמטבע יהיה באמצע המסך
                    JPanel coin = new JPanel();
                    coin.setPreferredSize(new Dimension(8, 8));
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
                if (Map[i][j] == 9) {
                    jPanel1.setBackground(Color.cyan);
                }
                mapjpanel.add(jPanel1);

            }

        }

        player = new Player();
        player.setBounds(260, 460, width, height);
        player.setBackground(Color.RED);

        pink_ghost1 = new Pink_Ghost(280, 280, "C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\ghost.gif");
        layeredPane.add(pink_ghost1, JLayeredPane.PALETTE_LAYER);

        pink_ghost2 = new Pink_Ghost(240, 280, "C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\ghost.gif");
        layeredPane.add(pink_ghost2, JLayeredPane.PALETTE_LAYER);

        pink_ghost3 = new Pink_Ghost(260, 260, "C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\ghost.gif");
        layeredPane.add(pink_ghost3, JLayeredPane.PALETTE_LAYER);

        pink_ghost4 = new Pink_Ghost(260, 200, "C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\ghost.gif");
        layeredPane.add(pink_ghost4, JLayeredPane.PALETTE_LAYER);


        layeredPane.add(mapjpanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(player, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(Massages.scoreLabel , JLayeredPane.PALETTE_LAYER);
        layeredPane.add(Massages.lives , JLayeredPane.PALETTE_LAYER);

        jFrame.add(layeredPane);
        jFrame.pack(); // פותח מסך לפי גודל התוכן
        jFrame.setLocationRelativeTo(null); // נפתח באמצע המסך
        jFrame.setVisible(true); // שתהיה תוגה על המסך
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.addKeyListener(new MyKeyListenerV2());
        jFrame.setFocusable(true); // שיקשיב טוב יותר למקשים


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(150);
                        pink_ghost1.randomDir();
                        pink_ghost2.randomDir();
                        pink_ghost3.randomDir();
                        pink_ghost4.randomDir();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();



    } // and of the main.
}// and of class.