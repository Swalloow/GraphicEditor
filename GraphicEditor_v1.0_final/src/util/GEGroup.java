package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Vector;

import shapes.GEAnchors;
import shapes.GEShape;


public class GEGroup extends GEShape{
	private static final long serialVersionUID = 1L;
	private Vector<GEShape> shapes;
	
	public GEGroup() {
		shapes = new Vector<GEShape>();
		shape = new Rectangle();
	}

	public void insert(GEShape shapeManager) {
		shapeManager.groupShapes = this;
		shapes.add(0, shapeManager);
		if( shapes.size() == 1 ) {
			shape = shapeManager.getBounds();
		} else {
			shape = shape.getBounds().createUnion(shapeManager.getBounds());
		}
	}
	
	public Vector<GEShape> getGroupShape() {return shapes;}


	public void draw(Graphics g){
		Graphics2D g2D = (Graphics2D)g;
		for(GEShape shapeManager : shapes) {
			shapeManager.draw(g2D);
		}
		if(this.isSelected()){
			Color originColor = g2D.getColor();
			g2D.setColor(Color.GRAY);
			g2D.draw(shape);
			anchors.setBounds(shape.getBounds());
			anchors.draw(g2D);
			
			g2D.setColor(originColor);
		}
		
	}
	public void setSelected(boolean bselected) {
		selected = bselected;
		if(selected) {
			anchors = new GEAnchors(shape.getBounds());
		} else {
			anchors = null;
		}
	}	
	@Override
	public GEShape cloneShape() {return new GEGroup();}

	@Override
	public void setOrgin(int x, int y) {
		for(GEShape shape: shapes) {
			shape.setOrgin(x, y);
		}
		
	}

	@Override
	public void movePoint(int x, int y) {
		for(GEShape shape: shapes) {
			shape.movePoint(x, y);
		}
	}

	@Override
	public void addPoint(int x, int y) {
		for(GEShape shape: shapes) {
			shape.addPoint(x, y);
		}
	}
	public void setLineColor(Color lineColor) {
		for(GEShape shape : shapes) {
			shape.setLineColor(lineColor);
		}
	}
	
	public void setFillColor(Color fillColor) {
		for(GEShape shape : shapes) {
			shape.setFillColor(fillColor);
		}
	}

}
