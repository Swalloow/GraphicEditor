package frames;

import java.util.Vector;

import shapes.GEShape;

public class GEUndoStack {
	private final int MAXSIZE = 10;
	private Vector<Vector<GEShape>> stack;
	
	public GEUndoStack() {
		this.stack = new Vector<Vector<GEShape>>();
	}
	
	public void push(Vector<GEShape> shapes) {
		this.stack.add((Vector<GEShape>) shapes.clone());
		if (this.stack.size() > MAXSIZE) {
			this.stack.remove(0);
		}
	}
	
	public Vector<GEShape> pop() {
		if (stack.size() > 0) {
			return this.stack.remove(this.stack.size()-1);
		}
		return null;
	}
}
