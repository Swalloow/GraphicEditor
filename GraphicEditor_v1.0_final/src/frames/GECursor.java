package frames;
import java.awt.Cursor;

import constants.GEConstant.ETransformationState;
import shapes.GEShape;


public class GECursor {
	private GEPanel drawingPanel;
	private GEShape shapeManager;
	public GECursor() {
		// TODO Auto-generated constructor stub
	}
	public void init(GEPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	public void changeCursor(int x, int y){
		shapeManager = drawingPanel.onShape(x, y);
		if(shapeManager != null) {
			if (shapeManager.getTransformationState() == ETransformationState.move) {
				drawingPanel.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			} else if (shapeManager.getTransformationState() == ETransformationState.resize) {
				switch(shapeManager.getAnchorType()){
					case EE:drawingPanel.setCursor(new Cursor(Cursor.E_RESIZE_CURSOR));break;
					case WW:drawingPanel.setCursor(new Cursor(Cursor.W_RESIZE_CURSOR));break;
					case NN:drawingPanel.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));break;
					case SS:drawingPanel.setCursor(new Cursor(Cursor.S_RESIZE_CURSOR));break;
					case NE:drawingPanel.setCursor(new Cursor(Cursor.NE_RESIZE_CURSOR));break;
					case NW:drawingPanel.setCursor(new Cursor(Cursor.NW_RESIZE_CURSOR));break;
					case SE:drawingPanel.setCursor(new Cursor(Cursor.SE_RESIZE_CURSOR));break;
					case SW:drawingPanel.setCursor(new Cursor(Cursor.SW_RESIZE_CURSOR));break;
					default: break;
				}
			} else if (shapeManager.getTransformationState() == ETransformationState.rotate) {
				drawingPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			} 
		} else {
			drawingPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
}
