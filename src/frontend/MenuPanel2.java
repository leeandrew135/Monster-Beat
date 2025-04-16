package frontend;

import backend.DataProcessing;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The MenuPanel2 class represents the main menu interface of the game, allowing users to navigate
 * to different parts of the game such as starting a new game, viewing the tutorial, checking the leaderboard,
 * logging out, or exiting the game. This panel includes interactive elements like buttons and a selection
 * menu for difficulty levels, all laid out against a visually appealing background with optional animations.
 */

public class MenuPanel2 extends JPanel {
    Color cGreenBG = new Color(96, 199, 96);
    Color cRedBG = Color.red;
    JLayeredPane layeredPane;
    int animationTracker = 0;
    
    String[] difficulties = {"Easy", "Medium", "Hard"};
    int selectedDifficulty  = 1; // defualt is easy mode;

	/**
	 * Constructs the main menu panel with navigation options for the game. It initializes UI components
	 * including buttons for game actions and a combo box for difficulty selection, sets up the background
	 * and title images, and prepares animations for visual enhancement.
	 *
	 * @param frame The main application frame to facilitate panel switching based on user interaction.
	 * @param userEmail The email of the currently logged-in user, used for personalization and functionality.
	 * @throws IOException If an error occurs loading images or sound files.
	 */
	public MenuPanel2 (MainApplication frame, String userEmail) throws IOException {
        JButton startButton, leaderboardButton, tutorialButton, logoutButton, exitButton;
    	
        // configure this class which extends jpanel
        setBounds(0, 0, 1280, 770);
		setVisible(true);
		setLayout(null);
    	setBackground(cRedBG);
  
		//////////////////////////////////////////////////////////
        
        // initialise layered pane
        layeredPane = new JLayeredPane();
	    layeredPane.setBounds(0, 0, 1280, 770);
	    layeredPane.setVisible(true);
	    layeredPane.setBackground(Color.GREEN);
	    add(layeredPane);
	    
	    // initialise background
        BufferedImage spriteEasyBackground;										
		spriteEasyBackground = ImageIO.read(new File("src/resources/easyBackground.jpg"));				// read in image
		JLabel labelBackground = new JLabel(new ImageIcon(spriteEasyBackground));			// JLabel to hold image
		labelBackground.setBounds(0, 0, 1280, 770);		// (position, size)					// set bounds
		layeredPane.add(labelBackground, 10);	
		
		// initialise title image
		BufferedImage spriteGameTitle;										
		spriteGameTitle = ImageIO.read(new File("src/resources/imageGameTitle.png"));						// read in image
		JLabel labelGameTitle = new JLabel(new ImageIcon(spriteGameTitle));					// JLabel to hold image
		labelGameTitle.setBounds(241, 20, 800, 400);	// (position, size)					// set bounds
		layeredPane.add(labelGameTitle, 0);	
		
		// initialise title image
		BufferedImage spritePlayer;										
		spritePlayer = ImageIO.read(new File("src/resources/c1.png"));									// read in image
		JLabel labelPlayer = new JLabel(new ImageIcon(spritePlayer));						// JLabel to hold image
		labelPlayer.setBounds(10, 290, 364, 415);	// (position, size)						// set bounds
		layeredPane.add(labelPlayer, 0);			
				
		// initialise title image
		BufferedImage spriteEnemy;										
		spriteEnemy = ImageIO.read(new File("src/resources/m1.png"));										// read in image
		JLabel labelEnemy = new JLabel(new ImageIcon(spriteEnemy));							// JLabel to hold image
		labelEnemy.setBounds(850, 240, 350, 411);	// (position, size)						// set bounds
		layeredPane.add(labelEnemy, 0);	
	    
		// Heading label
        JLabel heading = new JLabel("Menu");
        heading.setBounds(541, 371, 100, 20);
        layeredPane.add(heading, 0);
		
        // Start button
        startButton = new JButton("Start");													// Start Button
        startButton.addActionListener(e -> {
        	try { sfx("src/resources/soundDefault.wav"); }
        	catch (IOException e2) { e2.printStackTrace(); }  
        	
        	try {
                System.out.println("pass difficulty Level " + selectedDifficulty); 			// TESTER
                frame.switchToGamePanel(userEmail, selectedDifficulty);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        startButton.setBounds(541, 391, 200, 50);
        layeredPane.add(startButton, 0);

        // difficulty toggle
        JComboBox<String> combobox1Difficulty = new JComboBox<>(difficulties);
        combobox1Difficulty.setBounds(541, 446, 200, 25);
        layeredPane.add(combobox1Difficulty, 0);
        // Get the selected item.
        combobox1Difficulty.addActionListener(e -> {
            String difficultyString = (String) combobox1Difficulty.getSelectedItem();
            System.out.println("select what in toggle: " + difficultyString);
            if (difficultyString.equals("Easy")) selectedDifficulty = 1;
            else if (difficultyString.equals("Medium")) selectedDifficulty = 2;
            else selectedDifficulty = 3;
        });

        // Tutorial button
        tutorialButton = new JButton("Tutorial");
        tutorialButton.setBounds(541, 476, 200, 50);
        layeredPane.add(tutorialButton, 0);
        tutorialButton.addActionListener(e ->  {
			try { sfx("src/resources/soundDefault.wav"); }
        	catch (IOException e2) { e2.printStackTrace(); }  
			try {
				frame.switchToTutorialPanel(userEmail);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        

		// Leaderboard button
		leaderboardButton = new JButton("Leaderboard");
		leaderboardButton.setBounds(541, 531, 200, 50);
		leaderboardButton.addActionListener(e ->  {
			try { sfx("src/resources/soundDefault.wav"); }
        	catch (IOException e2) { e2.printStackTrace(); }  
			frame.switchToLeaderBoardPanel(userEmail); 
		}); // Pass userEmail as an argument
		layeredPane.add(leaderboardButton, 0);


		// Logout Button
        logoutButton = new JButton("Logout");
        logoutButton.setBounds(541, 586, 200, 50);
        logoutButton.addActionListener(e -> {
        	try { sfx("src/resources/soundDefault.wav"); }
        	catch (IOException e2) { e2.printStackTrace(); }  
        	
			try {
				frame.switchToLogInPanel();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
        layeredPane.add(logoutButton, 0);
        
        // Exit Button
        exitButton = new JButton("Exit");
        exitButton.setBounds(541, 641, 200, 50);
        exitButton.addActionListener(e -> {  
        	try { sfx("src/resources/soundDefault.wav"); }
        	catch (IOException e2) { e2.printStackTrace(); }
        	System.exit(0);
        });
        layeredPane.add(exitButton, 0);
        
        //////////////////////////////////////////////////////////
        
        // Timer config for animation
        Timer timer = new Timer(500, new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		animate(labelPlayer, labelEnemy);
        		animationTracker = (animationTracker + 1) % 4;
        	}
        });
        timer.start(); // Start the countdown

    } // constructor end
	/**
	 * Plays a sound effect from a given file path. This method enhances user interaction by providing
	 * audio feedback for actions within the menu.
	 *
	 * @param filename The path to the sound file to be played.
	 * @throws IOException If there's an issue accessing or playing the sound file.
	 */

	public void sfx(String filename) throws IOException {
    	Clip clip;
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File(filename)));
	        clip.start();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
	/**
	 * Animates character and enemy images on the menu screen by cyclically moving their positions.
	 * This method is intended to add dynamic visual effects to the menu.
	 *
	 * @param labelPlayer The JLabel containing the player's image.
	 * @param labelEnemy The JLabel containing the enemy's image.
	 */

	private void animate(JLabel labelPlayer, JLabel labelEnemy) {		// sequences the title screen animation
    	if (animationTracker == 0) {
    		moveNE(labelPlayer);
    		moveSW(labelEnemy);
    	}
    	else if (animationTracker == 1) {
    		moveSE(labelPlayer);
    		moveNE(labelEnemy);
    	}
    	else if (animationTracker == 2) {
    		moveNW(labelPlayer);
    		moveSE(labelEnemy);
    	}
    	else {
    		moveSW(labelPlayer);
    		moveNW(labelEnemy);
    	}
    }
	/**
	 * Moves the provided JLabel image northeast by adjusting its position on the panel. This method is part of
	 * the animation logic to create movement effects for images.
	 *
	 * @param image The JLabel to be moved.
	 */

	private void moveNE(JLabel image) {									// moves JLabel 50 px in NE
    	int newX = getX(image);
    	int newY = getY(image);
    	
    	newX += 50;
    	newY -= 50;
    	
    	image.setLocation(newX, newY);
    }
    private void moveSE(JLabel image) {									// moves JLabel 50 px in SE 
    	int newX = getX(image);
    	int newY = getY(image);
    	
    	newX += 50;
    	newY += 50;
    	
    	image.setLocation(newX, newY);
    }
    private void moveSW(JLabel image) {									// moves JLabel 50 px in SW 
    	int newX = getX(image);
    	int newY = getY(image);
    	
    	newX -= 50;
    	newY += 50;
    	
    	image.setLocation(newX, newY);
    }
    private void moveNW(JLabel image) {									// moves JLabel 50 px in NW 
    	int newX = getX(image);
    	int newY = getY(image);
    	
    	newX -= 50;
    	newY -= 50;
    	
    	image.setLocation(newX, newY);
    }
    
    private int getX(JLabel image) {									// get x pos of JLabel 
    	Point location = image.getLocation();
    	return location.x;
    }
    private int getY(JLabel image) {									// get y pos of JLabel 
    	Point location = image.getLocation();
    	return location.y;
    }
} // class end
