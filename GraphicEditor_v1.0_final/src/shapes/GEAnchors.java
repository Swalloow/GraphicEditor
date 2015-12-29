package shapes;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Vector;

import constants.GEConstant;
import constants.GEConstant.EAnchorType;

public class GEAnchors implements Serializable {
	private static final long serialVersionUID = 1L;
	private Vector<Shape> anchors;
	
	public GEAnchors(Rectangle bounds) {
		anchors = new Vector<Shape>();		
		setBounds(bounds);
	}
	
	public void setBounds(Rectangle bounds) {
		anchors = new Vector<Shape>();		
		for (EAnchorType eAnchorType: EAnchorType.values()) {
			Ellipse2D.Double shape = new Ellipse2D.Double();
			shape.setFrame(getLocation(bounds, eAnchorType), new Dimension(GEConstant.ANCHOR_W, GEConstant.ANCHOR_H));
			anchors.add(shape);
		}
	}
	
	private Point getLocation(Rectangle bounds, EAnchorType eAnchorType) {
		int x = bounds.x;
		int y = bounds.y;
		int w = bounds.width;
		int h = bounds.height;
		int dw = GEConstant.ANCHOR_W/2;
		int dh = GEConstant.ANCHOR_H/2;
	
		switch(eAnchorType) {
			case NN: return new Point(x+w/2-dw, y-dh); 
			case NE: return new Point(x+w-dw, 	y-dh);
			case EE: return new Point(x+w-dw, 	y+h/2-dh);
			case SE: return new Point(x+w-dw, 	y+h-dh);
			case SS: return new Point(x+w/2-dw,	y+h-dh);
			case SW: return new Point(x-dw, 	y+h-dh);
			case WW: return new Point(x-dw, 	y+h/2-dh);
			case NW: return new Point(x-dw, 	y-dh);
			case RR: return new Point(x+w/2-dw, y-GEConstant.ANCHOR_H*5-dh);
			default: return null;
		}
		
	}
	
	public int size() { return anchors.size(); }
	public Shape get(int i) { return anchors.get(i); }
	public Rectangle getBounds(EAnchorType eAnchorType) { return anchors.get(eAnchorType.ordinal()).getBounds();}
	public void set(int i, Rectangle shape) { this.set(i, shape); }
	
	public void draw(Graphics2D g2D) {
		for(int i = 0; i<anchors.size(); i++) {
			g2D.draw(anchors.get(i));
		}
	}
	
	public EAnchorType contains(int x, int y) {
		for(int i = 0; i<anchors.size(); i++) {
			if(anchors.get(i).contains(x, y)) {
				return  EAnchorType.values()[i];
			}
		}
		return null;
	}
	
	
	public void setTransformedShape(AffineTransform affineTrnasform) {
		for (int i=0; i<anchors.size(); i++) {
			Shape transformedShape = affineTrnasform.createTransformedShape(anchors.get(i));			
			double x = transformedShape.getBounds().getCenterX();
			double y = transformedShape.getBounds().getCenterY();
			Ellipse2D.Double anchor = new Ellipse2D.Double();
			anchor.setFrameFromCenter(x, y, x+GEConstant.ANCHOR_W/2, y+GEConstant.ANCHOR_H/2);
			anchors.set(i, anchor);
		}
	}
 }
