package shapes;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Vector;

import shapes.GEShape.EAnchors;

public class GEAnchors {
	// Anchor status
	private Vector<Rectangle> anchors;
	private final int AW = 4;
	private final int AH = 4;

	public GEAnchors() {
		this.anchors = new Vector<Rectangle>();
		for(int i=0; i<EAnchors.values().length-1; i++) {
			anchors.add(new Rectangle());
		}
	}
	
	public void setAnchorGeo(int x, int y, int w, int h) {
		int d = AW/2;
		for(int i=0; i<EAnchors.values().length-1; i++) {
			this.anchors.get(EAnchors.EE.ordinal()).setBounds(x+w  -d, y+h/2-d, AW, AH);
			this.anchors.get(EAnchors.WW.ordinal()).setBounds(x    -d, y+h/2-d, AW, AH);
			this.anchors.get(EAnchors.NN.ordinal()).setBounds(x+2/w-d, y    -d, AW, AH);
			this.anchors.get(EAnchors.SS.ordinal()).setBounds(x+w/2-d, y+h  -d, AW, AH);
			this.anchors.get(EAnchors.NE.ordinal()).setBounds(x+w  -d, y    -d, AW, AH);
			this.anchors.get(EAnchors.NW.ordinal()).setBounds(x    -d, y    -d, AW, AH);
			this.anchors.get(EAnchors.SE.ordinal()).setBounds(x+w  -d, y  +h-d, AW, AH);
			this.anchors.get(EAnchors.SW.ordinal()).setBounds(x    -d, y  +h-d, AW, AH);
			this.anchors.get(EAnchors.RR.ordinal()).setBounds(x+w/2-d, y-h/2-d, AW, AH);
		}
	}
	
	public void draw(Graphics2D g2D) {
		for(int i=0; i<EAnchors.values().length-1; i++) {
			g2D.draw(this.anchors.get(i));
		}
	}
	
	public EAnchors onAnchor(int x, int y) {
		for(int i=0; i<EAnchors.values().length-1; i++){
			if(this.anchors.get(i).contains(x, y)) {
				return EAnchors.values()[i];
			}
		}
		return null;
	}
}
