package transformers;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import shapes.GEShape;
import util.GEGroup;


public class GERotater extends GETransformer{
	private Point centerPoint;
	public GERotater() {
		super();
	}
	@Override
	public void initTransforming(int x, int y) {
		this.setOldPoint(x, y);
		centerPoint = new Point(
				(int)shapeManager.getBounds().getCenterX(), 
				(int)shapeManager.getBounds().getCenterY());
	}	
	private double computeRotationAngle(Point startPoint, Point previousPoint, Point currentPoint) {
		double startAngle = Math.toDegrees(
				Math.atan2(startPoint.getX()-previousPoint.getX(), startPoint.getY()-previousPoint.getY()));
		double endAngle = Math.toDegrees(
				Math.atan2(startPoint.getX()-currentPoint.getX(), startPoint.getY()-currentPoint.getY()));
		double angle = startAngle-endAngle;
		if (angle<0) angle += 360;
		return angle;
	}
	@Override
	public void keepTransforming(Graphics2D g2D, int x, int y) {
		AffineTransform saveAt = g2D.getTransform();
		g2D.translate(this.getAnchorPoint().getX(), this.getAnchorPoint().getY());
		double rotationAngle = computeRotationAngle(centerPoint, oldPoint, new Point(x, y));
		affineTransform.setToRotation(Math.toRadians(rotationAngle), centerPoint.getX(), centerPoint.getY());
		
		if(getShapeManager() instanceof GEGroup) {
			for(GEShape shape : ((GEGroup) getShapeManager()).getGroupShape()) {
				shape.draw(g2D);
				shape.setShape(affineTransform.createTransformedShape(shape.getShape()));
				if (shape.isSelected()) {
					shape.getAnchors().setTransformedShape(affineTransform);
				}
			}
		}
		this.getShapeManager().draw(g2D);
		getShapeManager().setShape(affineTransform.createTransformedShape(getShapeManager().getShape()));
		if (getShapeManager().isSelected()) {
			getShapeManager().getAnchors().setTransformedShape(affineTransform);
		}
		this.setOldPoint(x, y);
		this.getShapeManager().draw(g2D);
		g2D.setTransform(saveAt);
	}

	@Override
	public void finishTransforming(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addPoint(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
