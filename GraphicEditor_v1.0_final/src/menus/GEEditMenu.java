package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenuItem;

import shapes.GEShape;
import util.GEGroup;
import constants.GEConstant;
import constants.GEConstant.EEditMenuItem;

public class GEEditMenu extends GEMenu {	
	private static final long serialVersionUID = 1L;
	private Vector<GEShape> tempShapes;
	private Vector<GEShape> temp2Shapes;
	private Vector<GEGroup> groups;
	private GEGroup currentGroup;
	
	private Vector<JMenuItem> menuItems;
	private ActionListener actionListener; 

	public GEEditMenu() {
		tempShapes = new Vector<GEShape>();
		temp2Shapes = new Vector<GEShape>();
		groups = new Vector<GEGroup>();
		currentGroup = null;
		
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
	
	public void redo() {
		drawingPanel.setShapes(drawingPanel.getStackManager().redo());
	}
	
	public void undo() {
		drawingPanel.setShapes(drawingPanel.getStackManager().undo());
	}
	
	public void cut() throws CloneNotSupportedException {
		for(GEShape shape :drawingPanel.getShapes() ) {
			if(shape.isSelected()) {
				temp2Shapes.add(shape.clone());
			}
		}
		if(temp2Shapes.size()==0) {
		} else {
			tempShapes.clear();
			temp2Shapes.clear();
			for(GEShape shape :drawingPanel.getShapes() ) {
				if(shape.isSelected()) {
					tempShapes.add(shape.clone());
				}
			}
		}
		getSelectShape(new Vector<GEShape>());
	}
	
	public void paste() throws CloneNotSupportedException {
		if(!tempShapes.isEmpty()) {
			for(GEShape shape : tempShapes) {
				drawingPanel.getShapes().add(shape.clone());
			}
		}
		for(GEShape shape :drawingPanel.getShapes() ) {
			shape.setSelected(false);
		}
	}
	
	public void copy() throws CloneNotSupportedException {
		tempShapes.clear();
		for(GEShape shape :drawingPanel.getShapes() ) {
			if(shape.isSelected()) {
				tempShapes.add(shape.clone());
			}
			shape.setSelected(false);
		}
	}
	
	public void delete() {
		getSelectShape(new Vector<GEShape>());
	}
	
	public void group() {
		currentGroup = new GEGroup();
		Vector<GEShape> groupShape = new Vector<GEShape>();
		getSelectShape(groupShape);
		for(GEShape shapeManager : groupShape) {
			currentGroup.insert(shapeManager);
			shapeManager.setSelected(false);
		}
		currentGroup.setSelected(true);
		drawingPanel.getShapes().add(currentGroup);
		groups.add(currentGroup);
		drawingPanel.getStackManager().push(drawingPanel.getShapes());
	}
	
	public void unGroup(){
		for(int i=0; i< groups.size(); i++) {
			if(groups.get(i).isSelected()) {
				currentGroup = groups.get(i); 
			};
		}
		if(currentGroup != null)  {
			for(GEShape shape: currentGroup.getGroupShape()) {
				drawingPanel.getShapes().add(shape);
				shape.setGroupShapes(null);
			}
			drawingPanel.getShapes().remove(currentGroup);
			currentGroup.getGroupShape().clear();
		}
	}
	
	public void selectAll() {
		for(GEShape shapeManager : drawingPanel.getShapes()) {
			shapeManager.setSelected(true);
		}
	}
	
	public void deselectAll() {
		for(GEShape shapeManager : drawingPanel.getShapes()) {
			shapeManager.setSelected(false);
		}
	}
	
	public void getSelectShape(Vector<GEShape> shapes) {
		shapes.clear();
		for(int i = 0; i < drawingPanel.getShapes().size(); i++) {
			if(drawingPanel.getShapes().get(i).isSelected()) {
				shapes.add(drawingPanel.getShapes().get(i));
			}
		}
		for(int i = 0; i < shapes.size(); i++) {
			drawingPanel.getShapes().remove(shapes.get(i));
		}
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e1) {
			if(e1.getActionCommand().equals(EEditMenuItem.redo.getName())) {
				redo();
			} else if(e1.getActionCommand().equals(EEditMenuItem.undo.getName())) {
				undo();
			} else if(e1.getActionCommand().equals(EEditMenuItem.cut.getName())) {
				try {
					cut();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(e1.getActionCommand().equals(EEditMenuItem.copy.getName())) {
				try {
					copy();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(e1.getActionCommand().equals(EEditMenuItem.paste.getName())) {
				try {
					paste();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(e1.getActionCommand().equals(EEditMenuItem.delete.getName())) {
				delete();
			} else if(e1.getActionCommand().equals(EEditMenuItem.group.getName())) {
				group();
			} else if(e1.getActionCommand().equals(EEditMenuItem.unGroup.getName())) {
				unGroup();
			} else if(e1.getActionCommand().equals(EEditMenuItem.selectAll.getName())) {
				selectAll();
			} else if(e1.getActionCommand().equals(EEditMenuItem.deselectAll.getName())) {
				deselectAll();
			} 
			
		}
	}
}
