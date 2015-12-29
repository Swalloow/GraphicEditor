package shapes;

import java.awt.Polygon;

import constants.GEConstant.EDrawingType;

public class GEPolygon extends GEShape {
	private static final long serialVersionUID = 1L;
	public GEPolygon() {
		super(EDrawingType.NPoint);
		this.shape = new Polygon();
	}
	@Override
	public void setOrgin(int x, int y) {
		Polygon polygon = (Polygon) this.shape;
		polygon.addPoint(x, y);
		polygon.addPoint(x, y);
	}
	@Override
	public void addPoint(int x, int y) {
		Polygon polygon = (Polygon) this.shape;
		polygon.addPoint(x, y);
	}
	@Override
	public void movePoint(int x, int y) {
		Polygon polygon = (Polygon) this.shape;
		polygon.xpoints[polygon.npoints-1] = x;
		polygon.ypoints[polygon.npoints-1] = y;
	}
	@Override
	public GEShape cloneShape() {
		// TODO Auto-generated method stub
		return new GEPolygon();
	}

}
