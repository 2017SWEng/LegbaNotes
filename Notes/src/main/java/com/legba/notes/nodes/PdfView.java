package com.legba.notes.nodes;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import com.legba.notes.controllers.AppController;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;

public class PdfView extends BorderPane{
	private WebEngine webEngine;
	private Boolean pdfLoaded = false;
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public PdfView(String url) {
		super();

		WebView pdfViewer = new WebView();
		webEngine = pdfViewer.getEngine();
		webEngine.setJavaScriptEnabled(true);
		
		
		//Navigation buttons for moving around PDF document
		//When each button is clicked it carries out a function in pdf.html

		Button pageDown = new Button("Next Page");
		pageDown.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				if (pdfLoaded == true) {
					
					webEngine.executeScript("nextpage()");
					int currentPage = getPageNumber();
	    		
					AppController.getInstance().viewing.scrollToSlide(currentPage);
				}
	        	
			}
		});
		
		Button pageUp = new Button("Previous Page");
		pageUp.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				if (pdfLoaded == true) {
					webEngine.executeScript("previouspage()");
					int currentPage = getPageNumber();
	    		
					AppController.getInstance().viewing.scrollToSlide(currentPage-1);
				}
			}
		});
		
		Button first = new Button("<<");
		first.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				if (pdfLoaded == true) {
					webEngine.executeScript("firstpage()");
					AppController.getInstance().viewing.scrollToSlide(-100000);
				}
			}
		});
		
		Button last = new Button(">>");
		last.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				if (pdfLoaded == true) {
					webEngine.executeScript("lastpage()");
					AppController.getInstance().viewing.scrollToSlide(10000000);
				}
			}
		});
		
		//HBox holds all the buttons at top of pane
		
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10);
		hbox.setStyle("-fx-background-color: #535360;");
		
		hbox.getChildren().addAll(first, pageUp, pageDown, last);
		this.setTop(hbox);
		this.setCenter(pdfViewer);
		
		//Loads PDF in pdf.html 
		
		System.out.println("loadpdf(\"" + url + "\")");
		webEngine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
	        if (newState == State.SUCCEEDED) {
	        		webEngine.executeScript("loadpdf(\"" + url + "\")");
	        }
	    });

		webEngine.load(this.getClass().getClassLoader().getResource("com/legba/notes/PDF/pdf.html").toString());
		
		//https://stackoverflow.com/questions/12540044/execute-a-task-after-the-webview-is-fully-loaded?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
		webEngine.getLoadWorker().stateProperty().addListener(
				  new ChangeListener<Worker.State>() {
				  @Override
				  public void changed(
				    ObservableValue<? extends Worker.State> observable,
				    Worker.State oldValue, Worker.State newValue ) {

				    if( newValue == Worker.State.SUCCEEDED ) {
				      pdfLoaded = true;
				    }
				  }
				} );
		
		
	}
	
	public int getPageNumber(){
		return (int) webEngine.executeScript("getPageNumber()");
	}
}
