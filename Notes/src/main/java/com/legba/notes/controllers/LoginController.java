/**
 * 
 */
package com.legba.notes.controllers;

import com.legba.notes.models.AppModel;
import com.legba.notes.models.ViewMode.Mode;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * @author jjds502
 *
 */
public class LoginController {
	
	@FXML
	ImageView homeLogo;
	
	@FXML
	TextField usernameField;
	
	@FXML
	PasswordField passwordField;
	
	@FXML
	protected void handleHomeButtonAction(MouseEvent event) {
		 AppModel.getInstance().setVeiwMode(Mode.HOMEPAGE);
	}
	
	
	@FXML public void handleLoginBtnAction(ActionEvent e){
		if( usernameField.getText().equals("legba") && passwordField.getText().equals("password") ){
			AppModel.getInstance().setVeiwMode(Mode.HOMEPAGE);
		}
	}
	
	@FXML
	public void onEnter(ActionEvent ae){
		if( usernameField.getText().equals("legba") && passwordField.getText().equals("password") ){
			AppModel.getInstance().setVeiwMode(Mode.HOMEPAGE);
		}
	}
	
	public LoginController(){
		
	}
	
	public void initialize() {
		
		
	}
}
