package GameV2;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class ScoreManager {
    private static final String SCORES_FILE = "scores.txt";
    private static final int MAX_SCORES = 10;
    private List<GameScore> highScores;

    public ScoreManager() {
        highScores = new ArrayList<>();
        loadScores();
    }

    public void addScore(int score) {
        String date = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
        highScores.add(new GameScore(score, date));
        Collections.sort(highScores, Collections.reverseOrder());
        
        if (highScores.size() > MAX_SCORES) {
            highScores = highScores.subList(0, MAX_SCORES);
        }
        
        saveScores();
    }

    public List<GameScore> getHighScores() {
        return new ArrayList<>(highScores);
    }

    private void loadScores() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    highScores.add(new GameScore(Integer.parseInt(parts[0]), parts[1]));
                }
            }
            Collections.sort(highScores, Collections.reverseOrder());
        } catch (IOException e) {
            // אם הקובץ לא קיים, פשוט נתחיל עם רשימה ריקה
        }
    }

    private void saveScores() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SCORES_FILE))) {
            for (GameScore score : highScores) {
                writer.println(score.getScore() + "," + score.getDate());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class GameScore implements Comparable<GameScore> {
        private final int score;
        private final String date;

        public GameScore(int score, String date) {
            this.score = score;
            this.date = date;
        }

        public int getScore() {
            return score;
        }

        public String getDate() {
            return date;
        }

        @Override
        public int compareTo(GameScore other) {
            return Integer.compare(this.score, other.score);
        }
    }
}
