package com.legba.notes.controllers;

import com.legba.notes.elements.Presentation;
import com.legba.notes.models.AppModel;
import com.legba.notes.models.ViewMode.Mode;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.input.MouseEvent;

public class MenuController {

	
	@FXML
	BorderPane topbar_root;
	
	@FXML
	Button HomeBtn;
	
	@FXML
	MenuBar menuBar;
	
	@FXML
	ImageView homeLogo;
	
	public MenuController(){
	}
	
	@FXML
    void initialize(){
	
	}
	
	// Currently switches between the two veiw modes
	@FXML 
	protected void handleHomeButtonAction(MouseEvent event) {
		
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
		FileSystemController fsc = AppController.getInstance().fileSystemController;
		
		Presentation pres = fsc.loadXmlFile("example.pws");
		
		AppModel.getInstance().setPres(pres);
		
		
		AppModel.getInstance().setVeiwMode(Mode.VEIWING);
	}
}
