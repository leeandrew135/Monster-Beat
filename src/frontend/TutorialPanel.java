package frontend;

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
 * Displays a tutorial panel within the application, offering guidance or instructions to the user.
 * It includes visual aids and a return button to navigate back to the main menu.
 */

public class TutorialPanel extends JPanel {
	
	JLayeredPane layeredPane;
	/**
	 * Constructs the tutorial panel, loading and displaying tutorial imagery and setting up navigation controls.
	 *
	 * @param frame The main application frame for navigating between panels.
	 * @param userEmail The email address of the user, used for potential personalization or tracking.
	 * @throws IOException If an error occurs loading images or sound files.
	 */

	public TutorialPanel(MainApplication frame, String userEmail) throws IOException {
		JButton returnButton;
		
		// configure this class which extends jpanel
        setBounds(0, 0, 1280, 770);
		setVisible(true);
		setLayout(null);
    	setBackground(Color.RED);
  
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
		BufferedImage spriteTutorial;										
		spriteTutorial = ImageIO.read(new File("src/resources/imageTutorial.png"));						// read in image
		JLabel labelTutorial = new JLabel(new ImageIcon(spriteTutorial));					// JLabel to hold image
		labelTutorial.setBounds(175, 50, 932, 540);	// (position, size)					// set bounds
		layeredPane.add(labelTutorial, 0);
		
		
		
		// Return Button
        returnButton = new JButton("Return");
        returnButton.setBounds(541, 641, 200, 50);
        returnButton.addActionListener(e -> { 
        	try { sfx("src/resources/soundDefault.wav"); }
        	catch (IOException e2) { e2.printStackTrace(); }  
        	try {
				frame.switchToMenuPanel(userEmail);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        layeredPane.add(returnButton, 0);
		
        
        
        
	} // constructor end

	/**
	 * Plays a sound effect from the specified file. It supports WAV files and plays them once when invoked.
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
	
	
	
}
