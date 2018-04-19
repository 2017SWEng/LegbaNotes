package com.legba.notes.elements;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.scene.paint.Color;


public class ShapeTest {

	@Test
	public void test_line() {
		Shape linShape = new Shape("line");
		assertNotNull(linShape);
		assertEquals("line",linShape.getType());
	}
	
	@Test
	public void test_ellipse() {
		Shape ellShape = new Shape("ellipse");
		assertNotNull(ellShape);
		assertEquals("ellipse",ellShape.getType());
	}
	
	@Test
	public void test_rectangle() {
		Shape rectShape = new Shape("rectangle");
		assertNotNull(rectShape);
		assertEquals("rectangle",rectShape.getType());
	}
	
	@Test
	public void test_stroke() {
		Shape linShape = new Shape("line");
		linShape.setStroke(3);
		assertEquals(3, (int)linShape.getStroke());
	}
	
	@Test
	public void test_outline() {
		Shape linShape = new Shape("line");
		linShape.setColor(Color.INDIANRED);
		assertEquals(Color.INDIANRED, (Color)linShape.getColor());
	}
	
	@Test
	public void test_fill() {
		Shape ellShape = new Shape("ellipse");
		ellShape.setFill(Color.DODGERBLUE);
		assertEquals(Color.DODGERBLUE, (Color)ellShape.getFill());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void test_invalidShape() {
		Shape invShape = new Shape("Square");
		assertNull(invShape);
	}

}