package menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenuItem;

import constants.GEConstant;
import constants.GEConstant.EEditMenuItem;


public class GEEditMenu extends GEMenu {
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems;
	private ActionListener actionListener; 

	public GEEditMenu() {
		actionListener = new ActionHandler();
		menuItems = new Vector<JMenuItem>();		
		for(GEConstant.EEditMenuItem eMenuItem : GEConstant.EEditMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getName());
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(menuItem.getName());
			this.menuItems.add(menuItem);
			this.add(menuItem);
		}
	}
	
	public void undo() {
		this.drawingPanel.undo();
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EEditMenuItem.copy.getName())) {
			} else if(e.getActionCommand().equals(EEditMenuItem.cut.getName())) {
			} else if(e.getActionCommand().equals(EEditMenuItem.paste.getName())) {
			} else if(e.getActionCommand().equals(EEditMenuItem.delete.getName())) {
			} else if(e.getActionCommand().equals(EEditMenuItem.redo.getName())) {
			} else if(e.getActionCommand().equals(EEditMenuItem.undo.getName())) {
				undo();
			}
		}
	}
}
