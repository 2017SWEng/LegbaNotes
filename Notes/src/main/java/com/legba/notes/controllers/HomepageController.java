package com.legba.notes.controllers;

import java.io.File;
import java.util.ArrayList;

import com.legba.notes.elements.Presentation;
import com.legba.notes.models.AppModel;
import com.legba.notes.models.ViewMode.Mode;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class HomepageController {

	@FXML
	ListView<String> contentList;
	
	@FXML
	Button fileChooserBtn;
	
	@FXML public void handleListClick(MouseEvent org0) {
		
		//Output to console
		System.out.println("selected " + contentList.getSelectionModel().getSelectedItem());
		
		//Change view to viewer and render the selected Object(s)
		FileSystemController fsc = new FileSystemController();
		Presentation pres = fsc.loadXmlFile(contentList.getSelectionModel().getSelectedItem());
		AppModel.getInstance().setPres(pres);
		AppModel.getInstance().setVeiwMode(Mode.VEIWING);
		
		
	}
	
	@FXML public void handleFileChooserBtn(ActionEvent event){
		System.out.println("Open File Chooser...");
	}
	
	public HomepageController(){
		
	}
	
	public void initialize() {
		
		//load some list example documents
		
		//----------------------------------------------------------//
		// Will need to check for a Legba Notes directory and then  //
		// create one if there is not one present.					//
		// Written by jj											//
		//----------------------------------------------------------//
		
		//lets put some things in our list
		ArrayList<File> files = new ArrayList<File>();
		files.add(new File("example.pws"));
		files.add(new File("example2.pws"));
		files.add(new File("/Notes/src/main/resources/com/legba/notes/PDF/pdf.html"));
		
		
		ObservableList<String> items = contentList.getItems();
		items.clear();
		for(int i = 0; i < files.size(); i++ ){
			items.add(files.get(i).toString());
		}	
		
		//let's sort out the click handler for our list
		
		
		System.out.println("Home Page Initialised");
		
	}
}
