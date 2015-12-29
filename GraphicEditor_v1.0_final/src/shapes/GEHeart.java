package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;

import constants.GEConstant.EDrawingType;

public class GEHeart extends GEShape {
	private static final long serialVersionUID = 1L;

	public GEHeart() {
		super(EDrawingType.TwoPoint);
	}
	
	public void draw(Graphics g, int x, int y, int endX, int endY) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawArc(x, y, (endX-x)/2, (endY-y)/2, 0, 180);
		g2D.drawArc((endX+x)/2, y, (endX-x)/2, (endY-y)/2, 0 , 180);
		g2D.drawArc(x-(endX-x)/8, y-(endY-y)*11/4, (endX-x)*9/2, (endY - y)*9/2,199, 25);
		g2D.drawArc(endX+(endX-x)/8-(endX-x)*9/2, y-(endY-y)*11/4, (endX-x)*9/2, (endY - y)*9/2, 316 , 25);
	}
	
	@Override
	public GEShape cloneShape() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setOrgin(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void movePoint(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub

	}

}
