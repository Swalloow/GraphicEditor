package transformers;

import java.awt.Graphics;

import shapes.GEShape;

public class GEMover extends GETransformer {
	
	public GEMover(GEShape shape) {
		super(shape);
	}
	@Override
	public void initTransforming(Graphics g, int x, int y) {
		this.shape.initMove(x, y);
	}
	@Override
	public void keepTransforming(Graphics g, int x, int y) {
		this.shape.draw(g);
		this.shape.moveShape(x, y);
		this.shape.draw(g);
	}
	@Override
	public void finishTransforming(Graphics g, int x, int y) {
	}
	@Override
	public void continueTransforming(Graphics g, int x, int y) {
	}

}
