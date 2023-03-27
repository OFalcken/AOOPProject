import java.awt.event.KeyEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Sokoban extends Game {
	
	private int playerPosX, playerPosY;
	private int level = 1;
	private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	
	public Sokoban(int level) {
		this.level = level;
			
		run("Sokoban", 5 + 5*level, 5 + 5*level);
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				int keyPress = e.getKeyCode();
				
				if(keyPress == KeyEvent.VK_UP) {
					onUp();
				} else if(keyPress == KeyEvent.VK_DOWN) {
					onDown();
				} else if(keyPress == KeyEvent.VK_LEFT) {
					onLeft();
				} else if(keyPress == KeyEvent.VK_RIGHT) {
					onRight();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
	}

	public void InitCharacter(int level){
		if(level == 1){
			setPlayerPos(0, 0);
		}
		else if(level == 2){
			setPlayerPos(25,30);
		}
		else if(level == 3){
			setPlayerPos(30,30);
		}
	}
	
	private void setPlayerPos(int x, int y) {
		tiles[playerPosX][playerPosY].setContent("sokoban_icons/crate.png");
		
		playerPosX = x;
		playerPosY = y;
		
		tiles[x][y].setContent("sokoban_icons/player.png");
	}
	
	private void move(int direction) {
		if(canMove(direction)) {
			if(direction == UP) {
				setPlayerPos(playerPosX, playerPosY-1);
			} else if(direction == DOWN) {
				setPlayerPos(playerPosX, playerPosY+1);
			}else if(direction == LEFT) {
				setPlayerPos(playerPosX-1, playerPosY);
			}else {
			setPlayerPos(playerPosX+1, playerPosY);
			}
		}
	}
	
	private boolean canMove(int direction) {
		if(direction == UP) {
			if(playerPosY == 0) {
				return false;
			}
		} else if(direction == DOWN) {
			if(playerPosY == tiles.length-1) {
				return false;
			}
		} else if(direction == LEFT) {
			if(playerPosX == 0) {
				return false;
			}
		} else if(direction == RIGHT) {
			if(playerPosX == tiles.length-1) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public void onUp() {
		move(UP);
	}

	@Override
	public void onDown() {
		move(DOWN);
	}

	@Override
	public void onLeft() {
		move(LEFT);
	}

	@Override
	public void onRight() {
		move(RIGHT);
	}
}
