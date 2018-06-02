package com.legba.notes.models;

import java.util.Observer;

import com.legba.notes.controllers.AppController;
import com.legba.notes.elements.Presentation;

public class AppModel{

	private static AppModel instance;
	
	private String file;
	private Presentation pres;
	
	/**
	 * Current view mode
	 */
	private ViewMode viewMode;
	
	AppModel(){
		// any setup
		viewMode = new ViewMode();
	}
	
	public static AppModel getInstance() {
		if (instance == null){
			instance = new AppModel();
		}
		return instance;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Presentation getPres() {
		return pres;
	}

	public void setPres(Presentation pres) {
		
		// Cannot view a null presentaion
		if (pres == null){
			System.err.println("Presentaion was nulled\n Going back to home page");
			this.viewMode.setMode(ViewMode.Mode.HOMEPAGE);
		}
		
		this.pres = pres;
	}

	public ViewMode.Mode getVeiwMode() {
		return viewMode.getMode();
	}
	
	public void setVeiwMode(ViewMode.Mode viewMode) {
		
		// if null is passed default to the homepage
		if (viewMode == null){
			System.err.println("AppModel.setVeiwMode was passed null, defaulting to homepage");
			viewMode = ViewMode.Mode.HOMEPAGE;
		}
		else if(this.viewMode.getMode() == ViewMode.Mode.VEIWING) {
			AppController.getInstance().viewing.stopAllMedia();
		}
		
		System.out.println("Setting veiw to " + viewMode.toString());
		

		// if were not in viewing mode we shouldn't have a presentation open
		if (viewMode != ViewMode.Mode.VEIWING){
			this.pres = null;
		}
		
		// Carn't view a null presentaion
		if (viewMode == ViewMode.Mode.VEIWING && this.pres == null){
			System.err.println("Trying to enter veiwmode when no presentation is set");
			return;
		}
		
		this.viewMode.setMode(viewMode);
	}
	

	public void addVeiwModeObserver(Observer observer) {
		this.viewMode.addObserver(observer);		
	}

	
}
