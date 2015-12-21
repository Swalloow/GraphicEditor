package shapes;

import java.awt.Polygon;

public class GEPolygon extends GEShape {

	private static final long serialVersionUID = 1L;
	private Polygon shape;
	
	public GEPolygon() {
		super(new Polygon());
		this.shape = (Polygon)this.getShape();
	}
	@Override
	public void setPoint(int x, int y) {
		this.shape.addPoint(x, y);
	}
	@Override
	public void addPoint(int x, int y) {
		this.shape.addPoint(x, y);
	}
	@Override
	public void movePoint(int x, int y) {
		this.shape.xpoints[this.shape.npoints-1] = x;
		this.shape.ypoints[this.shape.npoints-1] = y;		
	}
	@Override
	public void moveShape(int dx, int dy) {
		this.shape.translate(dx, dy);
	}
	@Override
	public void resizeShape(int dw, int dh) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void rotateShape(int dw, int dh) {
		// TODO Auto-generated method stub
		
	}
}
