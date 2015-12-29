package menus;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JColorChooser;
import javax.swing.JMenuItem;

import constants.GEConstant;
import constants.GEConstant.EColorMenuItem;
import shapes.GEShape;

public class GEColorMenu extends GEMenu {
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems;
	private ActionListener actionListener; 
	
	public GEColorMenu() {
		actionListener = new ActionHandler();
		menuItems = new Vector<JMenuItem>();		
		for(GEConstant.EColorMenuItem eMenuItem : GEConstant.EColorMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getName());
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(menuItem.getName());
			this.menuItems.add(menuItem);
			this.add(menuItem);
		}
	}
	
	public void linecolor() {
		Color color = JColorChooser.showDialog(null, EColorMenuItem.linecolor.name(), null);
		if(color != null) {
			drawingPanel.setLineColor(color);
		}
		for(GEShape shape :drawingPanel.getShapes() ) {
			shape.setLineColor(color);
		}
	}
	
	public void fillcolor() {
		Color color = JColorChooser.showDialog(null, EColorMenuItem.fillcolor.name(), null);
		if(color != null) {
			drawingPanel.setFillColor(color);
		}
		for(GEShape shape :drawingPanel.getShapes() ) {
			shape.setFillColor(color);
		}
	}
	
	public void clearcolor() {
		for(GEShape shape :drawingPanel.getShapes() ) {
			shape.setFillColor(GEConstant.COLOR_FILL_DEFAULT);
			shape.setLineColor(GEConstant.COLOR_LINE_DEFAULT);
		}
		drawingPanel.setFillColor(GEConstant.COLOR_FILL_DEFAULT);
		drawingPanel.setLineColor(GEConstant.COLOR_LINE_DEFAULT);
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e1) {
			if(e1.getActionCommand().equals(EColorMenuItem.fillcolor.getName())) {
				fillcolor();
			} else if(e1.getActionCommand().equals(EColorMenuItem.linecolor.getName())) {
				linecolor();
			} else if(e1.getActionCommand().equals(EColorMenuItem.textcolor.getName())) {
				linecolor();
			} else if(e1.getActionCommand().equals(EColorMenuItem.clearcolor.getName())) {
				clearcolor();
			}
		}
	}
}
