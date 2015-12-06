package constants;
import javax.swing.JMenu;

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
	
	public enum EEditMenuItem {
		copy("copy"),
		cut("cut"),
		paste("paste"),
		delete("delete"),
		ddo("ddo"),
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
		private GEShape shape;
		private EButtons(String icon, String iconSLT, GEShape shape) {
			this.icon = icon;
			this.iconSLT = iconSLT;
			this.shape = shape;
		}
		public String getIcon() {return this.icon;}
		public String getIconSLT() {return this.iconSLT;}
		public GEShape getShape() {return this.shape;}
	}	
}
