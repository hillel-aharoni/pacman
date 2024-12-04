package GameV2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class Table extends JFrame {
    private static final String HIGH_SCORES_FILE = "C:\\Users\\HOME\\Documents\\GitHub\\pacman\\highscores.txt";
    private static final int MAX_SCORES = 10;
    private JTable table;
    private DefaultTableModel model;

    public Table() {
        setTitle("טבלת שיאים");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create table model with columns
        String[] columnNames = {"דירוג", "ניקוד", "תאריך", "שעה"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Create and configure table
        table = new JTable(model);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.setRowHeight(25);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Load high scores
        loadHighScores();

        // Set window properties
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    public void addScore(int score) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();

        List<HighScore> scores = loadScoresFromFile();
        scores.add(new HighScore(score, dateFormat.format(now), timeFormat.format(now)));
        Collections.sort(scores);

        // Keep only top scores
        while (scores.size() > MAX_SCORES) {
            scores.remove(scores.size() - 1);
        }

        saveScoresToFile(scores);
        updateTableDisplay(scores);
    }

    private void loadHighScores() {
        List<HighScore> scores = loadScoresFromFile();
        updateTableDisplay(scores);
    }

    private List<HighScore> loadScoresFromFile() {
        List<HighScore> scores = new ArrayList<>();
        File file = new File(HIGH_SCORES_FILE);

        if (!file.exists()) {
            return scores;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    scores.add(new HighScore(
                        Integer.parseInt(parts[0]),
                        parts[1],
                        parts[2]
                    ));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading high scores: " + e.getMessage());
        }

        Collections.sort(scores);
        return scores;
    }

    private void saveScoresToFile(List<HighScore> scores) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(HIGH_SCORES_FILE))) {
            for (HighScore score : scores) {
                writer.println(score.score + "," + score.date + "," + score.time);
            }
        } catch (IOException e) {
            System.err.println("Error saving high scores: " + e.getMessage());
        }
    }

    private void updateTableDisplay(List<HighScore> scores) {
        model.setRowCount(0);
        int rank = 1;
        for (HighScore score : scores) {
            model.addRow(new Object[]{
                rank++,
                score.score,
                score.date,
                score.time
            });
        }
    }

    private static class HighScore implements Comparable<HighScore> {
        int score;
        String date;
        String time;

        public HighScore(int score, String date, String time) {
            this.score = score;
            this.date = date;
            this.time = time;
        }

        @Override
        public int compareTo(HighScore other) {
            return Integer.compare(other.score, this.score); // Descending order
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Table table = new Table();
            table.setVisible(true);
        });
    }
}
