package shapes;

import java.awt.geom.Ellipse2D;

import constants.GEConstant.EDrawingType;

public class GEEllipse extends GEShape {
	private static final long serialVersionUID = 1L;
	
	public GEEllipse() {
		super(EDrawingType.TwoPoint);
		this.shape = new Ellipse2D.Double();
	}
	@Override
	public void setOrgin(int x, int y) {
		Ellipse2D.Double ellipse = (Ellipse2D.Double) this.shape;
		ellipse.x = x;
		ellipse.y = y;		
	}
	@Override
	public void movePoint(int x, int y) {
		Ellipse2D.Double ellipse = (Ellipse2D.Double) this.shape;
		ellipse.height = y -ellipse.y;
		ellipse.width = x-ellipse.x;
	}
	@Override
	public void addPoint(int x, int y) {		
	}
	@Override
	public GEShape cloneShape() {
		// TODO Auto-generated method stub
		return new GEEllipse();
	}
}
