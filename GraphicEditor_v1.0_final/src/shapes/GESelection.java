package shapes;

import java.util.Vector;

public class GESelection extends GERectangle {
	private static final long serialVersionUID = 1L;

	public void selectShapes(Vector<GEShape> shapes) {
		for(GEShape shapeManager: shapes)  {
			if(shape.contains(shapeManager.getBounds())) {
				shapeManager.setSelected(true);
			}
		}
	}
	
	public GEShape cloneShape() {
		return new GESelection();
	}
}
