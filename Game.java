import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class Game {	
	private JFrame frame;
	private JPanel gamePanel;
	
	protected Tile[][] tiles;

	protected void run(String name, int xSize, int ySize) {
		tiles = new Tile[xSize][ySize];
		
		frame = new JFrame(name);
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(xSize, ySize));
        
        for(int i = 0; i < xSize; i++) {
			for(int j = 0; j < ySize; j++) {
				tiles[j][i] = new Tile(false);
				tiles[j][i].setMinimumSize(new Dimension(50, 50));
				tiles[j][i].setX(j);
				tiles[j][i].setY(i);
				gamePanel.add(tiles[j][i]);
			}
		}
		
		frame.add(gamePanel, BorderLayout.CENTER);
		
		frame.setMinimumSize(new Dimension(xSize*32, ySize*32));
		frame.setResizable(false);
		frame.setVisible(true);
	}

	protected void inform(String text) {
		JOptionPane.showMessageDialog(frame, text);
	}
	
	protected void exit() {
		frame.dispose();
	}

	public abstract void onUp();
	public abstract void onDown();
	public abstract void onLeft();
	public abstract void onRight();
	
	public void addMouseListener(MouseListener mL) {
		frame.addMouseListener(mL);
	}
	
	public void addKeyListener(KeyListener kL) {
		frame.addKeyListener(kL);
	}
}