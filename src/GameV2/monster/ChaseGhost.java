package GameV2.monster;

import GameV2.D_Map;
import GameV2.Game;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ChaseGhost extends Pink_Ghost {
    private static final int CELL_SIZE = 20;
    private javax.swing.Timer chaseTimer;
    private Random random = new Random();

    public ChaseGhost(int x, int y, String imagePath) {
        super(x, y, imagePath);
        initializeChaseMode();
    }

    private void initializeChaseMode() {
        chaseTimer = new javax.swing.Timer(3000, e -> chasePlayer());
        chaseTimer.start();
    }

    public void chasePlayer() {
        if (!Game.isPlayerVisible()) return;

        // מיקום נוכחי של הרוח והשחקן בקואורדינטות של המפה
        Point ghostPos = new Point(getX() / CELL_SIZE, getY() / CELL_SIZE);
        Point playerPos = new Point(Game.getPlayerX() / CELL_SIZE, Game.getPlayerY() / CELL_SIZE);

        // מציאת המסלול הקצר ביותר לשחקן
        ArrayList<Point> path = findPathToPlayer(ghostPos, playerPos);
        
        if (path != null && path.size() > 1) {
            // הצעד הבא במסלול
            Point nextStep = path.get(1);
            moveToPosition(nextStep.x * CELL_SIZE, nextStep.y * CELL_SIZE);
        } else {
            // אם אין מסלול, נוע באופן אקראי
            randomDir();
        }
    }

    private ArrayList<Point> findPathToPlayer(Point start, Point target) {
        // אלגוריתם BFS למציאת המסלול הקצר ביותר
        Queue<Point> queue = new LinkedList<>();
        HashMap<Point, Point> cameFrom = new HashMap<>();
        queue.add(start);
        cameFrom.put(start, null);

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.equals(target)) {
                return reconstructPath(cameFrom, start, target);
            }

            // בדיקת כל הכיוונים האפשריים
            for (Point next : getValidNeighbors(current)) {
                if (!cameFrom.containsKey(next)) {
                    queue.add(next);
                    cameFrom.put(next, current);
                }
            }
        }

        return null;
    }

    private ArrayList<Point> getValidNeighbors(Point p) {
        ArrayList<Point> neighbors = new ArrayList<>();
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // ימינה, למטה, שמאלה, למעלה

        for (int[] dir : directions) {
            int newX = p.x + dir[0];
            int newY = p.y + dir[1];

            // בדיקה אם המיקום החדש תקין
            if (newX >= 0 && newX < D_Map.D_Map[0].length &&
                newY >= 0 && newY < D_Map.D_Map.length &&
                D_Map.D_Map[newY][newX] != 1) {
                neighbors.add(new Point(newX, newY));
            }
        }

        return neighbors;
    }

    private ArrayList<Point> reconstructPath(HashMap<Point, Point> cameFrom, Point start, Point target) {
        ArrayList<Point> path = new ArrayList<>();
        Point current = target;

        while (current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }

        Collections.reverse(path);
        return path;
    }

    private void moveToPosition(int targetX, int targetY) {
        if (targetX > getX()) move(RIGHT);
        else if (targetX < getX()) move(LEFT);
        else if (targetY > getY()) move(DOWN);
        else if (targetY < getY()) move(UP);
    }

    public void stopChasing() {
        if (chaseTimer != null && chaseTimer.isRunning()) {
            chaseTimer.stop();
        }
    }
}
