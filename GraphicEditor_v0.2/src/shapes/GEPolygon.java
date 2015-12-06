package shapes;

import java.awt.Polygon;

public class GEPolygon extends GEShape {

	private Polygon shape;
	
	public GEPolygon() {
		super(new Polygon());
	}
	@Override
	public void setFirstPoint(int x, int y) {
		shape = (Polygon)this.getShape();
		shape.addPoint(x, y);
	}
	@Override
	public void addLastPoint(int x, int y) {
		shape.addPoint(x, y);
	}
	@Override
	public void moveLastPoint(int x, int y) {
		shape.xpoints[shape.npoints-1] = x;
		shape.ypoints[shape.npoints-1] = y;		
	}
	
	@Override
	public void initMove(int x, int y) {
		// TODO Auto-generated method stub
	}
	@Override
	public void moveShape(int x, int y) {
		// TODO Auto-generated method stub
	}
}
