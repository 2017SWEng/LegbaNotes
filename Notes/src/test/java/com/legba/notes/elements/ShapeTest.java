package com.legba.notes.elements;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class ShapeTest {

	@Test	//Ensures the correct shape is created
	public void test_line() {
		Shape linShape = new Shape("line");
		assertNotNull(linShape);
		assertEquals("line",linShape.getType());
	}
	
	@Test	//Ensures the correct shape is created
	public void test_ellipse() {
		Shape ellShape = new Shape("ellipse");
		assertNotNull(ellShape);
		assertEquals("ellipse",ellShape.getType());
	}
	
	@Test	//Ensures the correct shape is created
	public void test_rectangle() {
		Shape rectShape = new Shape("rectangle");
		assertNotNull(rectShape);
		assertEquals("rectangle",rectShape.getType());
	}
	
	@Test	//Ensures the thickness of the outline is as defined
	public void test_stroke() {
		Shape linShape = new Shape("line");
		linShape.setStroke(3);
		assertEquals(3, (int)linShape.getStroke());
	}
	
	@Test	//Ensures the outline colour is as specified
	public void test_outline() {
		Shape linShape = new Shape("line");
		linShape.setColor(Color.INDIANRED);
		assertEquals(Color.INDIANRED, linShape.getColor());
	}
	
	@Test	//Ensures the fill colour of the shape is as specified
	public void test_fill() {
		Shape ellShape = new Shape("ellipse");
		
		Stop[] stops = new Stop[] {new Stop(0, Color.DARKOLIVEGREEN), new Stop(1, Color.BLUEVIOLET)};
		ellShape.setFill(new LinearGradient(ellShape.getX(), ellShape.getY(), ellShape.getX2(), ellShape.getY2(), false, CycleMethod.NO_CYCLE, stops));
		
		Stop[] tempStops = new Stop[] {new Stop(0, ((LinearGradient) ellShape.getFill()).getStops().get(0).getColor()), new Stop(1, ((LinearGradient) ellShape.getFill()).getStops().get(1).getColor())};
		
		assertEquals(new LinearGradient(ellShape.getX(), ellShape.getY(), ellShape.getX2(), ellShape.getY2(), false, CycleMethod.NO_CYCLE, tempStops), ellShape.getFill());
	}
	
	//Throws an exception if an invalid shape type is created
	//Also ensures a shape is not created if an invalid shape parameter is used.
	@Test (expected=IllegalArgumentException.class)
	public void test_invalidShape() {
		Shape invShape = new Shape("Square");
		assertNull(invShape);
	}

}