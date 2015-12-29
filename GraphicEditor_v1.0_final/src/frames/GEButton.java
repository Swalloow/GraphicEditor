package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JRadioButton;

import constants.GEConstant.EButton;
import shapes.GEShape;

public class GEButton extends JRadioButton {
	private static final long serialVersionUID = 1L;
	private GEPanel drawingPanel;
	private ActionHandler actionHandler;
	
	public GEButton(EButton ebutton) {
		actionHandler = new ActionHandler();
		this.setIcon(new ImageIcon(ebutton.getIconName()));
		this.setSelectedIcon(new ImageIcon(ebutton.getIconSLTName()));
		this.addActionListener(actionHandler);
		this.setActionCommand(ebutton.name());
	}
	
	public void init(GEPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	public class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GEShape tool = EButton.valueOf(e.getActionCommand()).newTool();
			drawingPanel.setCurrentTool(tool);
		}
	}

}
