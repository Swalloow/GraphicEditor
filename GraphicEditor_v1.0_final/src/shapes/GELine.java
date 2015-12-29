package shapes;

import java.awt.geom.Line2D;

import constants.GEConstant.EDrawingType;

public class GELine extends GEShape {
	private static final long serialVersionUID = 1L;
	
	public GELine() {
		super(EDrawingType.TwoPoint);
		this.shape = new Line2D.Double();
	}
	@Override
	public void setOrgin(int x, int y) {
		Line2D.Double line = (Line2D.Double) this.shape;
		line.x1 = x;
		line.y1 = y;
		line.x2 = x;
		line.y2 = y;
	}
	@Override
	public void movePoint(int x, int y) {
		Line2D.Double line = (Line2D.Double) this.shape;
		line.x2 = x;
		line.y2 = y;
	}
	@Override
	public void addPoint(int x, int y) {		
	}
	@Override
	public GEShape cloneShape() {
		// TODO Auto-generated method stub
		return new GELine();
	}
}
