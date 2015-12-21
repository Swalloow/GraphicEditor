package transformers;

import java.awt.Graphics;

import shapes.GEShape;

abstract public class GETransformer {
	private GEShape shape;
	public GEShape getShape() {return shape;}
	
	public GETransformer(GEShape shape) {
		this.shape = shape;
	}
	abstract public void initTransforming(Graphics g, int x, int y);
	abstract public void keepTransforming(Graphics g, int x, int y);
	abstract public void continueTransforming(Graphics g, int x, int y);
	abstract public void finishTransforming(Graphics g, int x, int y);
}
