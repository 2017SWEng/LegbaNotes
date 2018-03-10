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
	
	// Currently switches between the two veiw modes
	@FXML 
	protected void handleHomeButtonAction(ActionEvent event) {
		
		if (AppModel.getInstance().getVeiwMode() == Mode.HOMEPAGE){
			switchToViewing();
		}
		else if (AppModel.getInstance().getVeiwMode() == Mode.VEIWING){
			switchToHomepage();
		}
		else{
			System.err.println(this.toString() +" : uknown viewmode bailing to homepage" );
			switchToHomepage();

		}
	}
	
	private void switchToHomepage(){
		AppModel.getInstance().setVeiwMode(Mode.HOMEPAGE);
	}
	
	private void switchToViewing(){
		FileSystemController fsc = new FileSystemController();
		
		Presentation pres = fsc.loadXmlFile("example.pws");
		
		AppModel.getInstance().setPres(pres);
		
		
		AppModel.getInstance().setVeiwMode(Mode.VEIWING);
	}
}
