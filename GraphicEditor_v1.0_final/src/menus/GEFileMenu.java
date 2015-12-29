package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import constants.GEConstant;
import constants.GEConstant.EFileMenuItem;
import models.GEModel;

public class GEFileMenu extends GEMenu {
	private static final long serialVersionUID = 1L;
	private String currentPath;
	private String currentFileName;
	
	private Vector<JMenuItem> menuItems;
	private ActionListener actionListener;
	
	public GEFileMenu() {
		this.currentFileName = null;
		this.currentPath = ".";
		
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
	
	public void init() {
		openDirectory();
	}
	
	public void openDirectory() {
		try {
			this.currentPath = (String) GEModel.read(GEConstant.SFILECONFIG);
		} catch (ClassNotFoundException | IOException e) {
			this.currentPath = GEConstant.SWORKSPACE;
		}
		
	}
	
	enum EDialogType { OPEN, SAVE, NONE };
	private int showOpenDialog(EDialogType eDialogType) {
		JFileChooser fileChooser = new JFileChooser(this.currentPath);
		FileNameExtensionFilter filter = 
				new FileNameExtensionFilter(GEConstant.SFILEKIND, GEConstant.SFILEEXTENSION);
		fileChooser.setFileFilter(filter);
		int returnVal = JFileChooser.ERROR_OPTION;
		
		if(eDialogType == EDialogType.OPEN) {
			returnVal =fileChooser.showOpenDialog(null);
		} else if(eDialogType == EDialogType.SAVE) {
			returnVal = fileChooser.showSaveDialog(null);
		}
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			this.currentPath = fileChooser.getSelectedFile().getParent();
			this.currentFileName = currentPath + "\\" + fileChooser.getSelectedFile().getName();
		}
		return returnVal;
	}
	
	private int saveOrNot() {
		int reply = JOptionPane.NO_OPTION;
    	if (drawingPanel.isbUpdated())
   			reply = JOptionPane.showConfirmDialog(null, GEConstant.SSAVEORNOT);
    	if (reply == JOptionPane.OK_OPTION)
    		save();
    	return reply;
	}
    	
	public void newFile() {
		if(this.saveOrNot() != JOptionPane.CANCEL_OPTION) {
			this.drawingPanel.newShapes();
		}
	}
	
	public void open() {
		if(this.saveOrNot() != JOptionPane.CANCEL_OPTION) {
			int returnVal = this.showOpenDialog(EDialogType.OPEN);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				this.drawingPanel.readShapes(this.currentFileName);
			}
		}
	}

	public void save() {
		if(this.drawingPanel.isbUpdated()) {
			if(this.currentFileName == null) {
				this.saveAs();
			} else {
				this.drawingPanel.saveShapes(this.currentFileName);
			}
		}
	}	
	
	public void saveAs() {
		openDirectory();
		int returnVal =this.showOpenDialog(EDialogType.SAVE);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			if(!this.currentFileName.endsWith("."+ GEConstant.SFILEEXTENSION)) {
				this.currentFileName = this.currentFileName + "." + GEConstant.SFILEEXTENSION;
			}
			try {
				GEModel.write(GEConstant.SFILECONFIG, currentPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.drawingPanel.saveShapes(this.currentFileName);
	}
	
	public void close() {
		this.newFile();
	}
	
	public void print() {
		PrinterJob job = PrinterJob.getPrinterJob();
		// show the dialog
		if (job.printDialog()) {
			try {
				job.print();
			} catch (PrinterException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void exit() {
		if(this.saveOrNot() != JOptionPane.CANCEL_OPTION) {
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
			} else if(e.getActionCommand().equals(EFileMenuItem.close.getName())) {
				close();
			} else if(e.getActionCommand().equals(EFileMenuItem.print.getName())) {
				print();
			} else if(e.getActionCommand().equals(EFileMenuItem.exit.getName())) {
				exit();
			}
		}
	}
}
	
	


