import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Tile extends JLabel {
	private static boolean isText;
	
	public Tile(boolean isText) {
		Tile.isText = isText;
	}
	
	public void setContent(String content) {
		if(isText) {
			super.setText(content);
		} else {
			super.setIcon(new ImageIcon(content));
		}
	}
}
