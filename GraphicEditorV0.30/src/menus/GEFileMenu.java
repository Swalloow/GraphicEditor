package menus;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import models.GEModel;
import constants.GEConstant;
import constants.GEConstant.EFileMenuItem;
import frames.GEPanel;

public class GEFileMenu extends GEMenu {
	private static final long serialVersionUID = 1L;

	private String currenetPath;
	private String currentFileName;
	
	private Vector<JMenuItem> menuItems;
	private ActionListener actionListener;

	public GEFileMenu() {
		this.currenetPath = ".";
		this.currentFileName = null;
		
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
	public void init(GEPanel drawingPanel) {
		super.init(drawingPanel);
		openDirectory();
	}
	
	public void openDirectory() {
		try {
			this.currenetPath = (String) GEModel.read(GEConstant.SFILECONFIG);
		} catch (ClassNotFoundException | IOException e) {
			this.currenetPath = GEConstant.SWORKSPACE;
		}
	}
	
	private void printFile() {
	}
	
	private int SaveOrNot() {
		int reply = JOptionPane.NO_OPTION;
		if (this.drawingPanel.isUpdated())
			reply = JOptionPane.showConfirmDialog(null, GEConstant.SSAVEORNOT);
		if (reply == JOptionPane.OK_OPTION)
			save();
		return reply;
	}
	
	private void newFile() {
		if (this.SaveOrNot() != JOptionPane.CANCEL_OPTION) {
			this.drawingPanel.newShapes();			
		}
	}
	enum EDialogType { OPEN, SAVE, NONE };
	private int showDialog(EDialogType eDialogType) {
	    JFileChooser chooser = new JFileChooser(this.currenetPath);
	    FileNameExtensionFilter filter = 
	    		new FileNameExtensionFilter(GEConstant.SFILEKIND, GEConstant.SFILEEXTENSION);
	    chooser.setFileFilter(filter);
	    int returnVal = JFileChooser.ERROR_OPTION;
	    
	    if (eDialogType == EDialogType.OPEN) {
	    	returnVal = chooser.showOpenDialog(null);
	    } else if (eDialogType == EDialogType.SAVE) {
	    	returnVal = chooser.showSaveDialog(null);	    	
	    } 
	    
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	    	this.currenetPath = chooser.getSelectedFile().getParent();
	    	this.currentFileName = currenetPath + "\\" + chooser.getSelectedFile().getName();
	    }
	    return returnVal;
	}
	private void open() {
		if (this.SaveOrNot() != JOptionPane.CANCEL_OPTION) {
			int returnVal = this.showDialog(EDialogType.OPEN);
		    if( returnVal == JFileChooser.APPROVE_OPTION) {
		    	this.drawingPanel.readShapes(this.currentFileName);
		    }
		}	    
	}
	private void saveAs() {
		openDirectory();
		int returnVal = this.showDialog(EDialogType.SAVE);
	    if( returnVal == JFileChooser.APPROVE_OPTION) {
	    	if (!this.currentFileName.endsWith("." + GEConstant.SFILEEXTENSION)) {
	    		this.currentFileName = this.currentFileName + "." + GEConstant.SFILEEXTENSION;
	    	}
	    	try {
				GEModel.write(GEConstant.SFILECONFIG, currenetPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.drawingPanel.saveShapes(this.currentFileName);
	    }
	}
	
	private void save() {
		if (this.drawingPanel.isUpdated()) {
			if (this.currentFileName == null) {
				this.saveAs();
			} else {
				this.drawingPanel.saveShapes(this.currentFileName);
			}
		}
	}
	private void exitSystem() {
		if (this.SaveOrNot() != JOptionPane.CANCEL_OPTION) {
			System.exit(1);			
		}
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(EFileMenuItem.newFile.getName())) {
				newFile();
			} else if(e.getActionCommand().equals(EFileMenuItem.open.getName())) {
				open();
			} else if(e.getActionCommand().equals(EFileMenuItem.save.getName())) {
				save();
			} else if(e.getActionCommand().equals(EFileMenuItem.saveAs.getName())) {
				saveAs();
			} else if(e.getActionCommand().equals(EFileMenuItem.print.getName())) {
				printFile();
			} else if(e.getActionCommand().equals(EFileMenuItem.exit.getName())) {
				exitSystem();
			}
		}
	}
}
