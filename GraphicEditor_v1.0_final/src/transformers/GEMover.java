package transformers;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import shapes.GEShape;
import util.GEGroup;

public class GEMover extends GETransformer {
	public GEMover() {
		super();
	}
	@Override
	public void initTransforming(int x, int y) {
		this.setOldPoint(x, y);
	}
	@Override
	public void keepTransforming(Graphics2D g2D, int x, int y) {
		AffineTransform saveAt = g2D.getTransform();
		g2D.translate(this.getAnchorPoint().getX(), this.getAnchorPoint().getY());
		affineTransform.setToTranslation(x-this.oldPoint.x, y-this.oldPoint.y);
		if(getShapeManager() instanceof GEGroup) {
			for(GEShape shape : ((GEGroup) getShapeManager()).getGroupShape()) {
				shape.draw(g2D);
				shape.setShape(affineTransform.createTransformedShape(shape.getShape()));
				if (shape.isSelected()) {
					shape.getAnchors().setTransformedShape(affineTransform);
				}
				shape.draw(g2D);
			}
		} 
		shapeManager.draw(g2D);
		shapeManager.setShape(affineTransform.createTransformedShape(shapeManager.getShape()));
		if (shapeManager.isSelected()) {
			shapeManager.getAnchors().setTransformedShape(affineTransform);
		}
		shapeManager.draw(g2D);
		setOldPoint(x, y);
		g2D.setTransform(saveAt);
	}
	@Override
	public void finishTransforming(int x, int y) {
	}
	@Override
	public void addPoint(int x, int y) {
	}

}
