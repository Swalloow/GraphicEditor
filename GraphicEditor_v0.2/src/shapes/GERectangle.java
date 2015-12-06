package shapes;

import java.awt.Rectangle;

public class GERectangle extends GEShape {
	
	private Rectangle shape;
	private int x0, y0;
	
	public GERectangle() {
		super(new Rectangle());
	}
	
	@Override
	public void initMove(int x, int y) {
		x0 = x;
		y0 = y;
	}
	@Override
	public void moveShape(int x, int y) {
		shape.setLocation(shape.x + x-x0, shape.y+y-y0);
		x0 = x;
		y0 = y;				
	}
	
	@Override
	public void setFirstPoint(int x, int y) {
		shape = (Rectangle)this.getShape();
		shape.setFrame(x, y, 0, 0);
	}
	@Override
	public void addLastPoint(int x, int y) {
		shape.setFrame(shape.getX(), shape.getY(), x-shape.getX(), y-shape.getY());		
	}
	@Override
	public void moveLastPoint(int x, int y) {
		shape.setFrame(shape.getX(), shape.getY(), x-shape.getX(), y-shape.getY());		
	}

}
