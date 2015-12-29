package transformers;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import shapes.GEAnchors;
import shapes.GEShape;
import util.GEGroup;
import constants.GEConstant.EAnchorType;

public class GEResizer extends GETransformer {

	public GEResizer() {
		super();
	}

	@Override
	public void initTransforming(int x, int y) {
		oldPoint = new Point(x, y);
		// compute anchor point for resizing
		anchorPoint = getResizeAnchor();
		// normalize anchor point for scale
		affineTransform.setToTranslation(-anchorPoint.getX(), -anchorPoint.getY());
		if(shapeManager instanceof GEGroup) {
			for(GEShape shape : ((GEGroup) shapeManager).getGroupShape()) {
				shape.setShape(affineTransform.createTransformedShape(shape.getShape()));
				if(shape.isSelected()) {
					shape.getAnchors().setTransformedShape(affineTransform);
				}
			}
		}
		shapeManager.setShape(affineTransform.createTransformedShape(shapeManager.getShape()));
		if (shapeManager.isSelected()) {
			shapeManager.getAnchors().setTransformedShape(affineTransform);
		}
	}
	@Override
	public void keepTransforming(Graphics2D g2D, int x, int y) {
		AffineTransform saveAt = g2D.getTransform();
		Point2D resizeFactor = computeResizeFactor(oldPoint, new Point(x, y));	
		g2D.translate(this.getAnchorPoint().getX(), this.getAnchorPoint().getY());
		affineTransform.setToScale(resizeFactor.getX(), resizeFactor.getY());
		if(shapeManager instanceof GEGroup) {
			for(GEShape shape : ((GEGroup) shapeManager).getGroupShape()) {
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
		oldPoint.setLocation(x, y);
		g2D.setTransform(saveAt);
	}
	@Override
	public void finishTransforming(int x, int y) {
		affineTransform.setToTranslation(anchorPoint.getX(), anchorPoint.getY());
		if(shapeManager instanceof GEGroup) {
			for(GEShape shape : ((GEGroup) shapeManager).getGroupShape()) {
				shape.setShape(affineTransform.createTransformedShape(shape.getShape()));
				if (shape.isSelected()) {
					shape.getAnchors().setTransformedShape(affineTransform);
				}

			}
		} 
		shapeManager.setShape(affineTransform.createTransformedShape(shapeManager.getShape()));
		if (shapeManager.isSelected()) {
			shapeManager.getAnchors().setTransformedShape(affineTransform);
		}
	}
	@Override
	public void addPoint(int x, int y) {
	}
	
	private Point getResizeAnchor() {
		GEAnchors anchors = shapeManager.getAnchors();
		Point resizeAnchor = new Point();
		switch (shapeManager.getAnchorType()) { 
			case EE: resizeAnchor.setLocation(anchors.getBounds(EAnchorType.WW).getX(), 0); break;
			case WW: resizeAnchor.setLocation(anchors.getBounds(EAnchorType.EE).getX(), 0); break;
			case SS: resizeAnchor.setLocation(0, anchors.getBounds(EAnchorType.NN).getY()); break;
			case NN: resizeAnchor.setLocation(0, anchors.getBounds(EAnchorType.SS).getY()); break;
			case SE: resizeAnchor.setLocation(anchors.getBounds(EAnchorType.NW).getX(), anchors.getBounds(EAnchorType.NW).getY()); break;
			case NE: resizeAnchor.setLocation(anchors.getBounds(EAnchorType.SW).getX(), anchors.getBounds(EAnchorType.SW).getY()); break;
			case SW: resizeAnchor.setLocation(anchors.getBounds(EAnchorType.NE).getX(), anchors.getBounds(EAnchorType.NE).getY()); break;
			case NW: resizeAnchor.setLocation(anchors.getBounds(EAnchorType.SE).getX(), anchors.getBounds(EAnchorType.SE).getY()); break;
			default: break;
		}
		return resizeAnchor;
	}	
	
	private Point2D computeResizeFactor(Point previousPoint, Point currentPoint) {
		int deltaW = 0;
		int deltaH = 0;
		
		switch (shapeManager.getAnchorType()) {
			case EE: deltaW =  currentPoint.x-previousPoint.x; 	deltaH = 0; break;
			case WW: deltaW = -(currentPoint.x-previousPoint.x);	deltaH = 0; break;
			case SS: deltaW =  0; deltaH = currentPoint.y-previousPoint.y; break;
			case NN: deltaW =  0; deltaH = -(currentPoint.y-previousPoint.y); break;
			case SE: deltaW =  currentPoint.x-previousPoint.x; 	deltaH =  currentPoint.y-previousPoint.y; break;
			case NE: deltaW =  currentPoint.x-previousPoint.x; 	deltaH = -(currentPoint.y-previousPoint.y);	break;
			case SW: deltaW = -(currentPoint.x-previousPoint.x);	deltaH =  currentPoint.y-previousPoint.y; break;			
			case NW: deltaW = -(currentPoint.x-previousPoint.x);	deltaH = -(currentPoint.y-previousPoint.y);	break;
			default: break;
		}
		
		// compute resize 
		double currentW = shapeManager.getBounds().getWidth();
		double currentH = shapeManager.getBounds().getHeight();
		double xFactor = 1.0;
		double yFactor = 1.0;
		if (currentW > 0.0)
			xFactor = deltaW / currentW + xFactor;
		if (currentH > 0.0)			
			yFactor = deltaH / currentH + yFactor;
		
		return new Point2D.Double(xFactor, yFactor);
	}	
}
