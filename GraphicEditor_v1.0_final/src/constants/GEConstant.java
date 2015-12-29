package constants;

import java.awt.Color;

import menus.GEColorMenu;
import menus.GEEditMenu;
import menus.GEFileMenu;
import menus.GEMenu;
import shapes.GEEllipse;
import shapes.GELine;
import shapes.GEPolygon;
import shapes.GERectangle;
import shapes.GESelection;
import shapes.GEShape;
import transformers.GEDrawer;
import transformers.GEMover;
import transformers.GEResizer;
import transformers.GERotater;
import transformers.GETransformer;

public class GEConstant {
	// Frame attributes
	public final static int FRAME_X = 100; 
	public final static int FRAME_Y = 100; 
	public final static int FRAME_W = 400; 
	public final static int FRAME_H = 600; 

	// Toolbar attributes
	public final static int TOOLBAR_W = FRAME_W; 
	public final static int TOOLBAR_H = 40;
	public static final int DEFAULT_DASH_OFFSET = 4;
	public static final int DEFAULT_DASHEDLINE_WIDTH = 1;
	
	// Anchor attributes
	public static enum EAnchorType {NN, NE, EE, SE, SS, SW, WW, NW, RR;}
	public static final int ANCHOR_W = 6;
	public static final int ANCHOR_H = 6;
	
	// Color attributes
	public final static Color COLOR_LINE_DEFAULT = Color.BLACK;
	public final static Color COLOR_FILL_DEFAULT = Color.WHITE;
	
	// FileMenu constants
	public static String SFILEKIND = "Graphics Editor";
	public static String SFILEEXTENSION = "gps";
	public static String SFILECONFIG = ".//config/file.config";
	public static String SWORKSPACE = ".//workspace";
	public final static String SSAVEORNOT = "변경된 내용을 저장 하시겠습니까?";
	
	// Menu attributes
	public enum EMenu {
		File("File", new GEFileMenu()),
		Edit("Edit", new GEEditMenu() ),
		Color("Color", new GEColorMenu());
		
		private String name;
		private GEMenu menu;
		private EMenu (String name, GEMenu menu) {
			this.menu = menu;
			this.name = name;

		}
		public String getName() {return this.name;}
		public GEMenu newMenu() {return menu;}
	}

	public static enum EFileMenuItem {
		newFile("new"), 
		open("open"), 
		save("save"),
		saveAs("save as"),
		close("close"),
		print("print"),
		exit("exit");

		private String name;
		private EFileMenuItem (String name) {
			this.name = name;
		}
		public String getName() {return this.name;}
	}

	public static enum EEditMenuItem {
		redo("redo"),
		undo("undo"),
		cut("cut"), 
		copy("copy"), 
		paste("paste"),
		delete("delete"),
		group("group"),
		unGroup("ungroup"),
		selectAll("selectAll"),
		deselectAll("deselectAll");

		private String name;
		private EEditMenuItem (String name) {
			this.name = name;
		}
		public String getName() {return this.name;}
	}

	public static enum EColorMenuItem {
		fillcolor("fill color"), 
		linecolor("line color"),
		textcolor("text color"),
		clearcolor("clear color");

		private String name;
		private EColorMenuItem (String name) {
			this.name = name;
		}
		public String getName() {return this.name;}
	}

	public static enum EButton {
		Rectangle("rsc/rectangle.gif", "rsc/rectangleSLT.gif", new GERectangle()), 
		Ellipse("rsc/ellipse.gif", "rsc/ellipseSLT.gif",  new GEEllipse()), 
		Line("rsc/line.gif", "rsc/lineSLT.gif", new GELine()),
		Heart("rsc/heart.gif", "rsc/heartSLT.gif", new GERectangle()),
		Polygon("rsc/polygon.gif", "rsc/polygonSLT.gif", new GEPolygon()),
		Text("rsc/text.gif", "rsc/textSLT.gif", new GERectangle()),
		Selection("rsc/select.gif", "rsc/selectSLT.gif", new GESelection());

		private String iconName;
		private String iconSLTName;
		private GEShape tool;

		private EButton(String iconName, String iconSLTName, GEShape tool) {
			this.iconName = iconName;
			this.iconSLTName = iconSLTName;
			this.tool = tool;
		}
		public String getIconName() {return iconName;}
		public String getIconSLTName() {return iconSLTName;}
		public GEShape newTool() {return tool;}
	};

	public static enum EDrawingState {idle, NPDrawing, TPDrawing, transforming;}
	public static enum EDrawingType {TwoPoint, NPoint;}
	
	public static enum ETransformationState {
		draw(new GEDrawer()),
		move(new GEMover()),
		rotate(new GERotater()),
		resize(new GEResizer());
		
		private GETransformer transformer;
		private ETransformationState(GETransformer transformer) {
			this.transformer = transformer;
		}
		public GETransformer newTransformer() {return transformer;}
	}
}
