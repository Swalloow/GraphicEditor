package menus;
import java.awt.Component;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import constants.GEConstant;
import frames.GEPanel;


public class GEMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;

	public GEMenuBar() {
		for(GEConstant.EMenus eMenu: GEConstant.EMenus.values()) {
			GEMenu menu = eMenu.getMenu();
			menu.setText(eMenu.getMenuName());
			this.add(menu);
		}
	}

	public void init(GEPanel drawingPanel) {
		for (int i=0; i<this.getMenuCount(); i++) {
			GEMenu menu = (GEMenu) this.getMenu(i);
			menu.init(drawingPanel);
		}
		
	}
}
