package frontend;

import backend.Accounts;
import backend.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainApplication extends JFrame  {
    /**
     * Initializes the main application frame, setting up the window properties and
     * launching the login panel. Initializes the Accounts system for user management.
     * @throws IOException If an error occurs during panel initialization.
     */

    public MainApplication() throws IOException {									// 
        setTitle("Monster Beat"); // title of the game
        setSize(1280, 770);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setResizable(false);
        new Accounts();
        switchToLogInPanel();
    }
    /**
     * Switches the current pane to the login panel for user authentication.
     * This version of the method initializes a new login panel without any user feedback messages.
     * @throws IOException If an error occurs during panel initialization.
     */

    public void switchToLogInPanel() throws IOException {							// Frontend finished - 3/30 AL 
        JPanel loginPanel = new LoginPanel2(this);			// This line for testing new title screen - 3/30 AL
        setContentPane(loginPanel);
        validate();
        repaint();
    }
    /**
     * Switches the current pane to the login panel, specifically after an unsuccessful registration attempt.
     * Displays a message to the user indicating that the email is already registered.
     * @param userEmail The email address that has already been registered.
     * @throws IOException If an error occurs during panel initialization.
     */

    public void switchToLogInPanel(String userEmail) throws IOException {			// Now displays error message - 3/30 AL 
        JLabel registerMessage = new JLabel(userEmail + " already registed, please login");
        registerMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //LoginPanel loginPanel = new LoginPanel(this);		// Cannot test here because of buttonPanel instance - 3/30 AL
        //loginPanel.buttonPanel.add(registerMessage);
        LoginPanel2 loginPanel2 = new LoginPanel2(this);		// Cannot test here because of buttonPanel instance - 3/30 AL
        loginPanel2.registerErrorMessage(registerMessage);
        
        //setContentPane(loginPanel);
        setContentPane(loginPanel2);
        
        validate();
        repaint();
    }

    /**
     * Switches the current pane to the registration panel for new user creation.
     * @throws IOException If an error occurs during panel initialization.
     */

    public void switchToRegisterPanel() throws IOException {						// 
        JPanel registerPanel = new RegisterPanel2(this);
        setContentPane(registerPanel);
        validate();
        repaint();
    }
    /**
     * Switches the current pane to the main menu panel, displaying game options.
     * @param userEmail The email address of the current user, used for personalized greetings or functionality.
     * @throws IOException If an error occurs during panel initialization.
     */

    public void switchToMenuPanel(String userEmail) throws IOException {			// Need to edit title image - 3/30 AL 
        JPanel menuPanel = new MenuPanel2(this, userEmail);	// This line for testing new main menu screen - 3/30 AL
        setContentPane(menuPanel);
        validate();
        repaint();
    }
    /**
     * Switches the current pane to the results panel, showing the game's outcome and stats.
     * @param game The Game object containing game data and statistics to display.
     * @param timeLeft The remaining time left at the end of the game session.
     * @param userEmail The email address of the current user, for personalized feedback.
     * @throws IOException If an error occurs during panel initialization.
     */

    public void switchToResultPanel(Game game, int timeLeft, String userEmail) throws IOException {	// Seems obsolete? - 3/31 AL
        System.out.println("SWITCH TO RESULTS PANEL CALLED");
    	JLabel timeLeftLabel = new JLabel("Time left: " + timeLeft + " seconds");
        JPanel resultPanel = new ResultPanel(this, game, timeLeft, userEmail);
        timeLeftLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultPanel.add(timeLeftLabel);
    }
    /**
     * Switches the current pane to the results panel, showing the game's outcome and stats.
     * This version is used when no specific user feedback or email is required.
     * @param game The Game object containing game data and statistics to display.
     * @param timeLeft The remaining time left at the end of the game session.
     * @throws IOException If an error occurs during panel initialization.
     */

    public void switchToResultPanel(Game game, int timeLeft) throws IOException {	// 
        JPanel resultPanel = new ResultPanel(this, game, timeLeft);

        setContentPane(resultPanel);
        add(Box.createRigidArea(new Dimension(0, 800))); // Increase this value to push buttons lower
        validate();
        repaint();
    }
    /**
     * Switches the current pane to the leaderboard panel, displaying user rankings.
     * @param userEmail The email address of the current user, potentially for highlighting or specific interactions.
     */

    public void switchToLeaderBoardPanel(String userEmail) {
        // This method now expects the user's email.
        // Ensure you have the user's email when calling this method.
        LeaderBoardPanel leaderboardPanel = new LeaderBoardPanel(this, userEmail);
        setContentPane(leaderboardPanel);
        validate();
        repaint();
    }

    /**
     * Switches the current pane to the tutorial panel, showing game instructions or tutorials.
     * @param userEmail The email address of the current user, used for personalized instructions if necessary.
     * @throws IOException If an error occurs during panel initialization.
     */

    public void switchToTutorialPanel(String userEmail) throws IOException {
    	JPanel tutorialPanel = new TutorialPanel(this, userEmail); 
    	setContentPane(tutorialPanel);
        validate();
        repaint();
    }

    /**
     * Switches the current pane to the game panel, starting a new game session with the selected difficulty.
     * @param userEmail The email address of the current user, used for game session personalization.
     * @param selectedDifficulty The difficulty level chosen for the new game session.
     * @throws IOException If an error occurs during panel initialization.
     */

    public void switchToGamePanel(String userEmail, int selectedDifficulty) throws IOException {
        JPanel gamePanel = new GamePanel2(this, userEmail, selectedDifficulty);
        setContentPane(gamePanel);
        validate();
        repaint();
    }

}
