package com.legba.notes.renders;

import javax.xml.soap.Node;

import com.legba.notes.elements.base.Element;

public interface Renderer<T extends Element> {

	public abstract Node render(T e);
}
