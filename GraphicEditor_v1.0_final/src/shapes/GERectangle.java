package shapes;

import java.awt.Rectangle;

import constants.GEConstant.EDrawingType;

public class GERectangle extends GEShape {
	private static final long serialVersionUID = 1L;
	
	public GERectangle() {
		super(EDrawingType.TwoPoint);
		this.shape = new Rectangle();
	}
	@Override
	public void setOrgin(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setLocation(x, y);
		rectangle.setSize(0, 0);
	}
	@Override
	public void movePoint(int x, int y) {
		Rectangle rectangle = (Rectangle) this.shape;
		rectangle.setSize(x-rectangle.x, y-rectangle.y);
	}
	@Override
	public void addPoint(int x, int y) {		
	}
	@Override
	public GEShape cloneShape() {
		// TODO Auto-generated method stub
		return new GERectangle();
	}
}
