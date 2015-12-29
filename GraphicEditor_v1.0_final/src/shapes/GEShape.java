package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import util.GEGroup;
import constants.GEConstant.EAnchorType;
import constants.GEConstant.EDrawingType;
import constants.GEConstant.ETransformationState;

public abstract class GEShape implements Serializable , Cloneable{
	private static final long serialVersionUID = 1L;
	
	// attributes
	protected Shape shape;
	protected EDrawingType eDrawingType;
	protected ETransformationState eTransformationState;
	protected Color lineColor, fillColor;
	protected boolean selected;
	
	// anchors
	private EAnchorType eAnchorType;
	protected GEAnchors anchors;
	public GEGroup groupShapes;

	// setters & getters	
	public Shape getShape(){return shape;}
	public void setShape(Shape shape){this.shape = shape;}
	public EDrawingType getDrawingType() {return eDrawingType;}
	public EAnchorType getAnchorType() {return eAnchorType;}
	public void setTransformationType(ETransformationState eTransformationType) { this.eTransformationState = eTransformationType;}
	public ETransformationState getTransformationState() {return eTransformationState;}
	public GEAnchors getAnchors() {return this.anchors;}
	public Rectangle2D getBounds() {return shape.getBounds2D();}
	public void setLineColor(Color lineColor) {	this.lineColor = lineColor;}
	public void setFillColor(Color fillColor) {	this.fillColor = fillColor;}
	public void setGroupShapes(GEGroup groupManager) { this.groupShapes = groupManager;}
	
	// constructor
	public GEShape() {
		// TODO Auto-generated constructor stub
	}
	public GEShape(EDrawingType eDrawingType) {
		// subclass will create a shape & set types
		this.shape = null;
		this.eDrawingType = eDrawingType;
		// initial state will be draw
		this.eTransformationState = ETransformationState.draw;
		// anchors will be created when needed
		this.anchors = null;
		this.eAnchorType = null;
		this.groupShapes = null;
	}
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		Color fgColor = g2D.getColor();	
		if( !(this instanceof GESelection) && !(this instanceof GEGroup)){
			g2D.setColor(this.fillColor);
			g2D.fill(shape);
		}
		g2D.setColor(this.lineColor);
		g2D.draw(shape);
		if (selected) {
			anchors.setBounds(shape.getBounds());
			anchors.draw(g2D);
		}
		g2D.setColor(fgColor);
	}
	
	public void setSelected(boolean bSelected) {
		if(groupShapes == null) {
			selected = bSelected;
			if (selected) {
				anchors = new GEAnchors(shape.getBounds());
				this.eTransformationState = ETransformationState.move;
			} else {
				anchors = null;
			}
		} else {
			this.selected = false;
			groupShapes.setSelected(bSelected);
		}
	}
	public boolean isSelected() {
		if(groupShapes == null) {
			return selected;
		} else {
			return groupShapes.isSelected();
		}
	}
	public boolean contains(int x, int y) {
		if (selected) {
			eAnchorType = anchors.contains(x, y);
			if (eAnchorType != null) {
				if (eAnchorType==EAnchorType.RR)
					this.eTransformationState = ETransformationState.rotate;
				else
					this.eTransformationState = ETransformationState.resize;
				return true;
			}
		}
		if (shape.contains(x, y)) {
			this.eTransformationState = ETransformationState.move;
			return true;
		}
		return false;

	}

	public GEShape clone() throws CloneNotSupportedException {
        return (GEShape) super.clone();
    }
	public abstract GEShape cloneShape();
	public abstract void setOrgin(int x, int y);
	public abstract void movePoint(int x, int y);
	public abstract void addPoint(int x, int y);

}