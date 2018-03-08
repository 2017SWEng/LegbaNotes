package com.legba.notes.renderers;

import javafx.scene.Node;
import javafx.scene.paint.Color;

import com.legba.notes.elements.Shape;
import javafx.scene.shape.*;

/**
 * 
 * Converts the properties in Shape to the correct type of javafx shape
 * If shape is not of type line, ellipse or rectangle then null is returned
 * @authors hjew501 rh1271
 *
 */

public class VectorRenderer extends Renderer<Shape> {
	
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
			line.setStartX(shape.getX());
			line.setStartY(shape.getY());
			line.setEndX(shape.getX2());
			line.setEndY(shape.getY2());
			line.setStrokeWidth(shape.getStroke());
			line.setStroke(shape.getColor());
			
			
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
			
			ellipse.setCenterX(shape.getX() == null ? 0 : shape.getX());
			ellipse.setCenterY(shape.getY() == null ? 0 : shape.getY());
			ellipse.setRadiusX(shape.getX2() == null ? 0 : shape.getX2());
			ellipse.setRadiusY(shape.getY2() == null ? 0 : shape.getY2());
			ellipse.setStrokeWidth(shape.getStroke() == null ? 0 : shape.getStroke());
			ellipse.setStroke(shape.getColor() == null ? Color.WHITESMOKE : shape.getColor());
			ellipse.setFill(shape.getFill() == null ? Color.DARKGRAY : shape.getFill());
			
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
			Rectangle rectangle = new Rectangle();
			
			rectangle.setX(shape.getX());
			rectangle.setY(shape.getY());
			rectangle.setWidth(shape.getWidth());
			rectangle.setHeight(shape.getHeight());
			rectangle.setStrokeWidth(shape.getStroke());
			rectangle.setStroke(shape.getColor());
			rectangle.setFill(shape.getFill());
			
			return (Node) rectangle;
		}
		return null;
	}

}
