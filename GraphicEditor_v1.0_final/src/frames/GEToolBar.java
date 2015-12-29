package frames;

import javax.swing.ButtonGroup;
import javax.swing.JToolBar;

import constants.GEConstant;
import constants.GEConstant.EButton;


public class GEToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	//components
	private ButtonGroup buttonGroup;
	
	//associations
	private GEPanel drawingPanel;
	
	public GEToolBar() {
		this.setSize(GEConstant.TOOLBAR_W, GEConstant.TOOLBAR_H);
		buttonGroup = new ButtonGroup();
	}
	
	public void init(GEPanel drawingPanel) {
		//set associations
		this.drawingPanel = drawingPanel;
		
		for(EButton eButton: EButton.values()) {
			GEButton button = new GEButton(eButton);
			button.init(this.drawingPanel);
			this.add(button);
			buttonGroup.add(button);
		}
		
		//set association attributes
		GEButton button= (GEButton)this.getComponentAtIndex(EButton.Rectangle.ordinal());
		button.doClick();
	}

}
