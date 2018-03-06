package com.legba.notes.renders;

import javafx.scene.Node;

import com.legba.notes.elements.base.Element;

public interface Renderer<T extends Element> {

	public abstract Node render(T e);
}
