package shapes;

import java.awt.Rectangle;

public class GERectangle extends GEShape {

	private static final long serialVersionUID = 1L;
	private Rectangle shape;
	
	public GERectangle() {
		super(new Rectangle());
		shape = (Rectangle)this.getShape();
	}
	
	@Override
	public void setPoint(int x, int y) {
		this.shape.setLocation(x, y);
	}
	@Override
	public void addPoint(int x, int y) {
		this.shape.setSize(x-this.shape.x, y-this.shape.y);
	}
	@Override
	public void movePoint(int x, int y) {
		this.shape.setSize(x-this.shape.x, y-this.shape.y);
	}
	@Override
	public void moveShape(int dx, int dy) {
		this.shape.setLocation(shape.x + dx, shape.y + dy);
	}

	@Override
	public void resizeShape(int dw, int dh) {
		this.shape.width = this.shape.width + dw;
		this.shape.height = this.shape.height + dh;
	}

	@Override
	public void rotateShape(int dw, int dh) {
		// TODO Auto-generated method stub
		
	}
	
}
