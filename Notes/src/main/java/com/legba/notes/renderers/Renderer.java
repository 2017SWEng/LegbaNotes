package com.legba.notes.renderers;

import javafx.scene.Node;

import com.legba.notes.elements.base.Element;

public abstract class Renderer<T extends Element> {

	public abstract Node render(T e);
}
