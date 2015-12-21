package constants;
import java.awt.Cursor;

import menus.GEColorMenu;
import menus.GEEditMenu;
import menus.GEFileMenu;
import menus.GEMenu;
import shapes.GEPolygon;
import shapes.GERectangle;
import shapes.GEShape;

public class GEConstant {
	
	public final static int FRAME_W = 400;
	public final static int FRAME_H = 600;
	
	public static Cursor DEFAULT_CURSOR 	= new Cursor(Cursor.DEFAULT_CURSOR);
	public static Cursor DRAW_CURSOR 		= new Cursor(Cursor.CROSSHAIR_CURSOR);
	public static Cursor MOVE_CURSOR 		= new Cursor(Cursor.MOVE_CURSOR);
	public static Cursor ROTATE_CURSOR 		= new Cursor(Cursor.HAND_CURSOR);
	public static Cursor EE_RESIZE_CURSOR 	= new Cursor(Cursor.E_RESIZE_CURSOR);
	public static Cursor WW_RESIZE_CURSOR 	= new Cursor(Cursor.W_RESIZE_CURSOR);
	public static Cursor SS_RESIZE_CURSOR 	= new Cursor(Cursor.S_RESIZE_CURSOR);
	public static Cursor NN_RESIZE_CURSOR 	= new Cursor(Cursor.N_RESIZE_CURSOR);
	public static Cursor SE_RESIZE_CURSOR 	= new Cursor(Cursor.SE_RESIZE_CURSOR);
	public static Cursor NE_RESIZE_CURSOR 	= new Cursor(Cursor.NE_RESIZE_CURSOR);
	public static Cursor SW_RESIZE_CURSOR 	= new Cursor(Cursor.SW_RESIZE_CURSOR);
	public static Cursor NW_RESIZE_CURSOR 	= new Cursor(Cursor.NW_RESIZE_CURSOR);	
	
	public static enum EDrawingState {idle, drawingTP, drawingNP, moving};

	public static enum EMenus {
		file("File", new GEFileMenu()),
		edit("Edit", new GEEditMenu()),
		color("Color", new GEColorMenu());
		
		private String menuName;
		private GEMenu menu;
		private EMenus(String menuName, GEMenu menu) {
			this.menuName = menuName;
			this.menu = menu;
		}
		public String getMenuName() {return menuName;}
		public GEMenu getMenu() {return menu;}
	}
	
	public enum EFileMenuItem {
		newFile("New"),
		open("Open"),
		save("Save"),		
		saveAs("saveAs"),		
		close("close"),	
		print("print"),	
		exit("exit");	
		private String name;		
		private EFileMenuItem(String name) {this.name = name;}
		public String getName() {return name;};
	}
	public static String SFILEKIND = "Graphics Editor";
	public static String SFILEEXTENSION = "gps";
	public static String SSAVEORNOT = "변경 내용을 저장하시겠습니까?";
	public static String SFILECONFIG = ".//config/file.config";
	public static String SWORKSPACE = ".//workspace";
	
	public enum EEditMenuItem {
		copy("copy"),
		cut("cut"),
		paste("paste"),
		delete("delete"),
		redo("redo"),
		undo("undo"),
		group("group"),
		ungroup("ungroup"),
		selectAll("selectAll");		
		private String name;		
		private EEditMenuItem(String name) {this.name = name;}
		public String getName() {return name;};
	}
	
	public enum EColorMenuItem {
		lineColor("lineColor"),
		fillColor("fillColor"),
		textColor("textColor");
		
		private String name;		
		private EColorMenuItem(String name) {this.name = name;}
		public String getName() {return name;};
	}

	public static enum EButtons {
		rectangle("rectangle.gif", "rectangleSLT.gif", new GERectangle()), 
		ellipse("ellipse.gif", "ellipseSLT.gif", new GERectangle()),
		line("line.gif", "lineSLT.gif", new GERectangle()),
		heart("heart.gif", "heartSLT.gif", new GERectangle()),
		polygon("polygon.gif", "polygonSLT.gif", new GEPolygon()),
		text("text.gif", "textSLT.gif", new GERectangle());
		
		private String icon;
		private String iconSLT;
		private GEShape tool;
		private EButtons(String icon, String iconSLT, GEShape tool) {
			this.icon = icon;
			this.iconSLT = iconSLT;
			this.tool = tool;
		}
		public String getIcon() {return this.icon;}
		public String getIconSLT() {return this.iconSLT;}
		public GEShape getTool() {return this.tool;}
	}	
}
