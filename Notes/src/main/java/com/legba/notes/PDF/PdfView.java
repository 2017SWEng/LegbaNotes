package com.legba.notes.PDF;

import com.legba.notes.app.AppController;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

public class PdfView {
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public Node render(String url) {
		
		BorderPane borderPane = new BorderPane();
		
		WebView pdfViewer = new WebView();
		WebEngine webEngine = pdfViewer.getEngine();
		webEngine.setJavaScriptEnabled(true);
		
		//Navigation buttons for moving around PDF document
		//When each button is clicked it carries out a function in pdf.html
		Button pageUp = new Button("Next Page");
		pageUp.setOnAction(new EventHandler<ActionEvent>() {
			
			

			@Override
			public void handle(ActionEvent e) {
	        	webEngine.executeScript("nextpage()");
			 }
		});
		
		Button pageDown = new Button("Previous Page");
		pageDown.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
	        	webEngine.executeScript("previouspage()");
			 }
		});
		
		Button first = new Button("<<");
		first.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
	        	webEngine.executeScript("firstpage()");
			 }
		});
		
		Button last = new Button(">>");
		last.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
	        	webEngine.executeScript("lastpage()");
			 }
		});
		
		//HBox holds all the buttons at top of pane
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #535360;");
		
		hbox.getChildren().addAll(first, pageDown, pageUp, last);
		borderPane.setTop(hbox);
		borderPane.setCenter(pdfViewer);
		
		//Loads PDF in pdf.html 
		
		System.out.println("loadpdf(\"" + url + "\")");
		webEngine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
	        if (newState == State.SUCCEEDED) {
	        		webEngine.executeScript("loadpdf(\"" + url + "\")");
	        }
	    });

		webEngine.load(this.getClass().getResource("pdf.html").toString());
		
		return borderPane;
		}
}
