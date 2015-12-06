package menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import models.GEModels;
import constants.GEConstant;
import constants.GEConstant.EFileMenuItem;

public class GEFileMenu extends GEMenu {
	private static final long serialVersionUID = 1L;

	private Vector<JMenuItem> menuItems;
	private ActionListener actionListener;

	public GEFileMenu() {	
		actionListener = new ActionHandler();
		menuItems = new Vector<JMenuItem>();		
		for(GEConstant.EFileMenuItem eMenuItem : GEConstant.EFileMenuItem.values()) {
			JMenuItem menuItem = new JMenuItem(eMenuItem.getName());
			menuItems.add(menuItem);
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(menuItem.getName());
			this.add(menuItem);
		}
	}
	
	private int SaveOrNot() {
		System.out.println(JOptionPane.CANCEL_OPTION);
		int reply=-1;
		if(drawingPanel.isCheck()==true) {
			reply = JOptionPane.showConfirmDialog(null, "변경 내용을 저장하시겠습니까?");
		}
		if(reply == JOptionPane.OK_OPTION) {
			save();
		}
		return reply;
	}
	
	private void open() {
		int reply = SaveOrNot();
		if(!(reply == JOptionPane.CANCEL_OPTION)) {
		    JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor", "gps");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	String pathName = chooser.getSelectedFile().getPath();
		    	String fileName = chooser.getSelectedFile().getName();
				GEModels.read(pathName+fileName);
				this.drawingPanel.repaint();
		    }
		}
	    
	}	
	private void save() {
	    JFileChooser chooser = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Graphics Editor", "gps");
	    chooser.setFileFilter(filter);
	    int returnVal = chooser.showSaveDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	String pathName = chooser.getSelectedFile().getPath();
	    	String fileName = chooser.getSelectedFile().getName();
			GEModels.write(pathName+fileName);
			this.drawingPanel.setCheck(false);
	    }
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EFileMenuItem.open.getName())) {
				open();
			} else if(e.getActionCommand().equals(EFileMenuItem.save.getName())) {
				save();
			} else if(e.getActionCommand().equals(EFileMenuItem.exit.getName())) {
				System.exit(1);
			}
		}
	}
}
