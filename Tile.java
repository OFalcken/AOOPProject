import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Tile extends JLabel {
	private static final String PATH = "sokoban_icons/";
	public static final String BLANK = PATH + "blank.png";
	public static final String BLANKMARKED = PATH + "blankmarked.png";
	public static final String CRATE = PATH + "crate.png";
	public static final String CRATEMARKED = PATH + "cratemarked.png";
	public static final String PLAYER = PATH + "player.png";
	public static final String WALL = PATH + "wall.png";
	
	private static boolean isText;
	
	private int x, y;
	private String content;
	
	public Tile(boolean isText) {
		Tile.isText = isText;
	}
	
	public void setContent(String content) {
		if(isText) {
			super.setText(content);
		} else {
			super.setIcon(new ImageIcon(content));
		}
		
		this.content = content;
	}
	
	public Tile getRight(Tile[][] tiles) {
		if(x == tiles[0].length-1) {
			return null;
		}
		
		return tiles[x+1][y];
	}
	
	public Tile getLeft(Tile[][] tiles) {
		if(x == 0) {
			return null;
		}
		
		return tiles[x-1][y];
	}
	
	public Tile getUp(Tile[][] tiles) {
		if(y == 0) {
			return null;
		}
		
		return tiles[x][y-1];
	}
	
	public Tile getDown(Tile[][] tiles) {
		if(y == tiles.length-1) {
			return null;
		}
		
		return tiles[x][y+1];
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String getContent() {
		return content;
	}
}
