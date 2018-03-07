package com.legba.notes.renderers;

import javafx.scene.layout.VBox;

/**
 * dummyBox is a dummy('fake') class derived from a jfx Node.
 * Used for testing purposes.
 * @author zraw500
 *
 */
public class dummyBox extends VBox {
	
	public String name;
	public dummyBox(String name){
		this.name = name;
	}
}
