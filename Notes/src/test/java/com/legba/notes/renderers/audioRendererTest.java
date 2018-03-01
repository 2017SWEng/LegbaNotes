package com.legba.notes.renderers;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import com.legba.notes.elements.Audio;
import com.legba.notes.renderers.AudioRenderer;

public class audioRendererTest {

	@Test
	public void test() {
		
		
		Audio audio = new Audio ("testData/audioTest.wav");
		AudioRenderer audiorend = new AudioRenderer(); 
		
		Node n = audiorend.render(audio);
		
		assertNotNull(n);
	}

}
