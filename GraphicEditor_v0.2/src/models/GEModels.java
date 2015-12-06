package models;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import shapes.GEShape;

public class GEModels {
	
	static private Vector<GEShape> shapes = new Vector<GEShape>();
	
	static public void setShapes(Vector<GEShape> shapes) {GEModels.shapes = shapes;}
	static public Vector<GEShape> getShapes() {return GEModels.shapes;}
	
	static public void read(String fileName) {
		try {
			FileInputStream fileInputStream = new FileInputStream(fileName);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);			
			GEModels.shapes = (Vector<GEShape>) objectInputStream.readObject();			
			objectInputStream.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	static public void write(String fileName) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(fileName);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
			ObjectOutputStream objectOutputStream =  new ObjectOutputStream(bufferedOutputStream);
			objectOutputStream.writeObject(GEModels.shapes);
			objectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
