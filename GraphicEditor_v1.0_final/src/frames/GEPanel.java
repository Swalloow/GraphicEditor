package frames;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import shapes.GESelection;
import shapes.GEShape;
import transformers.GETransformer;
import util.GEStack;
import constants.GEConstant;
import constants.GEConstant.EDrawingState;
import constants.GEConstant.EDrawingType;
import constants.GEConstant.ETransformationState;
import models.GEModel;

public class GEPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	// association variable
	private GEShape currentTool;
	
	// component
	private Vector<GEShape> shapes;
	private MouseHandler mouseHandler;
	private GETransformer transformer;
	private GECursor cursorManager;
	private BasicStroke dashedLineStroke;
	private GEStack stack;
	
	// working variable
	private boolean bUpdated;
	private Color fillColor, lineColor;
	
	// setters & getters
	public void setCurrentTool(GEShape tool) {this.currentTool = tool;}
	public Vector<GEShape> getShapes(){repaint(); return this.shapes; }
	public boolean isbUpdated() {return bUpdated;}
	public void setbUpdated(boolean bUpdated) {this.bUpdated = bUpdated;}
	public void setShapes(Vector<GEShape> shapes) {this.shapes = shapes; repaint();}
	public void setLineColor(Color lineColor) {this.lineColor = lineColor;}
	public void setFillColor(Color fillColor) {this.fillColor = fillColor;}
	public GEStack getStackManager() {return stack;}
	
	public GEPanel() {
		currentTool = null;
		shapes = new Vector<GEShape>();
		mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		transformer = null;
		
		cursorManager = new GECursor();
		cursorManager.init(this);
		float dashes[] = {GEConstant.DEFAULT_DASH_OFFSET};
		dashedLineStroke = new BasicStroke(
				GEConstant.DEFAULT_DASHEDLINE_WIDTH, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 10, dashes, 0);
		stack = new GEStack();
		
		fillColor = getBackground();
		lineColor = getForeground();
		setbUpdated(false);
	}
	
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	public void newShapes() {
		this.shapes.clear();
		setbUpdated(false);
		this.repaint();
	}
	
	@SuppressWarnings("unchecked")
	public void readShapes(String fileName) {
		try {
			this.shapes = (Vector<GEShape>) GEModel.read(fileName);
			setbUpdated(false);
			this.repaint();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveShapes(String fileName) {
		try {
			GEModel.write(fileName, this.shapes);
			setbUpdated(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		// extends JPanls's paint
		super.paint(g);
		for(GEShape shape: shapes) {
			shape.draw(g);
		}
	}
	public void resetSelections(GEShape selectedShape) {
		if (selectedShape==null) {
			for(GEShape shapeManager: shapes) {
				shapeManager.setSelected(false);
			}
			repaint();
		} else {
			if (!selectedShape.isSelected()) {
				for(GEShape shapeManager: shapes) {
					shapeManager.setSelected(false);
				}
				selectedShape.setSelected(true);
				repaint();
			}
		}
	}
	public GEShape onShape(int x, int y) {
		for(GEShape shape : shapes) {
			if(shape.contains(x, y)) {
				return shape;
			}
		}
		return null;
	}
	
	public void initTransforming(int x, int y) {
		GEShape currentShape = onShape(x, y);
		resetSelections(currentShape);
		if (currentShape==null) {
			currentShape = currentTool.cloneShape();
		}
		transformer = currentShape.getTransformationState().newTransformer();
		transformer.setLineColor(lineColor);
		transformer.setFillColor(fillColor);
		transformer.setShapeManager(currentShape);
		transformer.initTransforming(x, y);
	}
	
	public void keepTransforming(int x, int y) {
		Graphics2D g2D = (Graphics2D) getGraphics();
		g2D.setXORMode(getBackground());
		g2D.setStroke(dashedLineStroke);
		transformer.keepTransforming(g2D, x, y);
	}
	
	private void continueTransforming(int x, int y) {
		transformer.addPoint(x, y);
	}
	
	public void finishTransforming(int x, int y) {
		if( ! (transformer.getShapeManager() instanceof GESelection)) {
			transformer.finishTransforming(x, y);
			if (transformer.getShapeManager().getTransformationState()==ETransformationState.draw) {
				shapes.add(transformer.getShapeManager());
				resetSelections(transformer.getShapeManager());
			} else {
				repaint();
			}
			stack.push(shapes);
			setbUpdated(true);
		} else {
			((GESelection) transformer.getShapeManager()).selectShapes(shapes);
			repaint();
		}
		
	}
	
	public class MouseHandler implements MouseInputListener {
		private EDrawingState eDrawingState = EDrawingState.idle;
		private boolean bClicked = false;
		
		@Override
		public void mousePressed(MouseEvent e) {
			bClicked = true;
			if (eDrawingState == EDrawingState.idle) {
				initTransforming(e.getX(), e.getY());
				if (transformer.getShapeManager().getTransformationState()==ETransformationState.draw) {
					if (currentTool.getDrawingType() == EDrawingType.TwoPoint) {
						eDrawingState = EDrawingState.TPDrawing;
					}
				} else {
					eDrawingState = EDrawingState.transforming;
				}
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			bClicked = false;
			if (eDrawingState == EDrawingState.TPDrawing || eDrawingState == EDrawingState.transforming) {
				keepTransforming(e.getX(), e.getY());
			}
		}	
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EDrawingState.TPDrawing) {
				if (!bClicked) {
					finishTransforming(e.getX(), e.getY());
				}
				eDrawingState = EDrawingState.idle;
			} else if (eDrawingState == EDrawingState.transforming) {
				finishTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.idle;
			}

		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 1) {
				mouse1Clicked(e);
			} else if(e.getClickCount() == 2) {
				mouse2Clicked(e);
			}
		}
		public void mouse1Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {
				initTransforming(e.getX(), e.getY());
				if (transformer.getShapeManager().getTransformationState()==ETransformationState.draw) {
					if (currentTool.getDrawingType() == EDrawingType.NPoint) {
						eDrawingState = EDrawingState.NPDrawing;
					}
				}
			} else if (eDrawingState == EDrawingState.NPDrawing) {
				continueTransforming(e.getX(), e.getY());
			}
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EDrawingState.idle) {
				cursorManager.changeCursor(e.getX(), e.getY() );
			} else if (eDrawingState == EDrawingState.NPDrawing) {
				keepTransforming(e.getX(), e.getY());
			}
		}
		public void mouse2Clicked(MouseEvent e) {
			if (eDrawingState == EDrawingState.NPDrawing) {
				finishTransforming(e.getX(), e.getY());
				eDrawingState = EDrawingState.idle;
			}
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e){	
		}
	}
}
