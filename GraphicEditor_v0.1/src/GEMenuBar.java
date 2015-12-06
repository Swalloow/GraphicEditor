import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class GEMenuBar extends JMenuBar {
	public GEMenuBar() {
		JMenu fileMenu = new GEFileMenu("File");
		this.add(fileMenu);
		JMenu editMenu = new GEEditMenu("Edit");
		this.add(editMenu);
		JMenu colorMenu = new JMenu("Color");
		this.add(colorMenu);
	}
}
