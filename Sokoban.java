import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Sokoban extends Game {

	private int playerPosX, playerPosY;
	private int level;
	private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;

	boolean markedTiles[][];
	int totalMarkedTiles, placedMarkedTiles;
	
	public Sokoban(int level) {
		run("Sokoban", 5 + 5*level, 5 + 5*level);
		markedTiles = new boolean[5 + 5*level][5 + 5*level];
		
		totalMarkedTiles = 0;
		placedMarkedTiles = 0;

		loadLevel(level);
		initCharacter(level);
		
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
				
				if(keyPress == KeyEvent.VK_W) {
					tiles[playerPosX][playerPosY].getUp(tiles).setContent(Tile.CRATE);
				} else if(keyPress == KeyEvent.VK_S) {
					tiles[playerPosX][playerPosY].getDown(tiles).setContent(Tile.CRATE);
				} else if(keyPress == KeyEvent.VK_A) {
					tiles[playerPosX][playerPosY].getLeft(tiles).setContent(Tile.CRATE);
				} else if(keyPress == KeyEvent.VK_D) {
					tiles[playerPosX][playerPosY].getRight(tiles).setContent(Tile.CRATE);
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
	}

	public void initCharacter(int level){
		if(level == 1){
			setPlayerPos(1, 1);
		}
		else if(level == 2){
			setPlayerPos(14, 10);
		}
		else if(level == 3){
			setPlayerPos(10, 10);
		}
	}
	
	private void setPlayerPos(int x, int y) {
		if(markedTiles[playerPosX][playerPosY] == true) {
			tiles[playerPosX][playerPosY].setContent(Tile.BLANKMARKED);
		} else {
			tiles[playerPosX][playerPosY].setContent(Tile.BLANK);
		}
		
		playerPosX = x;
		playerPosY = y;		
		tiles[x][y].setContent(Tile.PLAYER);
	}
	
	private void move(int direction) {
		if(!canMove(direction)) {
			return;
		}
		
		Tile next = null;
		Tile nextNext = null;
		
		//Coordinates of next 		= playerPosX+deltaX, playerPosY+deltaY
		//Coordinates of nextNext 	= playerPosX+2*deltaX, playerPosY+2*deltaY
		int deltaX = 0;
		int deltaY = 0;
		
		switch(direction) {
			case UP:
				next = tiles[playerPosX][playerPosY].getUp(tiles);
				if(next != null) {
					nextNext = next.getUp(tiles);
				}
				deltaY = -1;
				break;
			case DOWN:
				next = tiles[playerPosX][playerPosY].getDown(tiles);
				if(next != null) {
					nextNext = next.getDown(tiles);
				}
				deltaY = +1;
				break;
			case LEFT:
				next = tiles[playerPosX][playerPosY].getLeft(tiles);
				if(next != null) {
					nextNext = next.getLeft(tiles);
				}
				deltaX = -1;
				break;
			case RIGHT:
				next = tiles[playerPosX][playerPosY].getRight(tiles);
				if(next != null) {
					nextNext = next.getRight(tiles);
				}
				deltaX = +1;
				break;
		}
		
		if(next.getContent() == Tile.CRATE || next.getContent() == Tile.CRATEMARKED) {
			if(markedTiles[playerPosX+2*deltaX][playerPosY+2*deltaY] == true) {
				nextNext.setContent(Tile.CRATEMARKED);
				changePlacedTiles(+1);
			} else {
				nextNext.setContent(Tile.CRATE);
			}
		}
		
		//Store content of next tile to check if it is marked
		String nextContent = tiles[playerPosX+deltaX][playerPosY+deltaY].getContent();
		
		//Move player and update screen
		setPlayerPos(playerPosX+deltaX, playerPosY+deltaY);
		
		//If it was marked, update the placedMarkedTiles variable and potentially end the game
		if(nextContent == Tile.CRATEMARKED) {
			changePlacedTiles(-1);
		}
	}
	
	private boolean canMove(int direction) {
		Tile next = null;
		Tile nextNext = null;
		
		switch(direction) {
			case UP:
				next = tiles[playerPosX][playerPosY].getUp(tiles);
				if(next != null) {
					nextNext = next.getUp(tiles);
				}
				break;
			case DOWN:
				next = tiles[playerPosX][playerPosY].getDown(tiles);
				if(next != null) {
					nextNext = next.getDown(tiles);
				}
				break;
			case LEFT:
				next = tiles[playerPosX][playerPosY].getLeft(tiles);
				if(next != null) {
					nextNext = next.getLeft(tiles);
				}
				break;
			case RIGHT:
				next = tiles[playerPosX][playerPosY].getRight(tiles);
				if(next != null) {
					nextNext = next.getRight(tiles);
				}
				break;
		}
		
		//On border
		if(next == null) {
			return false;
		}
		
		String nextContent = next.getContent();
		String nextNextContent = nextNext == null ? "" : nextNext.getContent();
		
		//If next space is empty
		if(nextContent == Tile.BLANK || nextContent == Tile.BLANKMARKED) {
			return true;
		}
		
		//If next is crate and nextNext is empty
		if((nextContent == Tile.CRATE || nextContent == Tile.CRATEMARKED) && (nextNextContent == Tile.BLANK || nextNextContent == Tile.BLANKMARKED)) {
			return true;
		}
		
		return false;
	}
	
	private void loadLevel(int level) {
		this.level = level;
		for(int i = 0; i < tiles.length; i++) {
			for(int j = 0; j < tiles[0].length; j++) {
				String val = Tile.BLANK;

				tiles[j][i].setContent(val);

				if(val == Tile.BLANKMARKED || (i%4 == 0 && j==3)) {
					totalMarkedTiles++;
					markedTiles[j][i] = true;
					tiles[j][i].setContent(Tile.BLANKMARKED);
				}
			}
		}
	}
	
	private void changePlacedTiles(int delta) {
		placedMarkedTiles += delta;
		
		if(placedMarkedTiles == totalMarkedTiles) {
			inform("You win!");
			exit();
			//Restart the game
			Main.main(null);
		}
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
