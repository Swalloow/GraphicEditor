import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class GEFrame extends JFrame{
	public GEFrame() {
		GEMenuBar menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
		GEToolBar toolBar = new GEToolBar();
		this.add(toolBar, BorderLayout.NORTH);
	}
}
