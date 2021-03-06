package com.legba.notes.renderers;

import static org.junit.Assert.*;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;

import org.junit.Test;

import com.legba.notes.elements.Shape;
import com.legba.notes.renderers.VectorRenderer;

public class VectorRendererTest {

	/**
	 * Supplies shape parameters to renderer and checks results are same as returned lines.
	 */
	@Test
	public void test_line() {
		VectorRenderer vr = new VectorRenderer();
		Shape line = new Shape("line");
		line.setX(5f);
		line.setY(10f);
		line.setX2(50f);
		line.setY2(100f);
		line.setStroke(2);
		line.setColor(Color.SEAGREEN);
		Line n = (Line) vr.render(line);
		
		/**
		 * Tests that shape parameters are rendered correctly
		 */
		assertNotNull(n);
		assertEquals((double)line.getX(), n.getStartX(),0.01f);
		assertEquals((double)line.getY(), n.getStartY(),0.01f);
		assertEquals((double)line.getX2(), n.getEndX(),0.01f);
		assertEquals((double)line.getY2(), n.getEndY(),0.01f);
		assertEquals((double)line.getStroke(), n.getStrokeWidth(),0.01f);
		assertEquals(line.getColor(), n.getStroke());	
		
	}
	
	@Test
	public void move_line_test() {
		VectorRenderer vr = new VectorRenderer();
		Shape line = new Shape("line");
		line.setX(50f);
		line.setY(10f);
		line.setX2(500f);
		line.setY2(100f);
		line.setStroke(2);
		line.setColor(Color.PAPAYAWHIP);
		Line n = (Line) vr.render(line);
		
		//Change line values
		line.setX(500f);
		line.setX2(200f);
		line.setY(333f);
		line.setY2(666f);
		line.setStroke(7);
		line.setColor(Color.SNOW);
		System.out.println(line.getColor());;
		
		/**
		 * Tests that new shape parameters are rendered correctly
		 */
		assertNotNull(n);
		assertEquals((double)line.getX(), n.getStartX(),0.01f);
		assertEquals((double)line.getY(), n.getStartY(),0.01f);
		assertEquals((double)line.getX2(), n.getEndX(),0.01f);
		assertEquals((double)line.getY2(), n.getEndY(),0.01f);
		assertEquals((double)line.getStroke(), n.getStrokeWidth(),0.01f);
		//The binding for colour was removed
		//assertEquals(line.getColor()[0], n.getStroke());
	}
	
	/**
	 * Supplies shape parameters to renderer and checks results are same as returned ellipses.
	 */
	@Test
	public void test_ellipse() {
		VectorRenderer vr = new VectorRenderer();
		Shape ellipse = new Shape("ellipse");
		ellipse.setX(5f);
		ellipse.setY(10f);
		ellipse.setX2(50f);
		ellipse.setY2(100f);
		ellipse.setStroke(2);
		ellipse.setColor(Color.LIGHTGOLDENRODYELLOW);
		
		LinearGradient lg = new LinearGradient(ellipse.getX(), ellipse.getY(), ellipse.getX2(), ellipse.getY2(), false, CycleMethod.NO_CYCLE, new Stop[] { new Stop(0, Color.HONEYDEW), new Stop(1, Color.BROWN)});
		
		ellipse.setFill(lg);
		
		Ellipse n = (Ellipse) vr.render(ellipse);
		
		/**
		 * Tests that shape parameters are rendered correctly
		 */
		assertNotNull(n);
		assertEquals((double)(ellipse.getX() + ellipse.getX2())/2, n.getCenterX(),0.01f);
		assertEquals((double)(ellipse.getY() + ellipse.getY2())/2, n.getCenterY(),0.01f);
		assertEquals((double)(ellipse.getX2() - ellipse.getX())/2, n.getRadiusX(),0.01f);
		assertEquals((double)(ellipse.getY2() - ellipse.getY())/2, n.getRadiusY(),0.01f);
		assertEquals((double)ellipse.getStroke(), n.getStrokeWidth(),0.01f);
		assertEquals(ellipse.getColor(), n.getStroke());
		assertEquals(ellipse.getFill(), n.getFill());
	}
	
	@Test
	public void move_ellipse_test() {
		//Give ellipse values
		VectorRenderer vr = new VectorRenderer();
		Shape ellipse = new Shape("ellipse");
		ellipse.setX(50f);
		ellipse.setY(10f);
		ellipse.setX2(500f);
		ellipse.setY2(100f);
		ellipse.setStroke(2);
		ellipse.setColor(Color.LIGHTSALMON);
		ellipse.setFill(Color.NAVAJOWHITE);
		Ellipse n = (Ellipse) vr.render(ellipse);
		
		//Change ellipse values
		
		ellipse.setX(500f);
		ellipse.setX2(200f);
		ellipse.setY(333f);
		ellipse.setY2(666f);
		
		ellipse.setStroke(20);
		ellipse.setColor(Color.CHARTREUSE);
		ellipse.setFill(Color.CHOCOLATE);
				
		/**
		 * Tests that new shape parameters are rendered correctly
		 */
		assertEquals((double)(ellipse.getX() + ellipse.getX2())/2, n.getCenterX(),0.01f);
		assertEquals((double)(ellipse.getY() + ellipse.getY2())/2, n.getCenterY(),0.01f);
		assertEquals((double)(ellipse.getX2() - ellipse.getX())/2, n.getRadiusX(),0.01f);
		assertEquals((double)(ellipse.getY2() - ellipse.getY())/2, n.getRadiusY(),0.01f);
		assertEquals((double)ellipse.getStroke(), n.getStrokeWidth(),0.01f);
		//The binding for color was removed
		assertEquals(ellipse.getColor(), n.getStroke());
		assertEquals(ellipse.getFill(), n.getFill());
	}
	
	/**
	 * Supplies shape parameters to renderer and checks results are same as returned rectangles.
	 */
	@Test
	public void test_rectangle() {
		VectorRenderer vr = new VectorRenderer();
		Shape rectangle = new Shape("rectangle");
		rectangle.setX(5f);
		rectangle.setY(10f);
		rectangle.setX2(50f);
		rectangle.setY2(100f);
		rectangle.setStroke(2);
		rectangle.setColor(Color.OLIVEDRAB);
		
		LinearGradient lg = new LinearGradient(rectangle.getX(), rectangle.getY(), rectangle.getX2(), rectangle.getY2(), false, CycleMethod.NO_CYCLE, new Stop[] { new Stop(0, Color.NAVAJOWHITE), new Stop(1, Color.ROYALBLUE)});
		rectangle.setFill(lg);
		
		
		Rectangle n = (Rectangle) vr.render(rectangle);
		
		/**
		 * Tests that shape parameters are rendered correctly
		 */
		assertNotNull(n);
		assertEquals((double)rectangle.getX(), n.getX(),0.01f);
		assertEquals((double)rectangle.getY(), n.getY(),0.01f);
		assertEquals((double)rectangle.getWidth(), n.getWidth(),0.01f);
		assertEquals((double)rectangle.getHeight(), n.getHeight(),0.01f);
		assertEquals((double)rectangle.getStroke(), n.getStrokeWidth(),0.01f);
		assertEquals(rectangle.getColor(), n.getStroke());
		assertEquals(rectangle.getFill(), n.getFill());		
	}
	
	@Test
	public void move_rectangle_test() {
		//Give rectangle values
		VectorRenderer vr = new VectorRenderer();
		Shape rectangle = new Shape("rectangle");
		rectangle.setX(50f);
		rectangle.setY(10f);
		rectangle.setX2(500f);
		rectangle.setY2(100f);
		rectangle.setStroke(2);
		rectangle.setColor(Color.PLUM);
		rectangle.setFill(Color.HOTPINK);
		Rectangle n = (Rectangle) vr.render(rectangle);
		
		/**
		 * Tests that shape parameters are rendered correctly
		 */
		
		assertEquals((double)rectangle.getX(), n.getX(),0.01f);
		assertEquals((double)rectangle.getY(), n.getY(),0.01f);
		assertEquals((double)rectangle.getWidth(), n.getWidth(),0.01f);
		assertEquals((double)rectangle.getHeight(), n.getHeight(),0.01f);
		assertEquals((double)rectangle.getStroke(), n.getStrokeWidth(),0.01f);
		assertEquals(rectangle.getColor(), n.getStroke());
		assertEquals(rectangle.getFill(), n.getFill());
		
		//Change rectangle values
		rectangle.setX(500f);
		rectangle.setX2(200f);
		rectangle.setY(333f);
		rectangle.setY2(666f);
		
		rectangle.setStroke(20);
		
		rectangle.setColor(Color.TOMATO);
		rectangle.setFill(Color.BISQUE);
				
		/**
		 * Tests that shape parameters are rendered correctly
		 */
		assertEquals((double)rectangle.getX(), n.getX(),0.01f);
		assertEquals((double)rectangle.getY(), n.getY(),0.01f);
		assertEquals((double)rectangle.getWidth(), n.getWidth(),0.01f);
		assertEquals((double)rectangle.getHeight(), n.getHeight(),0.01f);
		assertEquals((double)rectangle.getStroke(), n.getStrokeWidth(),0.01f);
		//The binding for color was removed
		//assertEquals(rectangle.getColor()[0], n.getStroke());
		//assertEquals(rectangle.getFill()[0], n.getFill());
	}
	
}


