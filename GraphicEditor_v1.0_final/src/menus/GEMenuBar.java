package menus;

import java.awt.Component;
import javax.swing.JMenuBar;
import constants.GEConstant.EMenu;
import frames.GEPanel;

public class GEMenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	// Constructor
	public GEMenuBar() { 
			// Add Menus
		for(EMenu eMenu: EMenu.values()) {
			GEMenu menu = eMenu.newMenu();
			menu.setText(eMenu.getName());
			this.add(menu);
		}
	}
		
	// Initialization
	public void init(GEPanel drawingPanel) {
		for(Component component: this.getComponents()) {
			GEMenu menu = (GEMenu) component;
			menu.init(drawingPanel);
		}
	}

}
