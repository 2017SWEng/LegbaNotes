package com.legba.notes.app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class AppController {
	
	@FXML
    private BorderPane root;
	
	@FXML
    private VBox content_root;
	
	@FXML
    private VBox reference_root;
	
	@FXML
	private HBox topbar_root;
	
	@FXML private Text actiontarget;
    
    @FXML protected void handleHomeButtonAction(ActionEvent event) {
        actiontarget.setText("Home button pressed");
    }
	
	public void initialize() {

    }
}
