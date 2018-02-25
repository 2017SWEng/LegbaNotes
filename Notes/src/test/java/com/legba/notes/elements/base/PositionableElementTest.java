package com.legba.notes.elements.base;

import org.junit.Assert;
import org.junit.Test;

public class PositionableElementTest {

	// Cannot test and abstract class, so we make a 'mock' version of it
	private class PElement extends PositionableElement {

		public PElement() {
			super("PElement");
			// TODO Auto-generated constructor stub
		}
		
	}
	
	// Cannot test and abstract class, so we make a 'mock' version of it
	private class PTCFElement extends PositionableTransitionableColorableFormatableElement {

		public PTCFElement() {
			super("PTCFElement");
			// TODO Auto-generated constructor stub
		}
		
	}
	
	
	@Test
	public void testX() {
		float x = -10;
		
		PElement pe = new PElement();
		PTCFElement ptcfe = new PTCFElement();
		
		pe.setX(x);
		ptcfe.setX(x);
		
		Assert.assertEquals(pe.getX(),x, 0.01f);
		Assert.assertEquals(ptcfe.getX(),x, 0.01f);
	}
	
	@Test
	public void testX2() {
		float x2 = -10;
		
		PElement pe = new PElement();
		PTCFElement ptcfe = new PTCFElement();
		
		pe.setX2(x2);
		ptcfe.setX2(x2);
		
		Assert.assertEquals(pe.getX2(),x2, 0.01f);
		Assert.assertEquals(ptcfe.getX2(),x2, 0.01f);
	}
	
	@Test
	public void testY() {
		float y = -10;
		
		PElement pe = new PElement();
		PTCFElement ptcfe = new PTCFElement();

		pe.setY(y);
		ptcfe.setY(y);
		
		Assert.assertEquals(pe.getY(),y, 0.01f);
		Assert.assertEquals(ptcfe.getY(),y, 0.01f);
	}
	
	@Test
	public void testY2() {
		float y2 = -10;
		
		PElement pe = new PElement();
		PTCFElement ptcfe = new PTCFElement();

		pe.setY2(y2);
		ptcfe.setY2(y2);
		
		Assert.assertEquals(pe.getY2(),y2, 0.01f);
		Assert.assertEquals(ptcfe.getY2(),y2, 0.01f);
	}
	
	@Test
	public void testWidth() {
		PElement pe = new PElement();
		PTCFElement ptcfe = new PTCFElement();

		pe.setX(-10);
		pe.setX2(10);
		ptcfe.setX(-20);
		ptcfe.setX2(20);
		
		Assert.assertEquals(pe.getWidth(),20f,0.01f);
		Assert.assertEquals(ptcfe.getWidth(),40f,0.01f);
	}
	
	@Test
	public void testHeight() {
		PElement pe = new PElement();
		PTCFElement ptcfe = new PTCFElement();

		pe.setY(-10);
		pe.setY2(10);
		ptcfe.setY(-20);
		ptcfe.setY2(20);
		
		Assert.assertEquals(pe.getHeight(),20f,0.01f);
		Assert.assertEquals(ptcfe.getHeight(),40f,0.01f);
	}

}
