import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GEFileMenu extends JMenu {
	public GEFileMenu(String menuString) {
		super(menuString);
		JMenuItem newItem = new JMenuItem("New");
		this.add(newItem);
		JMenuItem openItem = new JMenuItem("Open");
		this.add(openItem);
		JMenuItem saveItem = new JMenuItem("Save");
		this.add(saveItem);
		JMenuItem saveasItem = new JMenuItem("Save as");
		this.add(saveasItem);
		JMenuItem exitItem = new JMenuItem("Exit");
		this.add(exitItem);
	}
}
