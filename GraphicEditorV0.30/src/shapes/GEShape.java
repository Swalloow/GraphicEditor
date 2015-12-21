package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;

abstract public class GEShape implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	
	public static enum EAnchors {NN,SS,EE,WW,NE,NW,SE,SW,MM,RR,XX};
	private GEAnchors anchors;
	private EAnchors eSelectedAnchor;
	public EAnchors geteSelectedAnchor() {return eSelectedAnchor;}
	
	private boolean selected;
	public boolean isSelected() {return selected;}
	public void setSelected(boolean selected) {
		this.selected = selected;
		if(this.selected) {
			this.anchors = new GEAnchors();
		} else {
			this.anchors = null;
		}
	}

	private Shape shape;	
	public Shape getShape() {return this.shape;}
	
	public GEShape(Shape shape) {
		this.shape = shape;
		eSelectedAnchor = null;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		g2D.setXORMode(g2D.getBackground());
		g2D.draw(this.shape);
		
		if(this.selected) {
			Rectangle boundingRect = (Rectangle) shape.getBounds();
			this.anchors.setAnchorGeo(boundingRect.x, boundingRect.y, boundingRect.width, boundingRect.height);
			this.anchors.draw(g2D);
		}
	}
	
	public boolean onShape(int x, int y) {
		if (this.selected) {
			this.eSelectedAnchor = this.anchors.onAnchor(x, y);
			if (this.eSelectedAnchor != null) {
				return true;
			}
		}
		if (this.shape.contains(x, y)) {
			this.eSelectedAnchor = EAnchors.MM;
			return true;
		}
		return false;
	}
	
	abstract public void setPoint (int x, int y);
	abstract public void addPoint (int x, int y);
	abstract public void movePoint (int x, int y);
	abstract public void moveShape (int dx, int dy);
	abstract public void resizeShape(int dw, int dh);
	abstract public void rotateShape(int dw, int dh);
}
