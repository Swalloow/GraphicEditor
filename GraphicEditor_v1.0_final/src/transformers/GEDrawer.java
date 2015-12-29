package transformers;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class GEDrawer extends GETransformer{
	
	public GEDrawer() {
		super();
	}
	
	@Override
	public void initTransforming(int x, int y) {
		this.setOldPoint(x, y);
		shapeManager.setOrgin(x, y);
		shapeManager.setLineColor(this.lineColor);
		shapeManager.setFillColor(this.fillColor);
	}
	@Override
	public void keepTransforming(Graphics2D g2D, int x, int y) {
		AffineTransform saveAt = g2D.getTransform();
		g2D.translate(this.getAnchorPoint().getX(), this.getAnchorPoint().getY());
		shapeManager.draw(g2D);
		shapeManager.movePoint(x, y);
		shapeManager.draw(g2D);
		g2D.setTransform(saveAt);
	}
	@Override
	public void finishTransforming(int x, int y) {
	}
	@Override
	public void addPoint(int x, int y) {
		shapeManager.addPoint(x, y);
	}
}
