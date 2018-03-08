package com.legba.notes.PDF;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.legba.notes.app.AppController;
import com.legba.notes.elements.Shape;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import javafx.concurrent.Worker.State;

public class PdfView {
	
	public void render(String url, AppController appController) {
		
		
		
		WebView pdfViewer = new WebView();
		WebEngine webEngine = pdfViewer.getEngine();
		webEngine.setJavaScriptEnabled(true);
		
		System.out.println("loadpdf(\"" + url + "\")");
		webEngine.getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
	        if (newState == State.SUCCEEDED) {
	        		webEngine.executeScript("loadpdf(\"" + url + "\")");
	        }
	    });

		webEngine.load(this.getClass().getResource("pdf.html").toString());

		
		//PDFJS.getDocument(url);
		
		appController.getReferenceRoot().getChildren().addAll(pdfViewer);
		
		
		
		
	}


}
