package util;

import java.util.Vector;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import shapes.GEShape;

public class GEStack {
	private Vector<ByteArrayOutputStream> stack;
	private int top;
	
	public GEStack() {
		stack = new Vector<ByteArrayOutputStream>();
		top = -1;
	}
	public void init() {
		stack.clear();
		top = -1;
	}

	@SuppressWarnings("unchecked")
	public Vector<GEShape> undo() {
		if( !stack.isEmpty() && top > 0 ){
			try {
				return (Vector<GEShape>) (new ObjectInputStream(new ByteArrayInputStream(stack.get(--top).toByteArray()))).readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		top = -1;
		return (new Vector<GEShape>());
	}
	
	@SuppressWarnings("unchecked")
	public Vector<GEShape> redo() {
		if( !stack.isEmpty() && top < stack.size()-1 ){
			try {
				return (Vector<GEShape>) (new ObjectInputStream(new ByteArrayInputStream(stack.get(++top).toByteArray()))).readObject();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		if(stack.size() > 0) {
			try {
				return (Vector<GEShape>) (new ObjectInputStream(new ByteArrayInputStream(stack.get(top).toByteArray()))).readObject();
			} catch  (Exception e) {
				e.printStackTrace();
			} 
		}
		return new Vector<GEShape>();
	}
	
	public void push(Vector<GEShape> shapes) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			(new ObjectOutputStream(output)).writeObject(shapes);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		stack.setSize(top + 1);
		stack.add(output);
		top++;
	}
}
