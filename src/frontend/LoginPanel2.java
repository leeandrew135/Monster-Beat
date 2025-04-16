package frontend;

import backend.Accounts;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;
/**
 * The LoginPanel2 class provides a graphical user interface for users to log in,
 * register a new account, or exit the application. It features animated background elements
 * and sound effects for an interactive user experience.
 */

public class LoginPanel2 extends JPanel {
    public JLayeredPane layeredPane;
    private int animationTracker = 0;

	/**
	 * Constructs a new LoginPanel2 with all UI components initialized, including text fields for user email,
	 * buttons for login, registration, and exit, and sets up animations and sound effects. It configures the
	 * panel layout, adding all necessary components to a layered pane for depth-based rendering.
	 *
	 * @param frame The main application window, allowing for navigation between different panels.
	 * @throws IOException If an error occurs during loading of images or sound files.
	 */

	public LoginPanel2(MainApplication frame) throws IOException {
        JButton button0LogIn, button0NewUser, button0Exit;
        JTextField textField0UserEmail;

        // configure this class which extends jpanel
        setBounds(0, 0, 1280, 770);
		setVisible(true);
		setLayout(null);
		setBackground(Color.red);

        // since this is a type of panel,
        // initialise the layered pane and add to this
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

		// initialise player image
		BufferedImage spritePlayer;
		spritePlayer = ImageIO.read(new File("src/resources/c1.png"));									// read in image
		JLabel labelPlayer = new JLabel(new ImageIcon(spritePlayer));						// JLabel to hold image
		labelPlayer.setBounds(10, 290, 364, 415);	// (position, size)						// set bounds
		layeredPane.add(labelPlayer, 0);

		// initialise enemy image
		BufferedImage spriteEnemy;
		spriteEnemy = ImageIO.read(new File("src/resources/m1.png"));										// read in image
		JLabel labelEnemy = new JLabel(new ImageIcon(spriteEnemy));							// JLabel to hold image
		labelEnemy.setBounds(850, 240, 350, 411);	// (position, size)						// set bounds
		layeredPane.add(labelEnemy, 0);

		//////////////////////////////////////////////////////////

        // user email label
        JLabel labelEmail = new JLabel("Email:");											// set text labels
        labelEmail.setBounds(546, 456, 100, 20);
        layeredPane.add(labelEmail, 0);

        // email input field
        textField0UserEmail = new JTextField(20);											// set input text fields
        textField0UserEmail.setBounds(541, 476, 200, 50);
        layeredPane.add(textField0UserEmail, 0);

        // log in button
        button0LogIn = new JButton("Log In"); 												// Log In Button
        button0LogIn.setBounds(541, 531, 200, 50);
        layeredPane.add(button0LogIn, 0);
        button0LogIn.addActionListener(e -> {      					 						// Action Listener for the button
        	try {
				sfx("src/resources/soundDefault.wav");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

        	String userEmail = textField0UserEmail.getText();
        	if (Accounts.exist(userEmail)) {
                try {
                	Accounts.logIn(userEmail);
                    frame.switchToMenuPanel(userEmail);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                try {
					frame.switchToRegisterPanel();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
        });

        // create account button
        button0NewUser = new JButton("Create Account");
        button0NewUser.setBounds(541, 586, 200, 50);
        button0NewUser.addActionListener(e -> {
        	try {
				sfx("src/resources/soundDefault.wav");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
        	try {
				frame.switchToRegisterPanel();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        });
        layeredPane.add(button0NewUser, 0);

        // exit button
        button0Exit = new JButton("Exit");
        button0Exit.setBounds(541, 641, 200, 50);
        layeredPane.add(button0Exit, 0);
        button0Exit.addActionListener(e -> {      		// Action Listener for the button
        	try {
				sfx("src/resources/soundDefault.wav");
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
        	System.exit(0);
        });

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

    public void registerErrorMessage(JLabel message) {
    	JLabel errorMessage = message;											// set text labels
        errorMessage.setBounds(546, 700, 400, 20);
        //errorMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
    	layeredPane.add(errorMessage, 0);
    }
	/**
	 * Plays a sound effect from the specified file. It supports wav files and plays them once when called.
	 *
	 * @param filename The path and name of the sound file to play.
	 * @throws IOException If there is an error loading or playing the sound file.
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
	 * Manages the animation of player and enemy images on the login screen. This method updates
	 * their positions to create a simple animation loop, enhancing the visual appeal of the panel.
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
	 * Moves a JLabel image in the specified direction by adjusting its location.
	 * This method is part of the animation system, updating the position of images to simulate movement.
	 *
	 * @param image The JLabel image to move.
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
