package com.legba.notes.renderers;

import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import com.legba.notes.elements.Shape;
import javafx.scene.shape.*;

/**
 * 
 * Converts the properties in Shape to the correct type of javafx shape.
 * Binds the values in the shape passed in to the Javafx shape.
 * If shape is not of type line, ellipse or rectangle then null is returned.
 * @authors hjew501 rh1271
 *
 */

public class VectorRenderer extends Renderer<Shape> {
	
	//Default values 
	public static final double DEFAULT_X = 0;
	public static final double DEFAULT_Y = 0;
	public static final double DEFAULT_X2 = 10;
	public static final double DEFAULT_Y2 = 10;
	public static final double DEFAULT_STROKE = 2;
	public static final Color DEFAULT_BG = Color.WHITESMOKE;
	public static final Color DEFAULT_FG = Color.DIMGREY;
	
	
	/**
	 * Converts the properties in Shape to the correct type of javafx shape
	 * If shape is not of type line, ellipse or rectangle then null is returned
	 * @param shape	Contains position, size, stroke width, stroke colour and fill colour
	 * @return 		the javafx shape
	 */

	public Node render(Shape shape) {
		if (isLine(shape)) {
			return renderLine(shape);
		}
		else if(isEllipse(shape)){
			return renderEllipse(shape);
		}
		else if(isRectangle(shape)){
			return renderRectangle(shape);
		}
		
		System.err.println("Unknown shape passed to Vector renderer : [" + shape.getType()+"]");
		return null;
	}
	
	private boolean isLine(Shape shape){
		return shape.getType().equals("line");
	}
	
	private boolean isEllipse(Shape shape){
		return shape.getType().equals("ellipse");
	}
	
	private boolean isRectangle(Shape shape){
		return shape.getType().equals("rectangle");
	}
	
	/**
	 * Returns a javafx line that has been created using the properties in elements.Shape
	 * The shape has to be of type line, otherwise null is returned
	 * @param shape	Contains position, size, stroke width and colour
	 * @return 		the javafx line 
	 */
	
	private Node renderLine (Shape shape){
		
		if (isLine(shape)) {
			Line line = new Line();
			
			//Creates properties for binding to the values in shape 
			FloatProperty startX = new SimpleFloatProperty();
			FloatProperty endX = new SimpleFloatProperty();
			FloatProperty startY = new SimpleFloatProperty();
			FloatProperty endY = new SimpleFloatProperty();
			DoubleProperty strokeWidth = new SimpleDoubleProperty();
			ObjectProperty<Color> strokeColor = new SimpleObjectProperty<Color>();							
			
			//Sets defaults if values are null otherwise sets JavaFX values to values that shape.java passed in. 
			line.setStartX(shape.getX() == null ? DEFAULT_X : shape.getX());
			line.setStartY(shape.getY() == null ? DEFAULT_Y : shape.getY());
			line.setEndX(shape.getX2() == null ? DEFAULT_X2 : shape.getX2());
			line.setEndY(shape.getY2() == null ? DEFAULT_Y2 : shape.getY2());
			line.setStrokeWidth(shape.getStroke() == null ? DEFAULT_STROKE : shape.getStroke());
			line.setStroke(shape.getColor() == null ? DEFAULT_FG : shape.getColor());
			
			//Binds the x value in the shape to the startX value in line if x is not null
			if (shape.xProperty() != null) {
				line.startXProperty().bind(startX);
				startX.bind(shape.xProperty());
			}
			//Binds the x2 value in the shape to the endX value in line if x2 is not null
			if (shape.x2Property() != null) {
				line.endXProperty().bind(endX);
				endX.bind(shape.x2Property());
			}
			
			//Binds the y value in the shape to the startY value in line if y is not null
			if (shape.yProperty() != null) {
				line.startYProperty().bind(startY);
				startY.bind(shape.yProperty());
			}
			
			//Binds the y2 value in the shape to the endY value in line if y2 is not null
			if (shape.y2Property() != null) {
				line.endYProperty().bind(endY);
				endY.bind(shape.y2Property());
			}
			
			//Binds the stroke value in the shape to the strokeWidth value in line if stroke is not null
			if (shape.strokeProperty() != null) {
				line.strokeWidthProperty().bind(strokeWidth);
				strokeWidth.bind(shape.strokeProperty());
			}
			
			//Binds the color value in the shape to the stroke value in line if color is not null
			if (shape.colorProperty() != null) {
				line.strokeProperty().bind(strokeColor);
				strokeColor.bind(shape.colorProperty());
			}
			return (Node) line;
		}
		return null;
	}
	
	/**
	 * Returns a javafx ellipse that has been created using the properties in elements.Shape
	 * The shape has to be of type ellipse, otherwise null is returned
	 * @param shape	Contains position, size, stroke width, stroke colour and fill colour
	 * @return 		the javafx ellipse 
	 */
	
	private Node renderEllipse (Shape shape){
		if (isEllipse(shape)){
			Ellipse ellipse = new Ellipse();
			
			//Creates properties for binding to the values in shape 
			DoubleProperty strokeWidth = new SimpleDoubleProperty();
			ObjectProperty<Color> strokeColor = new SimpleObjectProperty<Color>();
			ObjectProperty<Color> fill = new SimpleObjectProperty<Color>();
			
			//Sets defaults if values are null otherwise sets JavaFX values to values that shape.java passed in. 
			ellipse.setCenterX(shape.getX() == null ? (DEFAULT_X + DEFAULT_X2)/2: shape.getX()+shape.getWidth()/2);
			ellipse.setCenterY(shape.getY() == null ? (DEFAULT_Y + DEFAULT_Y2)/2 : shape.getY()+shape.getHeight()/2);
			ellipse.setRadiusX(shape.getX2() == null ? (DEFAULT_X2 - DEFAULT_X)/2 : shape.getWidth()/2);
			ellipse.setRadiusY(shape.getY2() == null ? (DEFAULT_Y2 - DEFAULT_Y)/2 : shape.getHeight()/2);
			ellipse.setStrokeWidth(shape.getStroke() == null ? DEFAULT_STROKE : shape.getStroke());
			ellipse.setStroke(shape.getColor() == null ? DEFAULT_FG : shape.getColor());
			ellipse.setFill(shape.getFill() == null ? DEFAULT_BG : shape.getFill());
			
			//Binds the x and x2 value in the shape to the centerX and radiusX value in ellipse if x and x2 are not null
			if ((shape.xProperty()!= null) && (shape.x2Property() != null)) {
				NumberBinding centerX = (shape.xProperty().add(shape.x2Property())).divide(2);
				ellipse.centerXProperty().bind(centerX);
				
				NumberBinding radiusX = (shape.x2Property().subtract(shape.xProperty())).divide(2);
				ellipse.radiusXProperty().bind(radiusX);
			}
			
			//Binds the y and y2 value in the shape to the centerY and radiusY value in ellipse if y and y2 are not null
			if ((shape.yProperty()!= null) && (shape.y2Property() != null)) {
				NumberBinding centerY = (shape.yProperty().add(shape.y2Property())).divide(2);
				ellipse.centerYProperty().bind(centerY);

				NumberBinding radiusY = (shape.y2Property().subtract(shape.yProperty())).divide(2);
				ellipse.radiusYProperty().bind(radiusY);
			}
			
			//Binds the stroke value in the shape to the strokeWidth value in ellipse if stroke is not null
			if (shape.strokeProperty() != null) {
				ellipse.strokeWidthProperty().bind(strokeWidth);
				strokeWidth.bind(shape.strokeProperty());
			}
			
			//Binds the color value in the shape to the stroke value in ellipse if color is not null
			if (shape.colorProperty() != null) {
				ellipse.strokeProperty().bind(strokeColor);
				strokeColor.bind(shape.colorProperty());
			}
			
			//Binds the fill value in the shape to the fill value in ellipse if fill is not null
			if (shape.fillProperty() != null) {
				ellipse.fillProperty().bind(fill);
				fill.bind(shape.fillProperty());
			}			
			return (Node) ellipse;
		}
		return null;
	}
	
	/**
	 * Returns a javafx rectangle that has been created using the properties in elements.Shape
	 * The shape has to be of type rectangle, otherwise null is returned
	 * @param shape	Contains position, size, stroke width, stroke colour and fill colour
	 * @return 		the javafx rectangle 
	 */
	
	private Node renderRectangle (Shape shape){
		if (isRectangle(shape)){
			
			//Creates properties for binding to the values in shape 
			Rectangle rectangle = new Rectangle();
			FloatProperty x = new SimpleFloatProperty();
			FloatProperty y = new SimpleFloatProperty();
			DoubleProperty strokeWidth = new SimpleDoubleProperty();
			ObjectProperty<Color> strokeColor = new SimpleObjectProperty<Color>();
			ObjectProperty<Color> fill = new SimpleObjectProperty<Color>();
			
			//Sets defaults if values are null otherwise sets JavaFX values to values that shape.java passed in. 
			rectangle.setX(shape.getX() == null ? DEFAULT_X : shape.getX());
			rectangle.setY(shape.getY() == null ? DEFAULT_Y : shape.getY());
			rectangle.setWidth(shape.getWidth() == null ? (DEFAULT_X2 - DEFAULT_X) : shape.getWidth());
			rectangle.setHeight(shape.getHeight() == null ? (DEFAULT_Y2 - DEFAULT_Y) : shape.getHeight());
			rectangle.setStrokeWidth(shape.getStroke() == null ? DEFAULT_STROKE : shape.getStroke());
			rectangle.setStroke(shape.getColor() == null ? DEFAULT_FG : shape.getColor());
			rectangle.setFill(shape.getFill() == null ? DEFAULT_BG : shape.getFill());
			
			//Binds the x value in the shape to the x value in rectangle if x is not null
			if (shape.xProperty()!= null) {
				rectangle.xProperty().bind(x);
				x.bind(shape.xProperty());
			}
			
			//Binds the y value in the shape to the y value in rectangle if y is not null
			if (shape.yProperty() != null) {
				rectangle.yProperty().bind(y);
				y.bind(shape.yProperty());
			}
			
			//Binds the x and x2 value in the shape to the width value in rectangle if x and x2 are not null
			if ((shape.xProperty()!= null) && (shape.x2Property() != null)) {
				NumberBinding width = shape.x2Property().subtract(shape.xProperty());
				rectangle.widthProperty().bind(width);
			}
			
			//Binds the y and y2 value in the shape to the height value in rectangle if y and y2 are not null
			if ((shape.yProperty()!= null) && (shape.y2Property() != null)) {
				NumberBinding height = shape.y2Property().subtract(shape.yProperty());
				rectangle.heightProperty().bind(height);
			}
			
			//Binds the stroke value in the shape to the strokeWidth value in rectangle if stroke is not null
			if (shape.strokeProperty() != null) {
				rectangle.strokeWidthProperty().bind(strokeWidth);
				strokeWidth.bind(shape.strokeProperty());
			}
			
			//Binds the color value in the shape to the stroke value in rectangle if color is not null
			if (shape.colorProperty() != null) {
				rectangle.strokeProperty().bind(strokeColor);
				strokeColor.bind(shape.colorProperty());
			}
			
			//Binds the fill value in the shape to the fill value in rectangle if fill is not null
			if (shape.fillProperty() != null) {
				rectangle.fillProperty().bind(fill);
				fill.bind(shape.fillProperty());
			}
			
			
			return (Node) rectangle;
		}
		return null;
	}
}
