import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
public class Testawt extends Frame implements ActionListener{
	private JButton startButton;
	private JPanel gamePanel;
	private JPanel buttonPanel;
	private JFrame newFrame;
	private JLabel label;
	private JLabel gameName;
	private int count = 0;
	private JLabel character;
	
    public Testawt(){
    	super("Testawt");
    	// Create objects
        newFrame = new JFrame();
        gamePanel = new JPanel();
        buttonPanel = new JPanel(new GridLayout(2,1));
        startButton = new JButton("Clicka mig");
        label = new JLabel("antal gånger klickad = 0");
        gameName = new JLabel("Game level 1");
        character = new JLabel("jag är en label");
        
        //character 
        character.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
    			int keyCode = e.getKeyCode();
        		switch (keyCode) {
        		case KeyEvent.VK_UP:
            		// handle up arrow key press
            		break;
        		case KeyEvent.VK_DOWN:
            		// handle down arrow key press
            		break;
        		case KeyEvent.VK_LEFT:
            		// handle left arrow key press
            		break;
        		case KeyEvent.VK_RIGHT:
            		// handle right arrow key press
            		break;
        		}
			}
		});
        
        // StartButton configuration
        startButton.addActionListener(this);
        startButton.setPreferredSize(new Dimension(100,50));
        startButton.setMinimumSize(new Dimension(100, 50));
        startButton.setMaximumSize(new Dimension(100, 50));
        
        //buttonPanel configuration
        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.add(startButton);
        buttonPanel.add(label);
        
        //gamePanel configuration
        gamePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        gamePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
        		BorderFactory.createLineBorder(Color.blue,3)));
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(gameName, BorderLayout.NORTH);
        gamePanel.add(character);
        
        //set window size
        newFrame.setPreferredSize(new Dimension(500,500));
        
        newFrame.add(buttonPanel, BorderLayout.NORTH);
        newFrame.add(gamePanel, BorderLayout.CENTER);
        newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newFrame.setTitle("Sokoban Game");
        newFrame.pack();
        newFrame.setVisible(true);
        
    }
    
    public void keyPressed(KeyEvent e) {

    }
    
    public void keyRealeased(KeyEvent e) {
    	
    }

	public void actionPerformed(ActionEvent e) {
		count++;
		label.setText("antal gånger klickad = " + count);
	}
    
}
