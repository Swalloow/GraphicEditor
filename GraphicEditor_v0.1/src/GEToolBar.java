import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

public class GEToolBar extends JToolBar {
	
	public GEToolBar() {
		ButtonGroup buttonGroup = new ButtonGroup();
		this.setSize(400, 100);
		JRadioButton button1 = new JRadioButton();
		button1.setIcon(new ImageIcon("rsc/rectangle.png"));
		button1.setSelectedIcon(new ImageIcon("rsc/rectangleSLT.png"));
		this.add(button1);
		buttonGroup.add(button1);
		
		JRadioButton button2 = new JRadioButton();
		button2.setIcon(new ImageIcon("rsc/triangle.png"));
		button2.setSelectedIcon(new ImageIcon("rsc/triangleSLT.png"));
		this.add(button2);
		buttonGroup.add(button2);
		
		JRadioButton button3 = new JRadioButton();
		button3.setIcon(new ImageIcon("rsc/circle.png"));
		button3.setSelectedIcon(new ImageIcon("rsc/circleSLT.png"));
		this.add(button3);
		buttonGroup.add(button3);
	}
}
