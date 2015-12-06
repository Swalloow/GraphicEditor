import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class GEEditMenu extends JMenu {
	public GEEditMenu(String menuString) {
		super(menuString);
		JMenuItem undoItem = new JMenuItem("Undo");
		this.add(undoItem);
		JMenuItem redoItem = new JMenuItem("Redo");
		this.add(redoItem);
		JMenuItem cutItem = new JMenuItem("Cut");
		this.add(cutItem);
		JMenuItem copyItem = new JMenuItem("Copy");
		this.add(copyItem);
		JMenuItem pasteItem = new JMenuItem("Paste");
		this.add(pasteItem);
		JMenuItem deleteItem = new JMenuItem("Delete");
		this.add(deleteItem);
		JMenuItem selectallItem = new JMenuItem("Select all");
		this.add(selectallItem);
	}
}
