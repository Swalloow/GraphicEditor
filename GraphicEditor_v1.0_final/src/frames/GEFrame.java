package frames;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import constants.GEConstant;
import menus.GEMenuBar;


public class GEFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	// attributes
	
	// components
	private GEMenuBar menuBar;
	private GEToolBar toolBar;
	private GEPanel drawingPanel;
	
	// 1st phase initialization
	public GEFrame() {
		// attributes initialization
		this.setLocation(GEConstant.FRAME_X, GEConstant.FRAME_Y);
		this.setSize(GEConstant.FRAME_W, GEConstant.FRAME_H);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		// components initialization
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
		
		this.getContentPane().setLayout(new BorderLayout());
		toolBar = new GEToolBar();
		this.add(toolBar, BorderLayout.NORTH);
		drawingPanel = new GEPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
	}
	
	// 2nd phase initialization
	public void init() {
		// components association initialization
		this.menuBar.init(this.drawingPanel);
		this.toolBar.init(this.drawingPanel);
		this.drawingPanel.init();
		
		// associated attributes initialization
		this.setVisible(true);
	}
}
