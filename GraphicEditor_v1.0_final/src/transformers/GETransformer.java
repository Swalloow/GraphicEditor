package transformers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import shapes.GEShape;

public abstract class GETransformer {
	protected GEShape shapeManager;
	AffineTransform affineTransform;
	protected Point oldPoint, anchorPoint;
	protected Color lineColor, fillColor;

	public GETransformer() {
		affineTransform = new AffineTransform();
		shapeManager = null;
		oldPoint = new Point(0, 0);
		anchorPoint = new Point(0, 0);
	}
	
	// setters & getters
	public Point getOldPoint() {return oldPoint;}
	public Point getAnchorPoint() {return anchorPoint;}
	public GEShape getShapeManager() {return this.shapeManager;}
	public void setOldPoint(int x, int y) {this.oldPoint.x = x; this.oldPoint.y = y;}
	public void setAnchorPoint(int x, int y) {this.anchorPoint.x = x; this.anchorPoint.y = y;}
	public void setShapeManager(GEShape shapeManager) {this.shapeManager = shapeManager;}
	public void setLineColor(Color lineColor) { this.lineColor = lineColor; }
	public void setFillColor(Color fillColor) { this.fillColor = fillColor; }
	
	public abstract void initTransforming(int x, int y);
	public abstract void keepTransforming(Graphics2D g2D, int x, int y);
	public abstract void addPoint(int x, int y);
	public abstract void finishTransforming(int x, int y);
}
