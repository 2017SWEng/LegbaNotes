package com.legba.notes.controllers;

import com.legba.notes.elements.Presentation;
import com.legba.notes.models.AppModel;
import com.legba.notes.models.ViewMode.Mode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MenuController {

	
	@FXML
	BorderPane topbar_root;
	
	@FXML
	Button HomeBtn;
	
	public MenuController(){
	}
	
	@FXML
    void initialize(){
	
	}
	
	@FXML 
	protected void handleHomeButtonAction(ActionEvent event) {
		
		if (AppModel.getInstance().getVeiwMode() == Mode.HOMEPAGE){
			
			FileSystemController fsc = new FileSystemController();
			
			Presentation pres = fsc.loadXmlFile("example.pws");
			
			AppModel.getInstance().setPres(pres);
			
			
			AppModel.getInstance().setVeiwMode(Mode.VEIWING);
		}else{
			AppModel.getInstance().setVeiwMode(Mode.HOMEPAGE);
		}
	}
}
