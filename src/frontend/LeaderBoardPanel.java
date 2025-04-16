package frontend;

import backend.LeaderBoard;
import backend.User;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a panel displaying the leaderboard in the game's GUI.
 * It allows users to view scores across different difficulty levels.
 */
public class LeaderBoardPanel extends JPanel {
    private JTable leaderboardTable;
    private JScrollPane scrollPane;
    private JComboBox<String> difficultyComboBox;
    private JButton backButton;
    private String[] columnNames = {"Rank", "Name", "Score"};
    private String userEmail; // Variable to store user's email

    /**
     * Constructs a LeaderBoardPanel object.
     *
     * @param mainApp The main application window, used for navigating back.
     * @param userEmail The email of the current user, for potential user-specific functionality.
     */
    public LeaderBoardPanel(MainApplication mainApp, String userEmail) {
        this.userEmail = userEmail;
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for simplicity
        setBackground(new Color(230, 230, 230)); // Neutral background color

        // Dropdown for selecting difficulty level
        difficultyComboBox = new JComboBox<>(new String[]{"Easy", "Medium", "Hard"});
        difficultyComboBox.setFont(new Font("Arial", Font.BOLD, 14));
        difficultyComboBox.addActionListener(e -> updateLeaderboardTable(difficultyComboBox.getSelectedIndex() + 1));
        add(difficultyComboBox, BorderLayout.NORTH);

        // Initialize JTable with DefaultTableModel
        leaderboardTable = new JTable(new DefaultTableModel(null, columnNames));
        leaderboardTable.setEnabled(false); // Disable editing
        scrollPane = new JScrollPane(leaderboardTable);
        styleTable(); // Apply custom styles to the table after initializing scrollPane
        add(scrollPane, BorderLayout.CENTER);

        // Back Button to return to the previous menu
        backButton = new JButton("Back");
        configureBackButton(); // Apply custom styles and place the back button
        backButton.addActionListener(e -> {
            try { sfx("src/resources/soundDefault.wav"); }
            catch (IOException e2) { e2.printStackTrace(); }

            try {
                mainApp.switchToMenuPanel(userEmail);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Load the leaderboard with the Easy difficulty initially
        updateLeaderboardTable(1);
    }

    /**
     * Styles the leaderboard table for better visual appearance.
     */
    private void styleTable() {
        // Ensure scrollPane is not null
        if (scrollPane == null) {
            throw new IllegalStateException("scrollPane must be initialized before styling the table.");
        }

        // Set custom styles for table
        leaderboardTable.setRowHeight(30);
        leaderboardTable.setFont(new Font("SansSerif", Font.PLAIN, 20));
        leaderboardTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        leaderboardTable.getTableHeader().setBackground(new Color(34, 34, 34));
        leaderboardTable.getTableHeader().setForeground(new Color(255, 255, 255));
        leaderboardTable.setGridColor(new Color(200, 200, 200));
        leaderboardTable.setBackground(new Color(240, 240, 240));
        leaderboardTable.setForeground(new Color(50, 50, 50));

        // Make the scroll pane transparent
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        leaderboardTable.setDefaultRenderer(Object.class, centerRenderer);
        leaderboardTable.getTableHeader().setDefaultRenderer(centerRenderer);

        // Alternate row color
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        if (defaults.get("Table.alternateRowColor") == null)
            defaults.put("Table.alternateRowColor", new Color(220, 220, 220));
    }

    /**
     * Configures the back button with specific styles and functionality.
     */
    private void configureBackButton() {
        // Customize back button
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setPreferredSize(new Dimension(100, 50));

        // Add padding around the back button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(backButton, BorderLayout.CENTER);
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(new Color(230, 230, 230)); // Match the wireframe background color

        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Updates the leaderboard table based on the selected difficulty level.
     *
     * @param difficultyLevel The difficulty level to display scores for.
     */
    private void updateLeaderboardTable(int difficultyLevel) {
        // Get sorted list of users based on difficulty level
        ArrayList<User> users = LeaderBoard.getSortedList(difficultyLevel);
        Object[][] data = new Object[users.size()][3];

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            data[i][0] = i + 1;
            data[i][1] = user.getEmail(); // Change as needed to display names
            data[i][2] = switch (difficultyLevel) {
                case 1 -> user.getLevel1HighestScore();
                case 2 -> user.getLevel2HighestScore();
                case 3 -> user.getLevel3HighestScore();
                default -> 0;
            };
        }

        DefaultTableModel model = (DefaultTableModel) leaderboardTable.getModel();
        model.setDataVector(data, columnNames);
        configureTableColumns();
    }

    /**
     * Configures the widths of the columns in the leaderboard table.
     */
    private void configureTableColumns() {
        int[] columnWidths = {50, 200, 100};
        for (int i = 0; i < columnWidths.length; i++) {
            if (i < leaderboardTable.getColumnModel().getColumnCount()) {
                TableColumn column = leaderboardTable.getColumnModel().getColumn(i);
                column.setPreferredWidth(columnWidths[i]);
            }
        }
    }

    /**
     * Plays a sound effect from the specified file.
     *
     * @param filename The path to the sound file.
     * @throws IOException If there is an issue playing the sound.
     */
    public void sfx(String filename) throws IOException {
        Clip clip;
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }
}
