package frontend;


import backend.Accounts;
import backend.DataProcessing;
import backend.Game;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Result page of the game. 
public class ResultPanel extends JPanel {

    private JTextArea resultTextArea;

    public ResultPanel(MainApplication frame, Game game, int timeLeft, String userEmail) throws IOException {
        int score = game.calculateMarks(timeLeft);
        // Creating the text area for displaying results
        resultTextArea = new JTextArea("" + score);
        resultTextArea.setEditable(false); // Make the text area non-editable
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);

        add(resultTextArea);
        
        JButton buttonPause = new JButton("Back to Menu"); 									// set button
		buttonPause.setBounds(10, 10, 130, 40);	
		buttonPause.addActionListener(e -> {
			try { sfx("src/resources/soundDefault.wav"); }
        	catch (IOException e2) { e2.printStackTrace(); }  
			
            try {
				frame.switchToMenuPanel(userEmail);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
		add(buttonPause, 0);
    } 
	private int marks;
    public JPanel buttonPanel = new JPanel();
    public ResultPanel(MainApplication frame, Game game, int timeLeft) throws IOException {
        JButton button0Back;
        marks = game.calculateMarks(timeLeft);
        if (game.difficultyLevel == 1) {
        	if (marks > Accounts.getUser().getLevel1HighestScore())
        	Accounts.getUser().setLevel1HighestScore(marks);
        }
        else if (game.difficultyLevel == 2) {
        	if (marks > Accounts.getUser().getLevel2HighestScore())
        	Accounts.getUser().setLevel2HighestScore(marks);
        }
        else if (game.difficultyLevel == 3) {
        	if (marks > Accounts.getUser().getLevel3HighestScore())
        	Accounts.getUser().setLevel3HighestScore(marks);
        }
        Accounts.save();

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Or X_AXIS for horizontal arrangement
        buttonPanel.setPreferredSize(new Dimension(1220, 200));
        buttonPanel.setMinimumSize(new Dimension(1220, 200));
        buttonPanel.setMaximumSize(new Dimension(1220, 200));

        
        JLabel marksLabel = new JLabel("Game ended, your mark is : "+ marks);
        marksLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(marksLabel);
        
        JLabel timeLeftLabel = new JLabel("Time left: " + timeLeft + " seconds");
        timeLeftLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(timeLeftLabel);

        button0Back = new JButton("Go Back");
        button0Back.addActionListener(e -> {
        	try { sfx("src/resources/soundDefault.wav"); }
        	catch (IOException e2) { e2.printStackTrace(); }
        	try {
        		frame.switchToMenuPanel(game.character.toString());
        	}
        	catch (Exception e1) {
				e1.printStackTrace();
			}
        });
        button0Back.setSize(40, 60);
        button0Back.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(button0Back);

        add(buttonPanel);
        add(Box.createVerticalGlue());
    } // constructor end
    
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