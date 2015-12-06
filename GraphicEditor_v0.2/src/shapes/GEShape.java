package shapes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.Serializable;

abstract public class GEShape implements Serializable {
	private Shape shape;
	
	public Shape getShape() {return shape;}
	public void setShape(Shape shape) {this.shape = shape;}
	
	public GEShape(Shape shape) {
		this.shape = shape;
	}
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		g2D.setXORMode(g2D.getBackground());
		g2D.draw(this.shape);			
	}
	public boolean onShape(int x, int y) {
		if (this.shape.contains(x, y))
			return true;
		return false;
	}
	
	abstract public void initMove (int x, int y);
	abstract public void moveShape (int x, int y);
	abstract public void setFirstPoint (int x, int y);
	abstract public void addLastPoint (int x, int y);
	abstract public void moveLastPoint (int x, int y);
}
