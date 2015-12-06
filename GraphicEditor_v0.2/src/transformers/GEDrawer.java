package transformers;

import java.awt.Graphics;

import shapes.GEShape;

public class GEDrawer extends GETransformer {
	
	public GEDrawer(GEShape shape) {
		super(shape);
	}
	public void initTransforming(Graphics g, int x, int y) {
		shape.setFirstPoint(x, y);
		shape.addLastPoint(x, y);
		this.shape.draw(g);
	}
	public void keepTransforming(Graphics g, int x, int y) {
		this.shape.draw(g);
		this.shape.moveLastPoint(x, y);
		this.shape.draw(g);
	}
	public void continueTransforming(Graphics g, int x, int y) {
		this.shape.addLastPoint(x, y);
	}
	public void finishTransforming(Graphics g, int x, int y) {
	}

}
