 package com.legba.notes.componets;

import com.legba.notes.componets.Component;

import javafx.scene.Node;

public class Slide extends Component {
	
	public Slide() {
		super("slide");
	}

	public void setID(String id) {
		this.addAttribute("id", id);
	}
	
	public void setDuration(String duration) {
		this.addAttribute("duration", duration);
	}

	@Override
	public Node render() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
