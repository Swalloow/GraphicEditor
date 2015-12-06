package menus;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import constants.GEConstant;


public class GEColorMenu extends GEMenu {
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems;

	public GEColorMenu() {	
		menuItems = new Vector<JMenuItem>();		
		for(GEConstant.EColorMenuItem eMenuItem: GEConstant.EColorMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getName());
			menuItems.add(menuItem);
			this.add(menuItem);
		}
	}

}
