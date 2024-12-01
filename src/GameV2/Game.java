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
    static PowerPellet[][] powerPellets; // מערך לשמירת נקודות הכוח

    static Pink_Ghost pink_ghost1;
    static Pink_Ghost pink_ghost2;
    static Pink_Ghost pink_ghost3;
    static Pink_Ghost pink_ghost4;

    static Player player;
    private static int[][] Map;
    private static JFrame jFrame;
    private static JLayeredPane gamePane;
    private static StartScreen startScreen;
    private static Point playerStartPosition = new Point(260, 460);
    private static Point[] ghostStartPositions = {
        new Point(280, 280),
        new Point(240, 280),
        new Point(260, 260),
        new Point(260, 200)
    };

    public static void resetGame() {
        score = 0;
        Massages.scoreLabel.setText("ניקוד : 0");
        Massages.resetLives();
        
        // החזרת השחקן למיקום ההתחלתי
        player.setLocation(playerStartPosition);
        
        // החזרת הרוחות למיקום ההתחלתי
        pink_ghost1.setLocation(ghostStartPositions[0].x, ghostStartPositions[0].y);
        pink_ghost2.setLocation(ghostStartPositions[1].x, ghostStartPositions[1].y);
        pink_ghost3.setLocation(ghostStartPositions[2].x, ghostStartPositions[2].y);
        pink_ghost4.setLocation(ghostStartPositions[3].x, ghostStartPositions[3].y);
    }

    public static void resetPositions() {
        // החזרת השחקן והרוחות למיקום ההתחלתי
        player.setLocation(playerStartPosition);
        pink_ghost1.setLocation(ghostStartPositions[0].x, ghostStartPositions[0].y);
        pink_ghost2.setLocation(ghostStartPositions[1].x, ghostStartPositions[1].y);
        pink_ghost3.setLocation(ghostStartPositions[2].x, ghostStartPositions[2].y);
        pink_ghost4.setLocation(ghostStartPositions[3].x, ghostStartPositions[3].y);
    }

    public static void checkGhostCollision() {
        Rectangle playerBounds = player.getBounds();
        Pink_Ghost[] ghosts = {pink_ghost1, pink_ghost2, pink_ghost3, pink_ghost4};
        
        for (Pink_Ghost ghost : ghosts) {
            if (playerBounds.intersects(ghost.getBounds())) {
                Massages.loseLife();
                resetPositions();
                break;
            }
        }
    }

    public static void main(String[] args) {
        Map = D_Map.D_Map;

        jFrame = new JFrame("My-Pac-Men-2024");
        int winWidth = Map[0].length * 20;
        int winHeight = Map.length * 20;

        // יצירת מסך פתיחה
        startScreen = new StartScreen(winWidth, winHeight);
        startScreen.addStartButtonListener(e -> startGame());
        
        jFrame.add(startScreen);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    private static void startGame() {
        jFrame.remove(startScreen);
        
        int winWidth = Map[0].length * 20;
        int winHeight = Map.length * 20;

        gamePane = new JLayeredPane();
        gamePane.setPreferredSize(new Dimension(winWidth, winHeight));

        JPanel mapjpanel = new JPanel(new GridLayout(Map.length, Map[0].length));
        mapjpanel.setBounds(0, 0, winWidth, winHeight);

        coins = new JPanel[Map.length][Map[0].length];
        powerPellets = new PowerPellet[Map.length][Map[0].length];

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
                    jPanel1.setBackground(Color.BLACK);
                    PowerPellet powerPellet = new PowerPellet();
                    jPanel1.add(powerPellet);
                    powerPellets[i][j] = powerPellet;
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
        player.setBounds(260, 460, width, height);  // מיקום התחלתי של הפקמן
        player.setOpaque(false);  // מאפשר לראות את הרקע מתחת לפקמן
        player.setBackground(Color.RED);

        pink_ghost1 = new Pink_Ghost(280, 280, "C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\ghost.gif");
        gamePane.add(pink_ghost1, JLayeredPane.PALETTE_LAYER);

        pink_ghost2 = new Pink_Ghost(240, 280, "C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\ghost.gif");
        gamePane.add(pink_ghost2, JLayeredPane.PALETTE_LAYER);

        pink_ghost3 = new Pink_Ghost(260, 260, "C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\ghost.gif");
        gamePane.add(pink_ghost3, JLayeredPane.PALETTE_LAYER);

        pink_ghost4 = new Pink_Ghost(260, 200, "C:\\Users\\HOME\\Documents\\GitHub\\pacman\\src\\GameV2\\img\\ghost.gif");
        gamePane.add(pink_ghost4, JLayeredPane.PALETTE_LAYER);


        gamePane.add(mapjpanel, JLayeredPane.DEFAULT_LAYER);
        gamePane.add(player, JLayeredPane.PALETTE_LAYER);
        gamePane.add(Massages.scoreLabel , JLayeredPane.PALETTE_LAYER);
        gamePane.add(Massages.lives , JLayeredPane.PALETTE_LAYER);

        jFrame.add(gamePane);
        jFrame.pack();
        jFrame.revalidate();
        jFrame.repaint();

        // הוספת KeyListener למשחק
        jFrame.addKeyListener(new MyKeyListenerV2());
        jFrame.setFocusable(true);
        jFrame.requestFocus();  // חשוב! מבקש פוקוס מיד אחרי תחילת המשחק

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                        pink_ghost1.randomDir();
                        pink_ghost2.randomDir();
                        pink_ghost3.randomDir();
                        pink_ghost4.randomDir();
                        checkGhostCollision();  // בדיקת התנגשות עם רוחות
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}// and of class.