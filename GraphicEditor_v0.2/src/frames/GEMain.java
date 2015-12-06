package frames;
import javax.swing.JFrame;

public class GEMain {
	private static GEFrame frame;
	
	public static void main(String[] args) {
		frame = new GEFrame();
		frame.init();
		frame.setVisible(true);
	}
}
